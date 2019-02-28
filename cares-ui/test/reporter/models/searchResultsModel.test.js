import SearchResultsModel from '../../../src/reporter/models/searchResultsModel'
import { Survey } from "survey-react";
import { searchRoute } from '../../../src/routes'
import { mount } from 'enzyme'

describe('SearchResultsModel', () => {
  it('loads the search results from the props when there is an update', () => {
    const props = { }
    const model = new SearchResultsModel(props)
    spyOn(model, 'loadResults')
    model.update(props)

    expect(model.loadResults).toHaveBeenCalledWith(props)
  })

  it('loads json rules for search after rendering', () => {
    const model = new SearchResultsModel()
    const survey = mount(<Survey model={model}/>)
    expect(model.engine.empty()).toEqual(false)
  })

  describe('validate', () => {
    it('validates that the reporter passes all business rules', () => {
      const model = new SearchResultsModel()
      const options = {}
      model.data = {
        first_name: 'first',
        last_name: 'last',
        phone_number: 123
      }
      model.validate({}, options)
      expect(options.error).toEqual(undefined)
    })
  })
})
