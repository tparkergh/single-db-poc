import SearchModel from '../../../src/reporter/models/searchModel'
import {
  searchRoute,
  getBreRuleSetRoute
} from '../../../src/routes'
import { Survey } from "survey-react";
import { mount } from 'enzyme'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

const mockAxios = new MockAdapter(axios)

describe('SearchModel', () => {
  afterEach(() => {
    mockAxios.reset()
  })

  it('makes an api call to search on complete and sets search results', (done) => {
    const props = {
      setSearchResults: (data) => true
    }

    mockAxios.onPost(searchRoute()).reply(200, {})
    mockAxios.onGet(getBreRuleSetRoute('ReporterSearchScreenBusinessRules')).reply(200, { rules: [] })
    spyOn(props, 'setSearchResults').and.callFake((data) => {
      expect(mockAxios.history.post.length).toEqual(1)
      expect(props.setSearchResults).toHaveBeenCalledWith({})
      done()
    })

    const model = new SearchModel(props)
    model.doComplete()
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

  describe('loadJsonRules', () => {
    it('loads the json rules from an api', (done) => {
      const rule = {
        "and": [1,2]
      }
      mockAxios.onGet(getBreRuleSetRoute('ReporterSearchScreenBusinessRules')).reply(200, {
        rules: [{
          name: 'rule',
          logic: rule
        }]
      })
      const model = new SearchModel()
      model.loadJsonRules().then(() => {
        const rule = model.engine.find((rule) => rule.identifier === 'rule')
        expect(rule).toEqual(rule)
        done()
      })
    })
  })
})
