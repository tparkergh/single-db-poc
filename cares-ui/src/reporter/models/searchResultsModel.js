import SearchResultsJSON from "../jsonForms/searchResults"
import BaseModel from './baseModel'
import { ItemValue, SurveyError } from "survey-react";
import axios from 'axios'
import {
  createReporterRoute,
  getBreRuleSetRoute
} from '../../routes'

export default class SearchResultsModel extends BaseModel {
  constructor (props) {
    super(SearchResultsJSON)

    this.completeText = "Create Reporter"
    this.onValidatePanel.add(this.validate.bind(this))
    this.onValidateQuestion.add(this.setContinueText.bind(this))
    this.onCompleting.add(this.createReporter.bind(this))

    this.props = props
  }

  createReporter (result, options) {
    if (result.getQuestionByName('reporter').isEmpty()) {
      const {
        updateSearchModel,
        updateSearchResultsModel,
        createReporterSuccess,
        createReporterError
      } = this.props
      return axios({
        url: createReporterRoute(),
        method: 'post',
        data: this.buildReporter(result.data)
      })
        .then((result) => {
          updateSearchResultsModel && updateSearchResultsModel({
            active: false,
            data: this.data
          })
          updateSearchModel && updateSearchModel({ active: true })
          createReporterSuccess && createReporterSuccess()
        })
        .catch((error) => {
          createReporterError && createReporterError(error)
        })
    }
  }

  buildReporter ({
    first_name,
    last_name,
    phone_number,
    relationship
  }) {
    return {
      first_name,
      last_name,
      phone_number: parseInt(phone_number),
      relation_to_child: relationship
    }
  }

  update (props) {
    this.props = props
    this.data = props.data
    this.loadResults(props)
  }

  loadResults (props) {
    const { searchResults } = props
    const panel = this.getPanelByName("existingReporter")
    const element = panel.elements[0]
    if (element) {
      element.choices = []
      element.errors = []
      if (searchResults && searchResults.length > 0) {
        searchResults.map((result, index) => {
          element.choices.push(new ItemValue({
            value: index,
            text: this.formatSearchResult(result)
          }))
        })
      } else {
        element.addError(new SurveyError("There are no matching existing reporters"))
      }
    }
  }

  formatSearchResult ({first_name, last_name, primary_phone_number: phone_number}) {
    return [
      `Name: ${first_name} ${last_name}`,
      `Phone Number: ${phone_number}`
    ].join("\n")
  }



  setContinueText(sender, options) {
    if(options.name === "reporter") {
      if (options.question.isEmpty())
        this.completeText = "Create Reporter"
      else
        this.completeText = "Continue"
    }
    this.render()
  }

  validationData() {
    return {
      create: {
        reporter: {
          ...this.data
        }
      }
    }
  }

  validate(sender, options) {
    const panel = this.getPanelByName("existingReporter")
    const question = panel.getElementByName("reporter")
    if (question.isEmpty()) {
      const rules = this.engine.find((rule) => rule.applies('create.reporter'))
      const results = rules.map((rule) => this.engine.evaluate(rule, this.validationData()))
      const errors = results.filter((result) => result !== true)
      options.error = errors.join("\n") || undefined
    }
}

  loadJsonRules() {
    const { createReporterError } = this.props
    return axios({
      url: getBreRuleSetRoute('ReporterCreateScreenBusinessRules'),
      method: 'get',
    }).then((result) => {
      result.data.rules.map((rule) =>
        this.engine.define({
          identifier: rule.name,
          definition: rule.logic
        })
      )
    }).catch((error) => {
      createReporterError && createReporterError(error)
    })
  }
}

