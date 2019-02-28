import SearchResultsModel from '../../../src/reporter/models/searchResultsModel'
import { Survey } from "survey-react";
import { searchRoute } from '../../../src/routes'
import { mount } from 'enzyme'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import {
  createReporterRoute,
  getBreRuleSetRoute
} from '../../../src/routes'

const mockAxios = new MockAdapter(axios)

describe('SearchResultsModel', () => {
  it('loads the search results from the props when there is an update', () => {
    const props = { }
    const model = new SearchResultsModel(props)
    spyOn(model, 'loadResults')
    model.update(props)

    expect(model.loadResults).toHaveBeenCalledWith(props)
  })

  xit('loads json rules for search after rendering', () => {
    const model = new SearchResultsModel()
    const survey = mount(<Survey model={model}/>)
    expect(model.engine.empty()).toEqual(false)
  })

  describe('createReporter', () => {
    it('creates a reporter', () => {
      mockAxios.onPost(createReporterRoute()).reply(200)
      const model = new SearchResultsModel()
      const props = { searchResults: [] }
      const result = {
        data: {
          first_name: 'first',
          last_name: 'last',
          phone_number: '123456789',
          relationship_to_child: 'reporter'
        }
      }
      model.createReporter(result)
        .then(() => {
          expect(mockAxios.history.post.length).toEqual(1)
          done()
        })
    })
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

  describe('loadJsonRules', () => {
    it('loads the json rules from an api', (done) => {
      const rule = {
        "and": [1,2]
      }
      mockAxios.onGet(getBreRuleSetRoute('ReporterCreateScreenBusinessRules')).reply(200, {
        rules: [{
          name: 'rule',
          logic: rule
        }]
      })
      const model = new SearchResultsModel()
      model.loadJsonRules().then(() => {
        const rule = model.engine.find((rule) => rule.identifier === 'rule')
        expect(rule).toEqual(rule)
        done()
      })
    })
  })
})
