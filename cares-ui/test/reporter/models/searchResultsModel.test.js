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

  describe('createReporter', () => {
    it('creates a reporter', (done) => {
      const createReporterSuccess = jasmine.createSpy('createReporterSuccess')
      const props = { searchResults: [], createReporterSuccess }
      const model = new SearchResultsModel(props)
      const isEmptySpy = jasmine.createSpy('isEmpty').and.returnValue(true)
      const result = {
        getQuestionByName: jasmine.createSpy().and.returnValue({isEmpty: isEmptySpy}),
        data: {
          first_name: 'first',
          last_name: 'last',
          phone_number: '123456789',
          relationship_to_child: 'reporter'
        }
      }
      mockAxios.onPost(createReporterRoute()).reply(200)
      model.createReporter(result)
        .then(() => {
          expect(createReporterSuccess).toHaveBeenCalled()
          done()
        })
    })

    it('updates the search results model to be inactive with data', () => {
      const updateSearchModel = jasmine.createSpy('updateSearchModel')
      const props = { searchResults: [], updateSearchModel }
      mockAxios.onPost(createReporterRoute()).reply(200)
      const model = new SearchResultsModel(props)
      const isEmptySpy = jasmine.createSpy('isEmpty').and.returnValue(true)
      const result = {
        getQuestionByName: jasmine.createSpy().and.returnValue({isEmpty: isEmptySpy}),
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
          expect(updateSearchModel).toHaveBeenCalledWith({
            active: false,
            data: jasmine.anything()
          })

          done()
        })
    })

    it('updates the search model to be active', (done) => {
      const updateSearchModel = jasmine.createSpy('upadteSearchModel')
      const props = { searchResults: [], updateSearchModel }
      const model = new SearchResultsModel(props)
      const isEmptySpy = jasmine.createSpy('isEmpty').and.returnValue(true)
      const result = {
        getQuestionByName: jasmine.createSpy().and.returnValue({isEmpty: isEmptySpy}),
        data: {
          first_name: 'first',
          last_name: 'last',
          phone_number: '123456789',
          relationship_to_child: 'reporter'
        }
      }
      mockAxios.onPost(createReporterRoute()).reply(200)
      model.createReporter(result)
        .then(() => {
          expect(updateSearchModel).toHaveBeenCalledWith({
            active: true
          })
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
