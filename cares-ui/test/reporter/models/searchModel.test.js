import SearchModel from '../../../src/reporter/models/searchModel'
import { searchRoute } from '../../../src/routes'
import { Survey } from "survey-react";
import { mount } from 'enzyme'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

describe('SearchModel', () => {
  it('makes an api call to search on complete and sets search results', (done) => {
    const props = {
      setSearchResults: (data) => true
    }
    const mockAxios = new MockAdapter(axios)

    mockAxios.onPost(searchRoute()).reply(200, {})
    spyOn(props, 'setSearchResults').and.callFake((data) => {
      expect(mockAxios.history.post.length).toEqual(1)
      expect(props.setSearchResults).toHaveBeenCalledWith({})
      done()
    })

    const model = new SearchModel(props)
    model.doComplete()
  })

  it('loads json rules for search after rendering', () => {
    const model = new SearchModel()
    const survey = mount(<Survey model={model}/>)
    expect(model.engine.empty()).toEqual(false)
  })

  describe('validateName', () => {
    it('validates that the first name and last name pass all business rules', () => {
      const model = new SearchModel()
      const options = {
        name: 'first_name'
      }
      model.data = {
        first_name: 'first',
        last_name: 'last'
      }
      model.validateName({}, options)
      expect(options.error).toEqual(undefined)
    })
  })

  describe('validatePhoneNumber', () => {
    it('validates that the phone number passes all business rules', () => {
      const model = new SearchModel()
      const options = {
        name: 'phone_number'
      }
      model.data = {
        phone_number: '1234567890'
      }
      model.validatePhoneNumber({}, options)
      expect(options.error).toEqual(undefined)
    })
  })
})
