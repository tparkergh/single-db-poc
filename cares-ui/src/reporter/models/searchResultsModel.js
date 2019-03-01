import SearchResultsJSON from "../jsonForms/searchResults"
import BaseModel from './baseModel'
import { ItemValue, SurveyError } from "survey-react";

export default class SearchResultsModel extends BaseModel {
  constructor (props) {
    super(SearchResultsJSON)

    this.completeText = "Create Reporter"
    this.loadJsonRules()
    this.onValidatePanel.add(this.validate.bind(this))
    this.onValidateQuestion.add(this.setContinueText.bind(this))
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
    // temporary as these rules will be loaded from the api later
    const firstNameRule = {
      identifier: 'create-reporter-first-name-rule',
	  definition: {
		"if": [
		  { "missing": "create.reporter.first_name" },
		  "A first name is required to create a reporter.",
		  true
		]
	  }
	}
    const lastNameRule = {
      identifier: 'create-reporter-last-name-rule',
	  definition: {
		"if": [
		  { "missing": "create.reporter.last_name" },
		  "A last name is required to create a reporter.",
		  true
		]
	  }
	}
    const phoneNumberRule = {
      identifier: 'create-reporter-phone-number-rule',
	  definition: {
		"if": [
		  { "missing": "create.reporter.phone_number" },
		  "A phone number is required to create a reporter.",
		  true
		]
	  }
    }
    this.engine.define(firstNameRule)
    this.engine.define(lastNameRule)
    this.engine.define(phoneNumberRule)
  }
}

