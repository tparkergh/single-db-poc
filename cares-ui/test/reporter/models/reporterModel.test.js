import ReporterModel from '../../../src/reporter/models/reporterModel'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import {
  createReporterRoute,
  getBreRuleSetRoute
} from '../../../src/routes'

const mockAxios = new MockAdapter(axios)

describe('ReporterModel', () => {
  describe('createReporter', () => {
    it('creates a reporter', (done) => {
      const createReporterSuccess = jasmine.createSpy('createReporterSuccess')
      const props = { searchResults: [], createReporterSuccess }
      const model = new ReporterModel(props)
      const result = {
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
      const model = new ReporterModel(props)
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
      const model = new ReporterModel(props)
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
      const model = new ReporterModel({})
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
      const model = new ReporterModel(props)
      model.loadJsonRules().then(() => {
        expect(createReporterError).toHaveBeenCalled()
        done()
      })
    })
  })
})
