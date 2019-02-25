import SearchJSON from "../jsonForms/search"
import { searchRoute } from '../../routes'
import axios from 'axios'

import marked from 'marked'
import BaseModel from "./baseModel.js"

export default class SearchModel extends BaseModel {

  constructor(props) {
    super(SearchJSON)

    this.onAfterRenderSurvey.add(this.loadJsonRules.bind(this))
    this.onValidatePanel.add(this.validateName.bind(this))
    this.onValidateQuestion.add(this.validatePhoneNumber.bind(this))
    this.onCompleting.add((result, options) => {
      axios({
        url: searchRoute(),
        method: 'post',
        data: this.buildSearchQuery(result.data)
      }).then((result) => {
        props.setSearchResults && props.setSearchResults(result.data)
      })
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
    // temporary as these rules will be loaded from the api later
    const nameRule = {
      identifier: 'search-first-name-last-name-rule',
      definition: {
        "if": [
          { "and":
            [{ "missing": "search.reporter.first_name" },
              { "missing": "search.reporter.last_name" }]
          },
          "Last name or first name is required to search for a reporter.",
          true
        ]
      }
    }
    const phoneNumberRule = {
      identifier: 'phone-number-rule',
      definition: {
        "if": [
          { "missing": "search.reporter.phone_number" },
          "A phone number is required to search for a reporter.",
          true
        ]
      }
    }
    this.engine.define(nameRule)
    this.engine.define(phoneNumberRule)
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
