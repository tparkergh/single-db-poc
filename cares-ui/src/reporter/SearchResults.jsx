import { Component } from "react";
import { Survey } from "survey-react";
import { connect } from 'react-redux'
import {
  setSearchResults,
  clearSearchResults,
  updateSearchModel,
  updateSearchResultsModel
} from './actions'
import SearchResultsModel from './models/searchResultsModel'
import {
  selectReporterSearchResults,
  selectSearchResultsModelActive
} from './selectors'

export class SearchResults extends Component {
  constructor (props) {
    super(props)
    const { data, onCompleting } = props

    this.model = new SearchResultsModel(this.props)
    this.model.onCompleting.add((result) => {
      updateSearchResultsModel({
        active: false,
        data
      })
      updateSearchModel({ active: true })
    })
  }

  componentDidUpdate() {
    const { data } = this.props
    this.model.update(this.props, data)
  }

  render () {
    if (this.props.active)
      return <Survey model = {this.model} />
    return null
  }
}

const mapStateToProps = (state, ownProps) => ({
  searchResults: selectReporterSearchResults(state),
  active: selectSearchResultsModelActive(state)
})

const mapDispatchToProps = {
  setSearchResults,
  clearSearchResults
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchResults)
