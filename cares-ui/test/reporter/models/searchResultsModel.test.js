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

  describe('loadResults', () => {
    it('formats the result', () => {
      const props = {
        searchResults: [{
          first_name: 'first',
          last_name: 'last',
          primary_phone_number: 1234567890
        }]
      }
      const model = new SearchResultsModel(props)
      spyOn(model, 'formatSearchResult')
      model.loadResults(props)
      expect(model.formatSearchResult).toHaveBeenCalled()
    })
  })

  describe('formatSearchResult', () => {
    it('displays first_name, last_name, and phone_number', () => {
      const result = {
        first_name: 'first',
        last_name: 'last',
        primary_phone_number: 1234567890
      }
      const props = {}
      const model = new SearchResultsModel(props)
      const formatedResult = model.formatSearchResult(result)
      expect(formatedResult).toMatch(/first/)
      expect(formatedResult).toMatch(/last/)
      expect(formatedResult).toMatch(/1234567890/)
    })
  })
})
