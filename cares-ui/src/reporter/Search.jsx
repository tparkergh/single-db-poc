import { Component } from "react";
import { Survey, Model, StylesManager } from "survey-react";
// import * as Survey  from 'survey-react'
import SearchJSON from "./jsonForms/search"
import SearchResultsJSON from "./jsonForms/searchResults"
import "survey-react/survey.css"
import { setupModel } from './helpers/survey'

export default class Search extends Component {
  constructor(props) {
    super(props)
    this.search()
  }

  search() {
    this.model = setupModel(SearchJSON)
    this.model.onComplete.add((result) => {
      // this would be an action dispatch to update the store
      // and re-render the component with updated search data
      // it would also dynamically render the search results
      this.setState({ results: this.model.data })
      this.searchResults()
    })
  }

  searchResults(setState = true) {
    this.model = setupModel(SearchResultsJSON)
    this.model.onComplete.add((result) => {
      // this would be an action dispatch to create the reporter
      // (maybe we need to use redux-saga)
      // and re-render the component at the search screen
      // it would also dynamically render a new survey
      this.setState({ results: this.model.data })
      this.search()
    })
  }

  render() {
    // StylesManager.applyTheme('bootstrap')
    // Survey.cssType = 'bootstrap'

    return <Survey model={this.model} />;
  }
}
