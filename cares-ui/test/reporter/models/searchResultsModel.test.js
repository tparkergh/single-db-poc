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
    it('displays expanded_identifier, first_name, last_name, and phone_number', () => {
      const result = {
        expanded_identifier: 'exp-identifier',
        first_name: 'first',
        last_name: 'last',
        primary_phone_number: 1234567890
      }
      const props = {}
      const model = new SearchResultsModel(props)
      const formatedResult = model.formatSearchResult(result)
      expect(formatedResult).toMatch(/exp-identifier/)
      expect(formatedResult).toMatch(/first/)
      expect(formatedResult).toMatch(/last/)
      expect(formatedResult).toMatch(/1234567890/)
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

    it('does not validate the reporter when the question is empty', () => {
      const model = new SearchResultsModel()
      spyOn(model, 'getPanelByName').and.returnValue({
        getElementByName: jasmine.createSpy('getElementByName').and.returnValue({
          isEmpty: () => false
        })
      })
      const options = {}
      model.data = {
        first_name: 'first',
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
      const model = new SearchResultsModel({})
      model.loadJsonRules().then(() => {
        const rule = model.engine.find((rule) => rule.identifier === 'rule')
        expect(rule).toEqual(rule)
        done()
      })
    })

    it('sets the create reporter error when there is a problem with BRE', (done) => {
      mockAxios.onGet(getBreRuleSetRoute('ReporterCreateScreenBusinessRules')).reply(404)
      const createReporterError = jasmine.createSpy('createReporterError')
      const props =  { createReporterError }
      const model = new SearchResultsModel(props)
      model.loadJsonRules().then(() => {
        expect(createReporterError).toHaveBeenCalled()
        done()
      })
    })
  })
})
