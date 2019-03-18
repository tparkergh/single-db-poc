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

  describe('search', () => {
    it('sets the search results model to active', (done) => {
      mockAxios.onPost(searchRoute()).reply(200, {})
      const updateSearchResultsModel = jasmine.createSpy('updateSearchResultsModel')
      const props = {
        updateSearchResultsModel
      }
      const result = {
        data: {
          first_name: 'first',
          last_name: 'last',
          number: 123
        }
      }
      const model = new SearchModel(props)
      model.search(result)
        .then(() => {
          expect(updateSearchResultsModel).toHaveBeenCalledWith({
            active: true
          })
          done()
        })
    })

    it('sets the search model to inactive with data', (done) => {
      mockAxios.onPost(searchRoute()).reply(200, {})
      const updateSearchModel = jasmine.createSpy('updateSearchModel')
      const props = {
        updateSearchModel
      }
      const model = new SearchModel(props)
      const result = {
        data: {
          first_name: 'first',
          last_name: 'last',
          number: 123
        }
      }
      model.search(result)
        .then(() => {
          expect(updateSearchModel).toHaveBeenCalledWith({
            active: false,
            data: jasmine.anything()
          })
          done()
        })
    })

    it('sets the search result data', (done) => {
      mockAxios.onPost(searchRoute()).reply(200, {})
      const setSearchResults = jasmine.createSpy('setSearchResults')
      const props = {
        setSearchResults
      }
      const model = new SearchModel(props)
      const result = {
        data: {
          first_name: 'first',
          last_name: 'last',
          number: 123
        }
      }
      model.search(result)
        .then(() => {
          expect(setSearchResults).toHaveBeenCalledWith(jasmine.anything())
          done()
        })
    })
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
      const model = new SearchModel({})
      model.loadJsonRules().then(() => {
        const foundRule = model.engine.find((rule) => rule.identifier === 'rule')
        expect(foundRule[0].definition).toEqual(rule)
        done()
      })
    })

    it('sets the search results error when there is a problem with BRE', (done) => {
      mockAxios.onGet(getBreRuleSetRoute('ReporterSearchScreenBusinessRules')).reply(404)
      const errorSearchResults = jasmine.createSpy('reporterSearchScreenBusinessRules')
      const props =  { errorSearchResults }
      const model = new SearchModel(props)
      model.loadJsonRules().then(() => {
        expect(errorSearchResults).toHaveBeenCalled()
        done()
      })
    })
  })
})
