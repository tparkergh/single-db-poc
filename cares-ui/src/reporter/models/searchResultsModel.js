import SearchResultsJSON from "../jsonForms/searchResults"
import BaseModel from './baseModel'
import { ItemValue, SurveyError } from "survey-react";
import axios from 'axios'
import {
  getBreRuleSetRoute
} from '../../routes'

export default class SearchResultsModel extends BaseModel {
  constructor (props) {
    super(SearchResultsJSON)

    this.completeText = "Create Reporter"
    this.onCompleting.add(this.continueNext.bind(this))

    this.props = props
  }

  continueNext (result, options) {
    const {
      updateSearchResultsModel,
      updateReporterModel
    } = this.props
    updateSearchResultsModel && updateSearchResultsModel({
      active: false,
      data: this.data
    })
    updateReporterModel && updateReporterModel({
      active: true,
      data: this.data
    })
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

