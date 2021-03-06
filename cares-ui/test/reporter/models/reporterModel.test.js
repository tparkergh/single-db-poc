import ReporterModel from '../../../src/reporter/models/reporterModel'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import {
  createReporterRoute,
  getBreRuleSetRoute
} from '../../../src/routes'

const mockAxios = new MockAdapter(axios)

describe('ReporterModel', () => {
  describe('createOrUpdateReporter', () => {
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
      model.createOrUpdateReporter(result)
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
      model.createOrUpdateReporter(result)
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
      model.createOrUpdateReporter(result)
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

  describe('buildReporter', () => {
    it('builds a reporter to send to an API', () => {
      const props = {}
      const model = new ReporterModel(props)
      const data = {
        first_name: 'first',
        last_name: 'last',
        phone_number: '1234567899',
        relationship: "Reporter",
        employer: "employer",
        title: "title",
        address: "1234 C Street",
        city: "city",
        zip_code: "12345",
        state: 12345,
      }
      expect(model.buildReporter(data)).toEqual({
        first_name: 'first',
        last_name: 'last',
        primary_phone_number: 1234567899,
        relation_to_child: "Reporter",
        employer_name: "employer",
        title_description: "title",
        address: {
          street_name: "C Street",
          street_number: "1234",
          city: "city",
          zip_code: "12345",
          state_code: 12345
        }
      })
    })
  })

  describe('addCancelButton', () => {
    it('adds a cancel button to options html element', () => {
      const props = {}
      const model = new ReporterModel(props)
      const element = document.createElement("div")
      const innerElement = document.createElement("div")
      innerElement.className = 'sv_nav'
      element.appendChild(innerElement)
      const options = {
        htmlElement: element
      }
      model.addCancelButton({}, options)
      const cancelButton = options.htmlElement.querySelector('.sv_nav button.cancel')
      expect(cancelButton).toBeDefined()
      expect(cancelButton.className).toEqual("btn btn-info btn-xs cancel")
      expect(cancelButton.innerHTML).toEqual("Cancel")
      expect(cancelButton.onclick).toBeDefined()
    })
  })
})
