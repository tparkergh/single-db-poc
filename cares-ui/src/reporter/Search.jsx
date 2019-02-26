import { Component } from "react";
import { Survey, StylesManager } from "survey-react";
// import * as Survey  from 'survey-react'
import "survey-react/survey.css"
import { connect } from 'react-redux'
import {
  setSearchResults,
  clearSearchResults
} from './actions'
import { selectReporterSearchResults } from './selectors'
import SearchModel from './models/searchModel'
import SearchResultsModel from './models/searchResultsModel'

export class Search extends Component {
  constructor(props) {
    super(props)

    const search = new SearchModel(this.props)
    const results = new SearchResultsModel(this.props)
    search.onCompleting.add((result) => {
      const data = this.model.data
      this.model = results
      // copy over the data so that we can create a reporter
      this.model.data = data
    })
    results.onCompleting.add((result) => (this.model = search))
    this.model = search
  }

  componentDidUpdate() {
    this.model.update(this.props)
  }

  render() {
    // StylesManager.applyTheme('bootstrap')
    // Survey.cssType = 'bootstrap'

    return <Survey model={this.model} />;
  }
}

const mapStateToProps = (state, ownProps) => ({
  searchResults: selectReporterSearchResults(state)
})

const mapDispatchToProps = {
  setSearchResults,
  clearSearchResults
}
export default connect(mapStateToProps, mapDispatchToProps)(Search)
