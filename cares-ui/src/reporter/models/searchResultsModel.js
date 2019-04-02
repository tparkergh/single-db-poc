import SearchResultsJSON from "../jsonForms/searchResults"
import BaseModel from './baseModel'
import { ItemValue, SurveyError } from "survey-react";
import axios from 'axios'
import { getReporterRoute } from "../../routes"

export default class SearchResultsModel extends BaseModel {
  constructor (props) {
    super(SearchResultsJSON)

    this.onCompleting.add(this.continueNext.bind(this))
    this.props = props
  }

  continueNext (result, options) {
    const {
      updateSearchResultsModel,
      updateReporterModel,
      clearSearchResults
    } = this.props
    updateSearchResultsModel && updateSearchResultsModel({
      active: false,
      data: this.data
    })
    clearSearchResults && clearSearchResults()
    if (this.data.reporter) {
      return axios({
        url: getReporterRoute(this.data.reporter[0]),
        method: 'get'
      }).then((result) => {
        updateReporterModel && updateReporterModel({
          active: true,
          data: this.transformReporter(result.data)
        })
      })
    } else {
      updateReporterModel && updateReporterModel({
        active: true,
        data: this.data
      })
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
        searchResults.map((result) => {
          element.choices.push(new ItemValue({
            value: result.identifier,
            text: this.formatSearchResult(result)
          }))
        })
      } else {
        element.addError(new SurveyError("There are no matching existing reporters"))
      }
    }
  }

  formatSearchResult ({expanded_identifier, first_name, last_name, primary_phone_number: phone_number}) {
    return [
      `Person Id: ${expanded_identifier}`,
      `Name: ${first_name} ${last_name}`,
      `Phone Number: ${phone_number}`
    ].join("\n")
  }

  transformReporter({
    identifier,
    first_name,
    last_name,
    primary_phone_number,
    primary_phone_extension,
    employer_name,
    title_description,
    birth_date,
    last_update_id,
    last_update_timestamp,
    address: {
      identifier: address_id,
      state_code,
      zip_code,
      city,
      street_name,
      street_number
    }
  }) {
    return {
      first_name,
      last_name,
      phone_number: primary_phone_number.toString(),
      extension: primary_phone_extension.toString(),
      employer: employer_name,
      title: title_description,
      birth_date,
      address: [ street_number, street_name ].join(' '),
      city,
      zip_code,
      reporter: identifier,
      last_update_id,
      last_update_timestamp,
      address_id,
      state: state_code
    }
  }
}

