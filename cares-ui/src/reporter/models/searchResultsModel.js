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
  }

  createReporter (result, options) {
    return axios({
      url: createReporterRoute(),
      method: 'post',
      data: this.buildReporter(result.data)})
    .then((result) => {
      this.onCompleting.error = false
    })
    .catch((error) => {
      this.onCompleting.error = true
    })
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

  update (props, data) {
    this.data = data
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
          const { first_name: firstName, last_name: lastName } = result
          element.choices.push(new ItemValue({
            value: index,
            text: `${firstName} ${lastName}`
          }))
        })
      } else {
        element.addError(new SurveyError("There are no matching existing reporters"))
      }
    }
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
	const rules = this.engine.find((rule) => rule.applies('create.reporter'))
	const results = rules.map((rule) => this.engine.evaluate(rule, this.validationData()))
	const errors = results.filter((result) => result !== true)
	options.error = errors.join("\n") || undefined
}

  loadJsonRules() {
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
    })
  }
}

