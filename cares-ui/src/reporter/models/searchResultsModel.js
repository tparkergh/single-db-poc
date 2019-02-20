import SearchResultsJSON from "../jsonForms/searchResults"
import BaseModel from './baseModel'
import { ItemValue, SurveyError } from "survey-react";

export default class SearchResultsModel extends BaseModel {
  constructor (props) {
    super(SearchResultsJSON)

    this.onCompleting.add((result, options) => {
      options.allowComplete=false
      props.clearSearchResults()
      // in the future this will update the store
    })
  }

  update (props) {
    this.loadResults(props)
  }

  loadResults (props) {
    const { searchResults } = props
    const panel = this.getPanelByName("existingReporter")
    const element = panel.elements[0]
    if (element) {
      element.choices = []
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
}

