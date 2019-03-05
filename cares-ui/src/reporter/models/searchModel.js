import SearchJSON from "../jsonForms/search"
import {
  searchRoute,
  getBreRuleSetRoute
} from '../../routes'
import BaseModel from "./baseModel.js"
import axios from 'axios'

export default class SearchModel extends BaseModel {

  constructor(props) {
    super(SearchJSON)
    this.props = props

    this.onValidatePanel.add(this.validateName.bind(this))
    this.onValidateQuestion.add(this.validatePhoneNumber.bind(this))
    this.onCompleting.add(this.search.bind(this))
  }

  search(result, options) {
    const {
      setSearchResults,
      updateSearchModel,
      updateSearchResultsModel,
      errorSearchResults
    } = this.props
    return axios({
      url: searchRoute(), 
      method: 'post',
      data: this.buildSearchQuery(result.data)})
      .then((result) => {
        this.onCompleting.error = false
        setSearchResults && setSearchResults(result.data)
        updateSearchModel && updateSearchModel({
          active: false,
          data: this.data
        })
        updateSearchResultsModel && updateSearchResultsModel({active: true})
      })
      .catch((error) => {
        this.onCompleting.error = true
        errorSearchResults && errorSearchResults(error)
      })
  }

  validationData() {
    return {
      search: {
        reporter: {
          ...this.data
        }
      }
    }
  }

  validateName(sender, options) {
    const firstNameRules = this.engine.find((rule) => rule.applies('search.reporter.first_name'))
    const lastNameRules = this.engine.find((rule) => rule.applies('search.reporter.last_name'))
    const rules = [...new Set([...firstNameRules, ...lastNameRules])]

    const results = rules.map((rule) => this.engine.evaluate(rule, this.validationData()))
    const errors = results.filter((result) => result !== true)
    options.error = errors.join("\n") || undefined
  }

  validatePhoneNumber(sender, options) {
    if(options.name === "phone_number") {
      const rules = this.engine.find((rule) => rule.applies('search.reporter.phone_number'))
      const results = rules.map((rule) => this.engine.evaluate(rule, this.validationData()))
      const errors = results.filter((result) => result !== true)
      options.error = errors.join("\n") || undefined
    }
  }

  loadJsonRules() {
    return axios({
      url: getBreRuleSetRoute('ReporterSearchScreenBusinessRules'),
      method: 'get',
    }).then((result) => {
      result.data.rules.map((rule) =>
        this.engine.define({
          identifier: rule.name,
          definition: rule.logic
        })
      )
    })
  }

  buildSearchQuery({first_name, last_name, number}) {
    return {
      limit: 10,
      sources: [
        "reporter"
      ],
      query: {
        person: {
          first_name,
          last_name,
          primary_phone_number: number
        }
      }
    }
  }
}
