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
  selectSearchResultsModelActive,
  selectSearchModelData
} from './selectors'

export class SearchResults extends Component {
  constructor (props) {
    super(props)
    const {
      data,
      updateSearchResultsModel,
      updateSearchModel,
      clearSearchResults
    } = props

    this.model = new SearchResultsModel(this.props)
    this.model.onCompleting.add((result) => {
      updateSearchResultsModel && updateSearchResultsModel({
        active: false,
        data
      })
      updateSearchModel && updateSearchModel({ active: true })
      clearSearchResults && clearSearchResults()
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
  active: selectSearchResultsModelActive(state),
  data: selectSearchModelData(state)
})

const mapDispatchToProps = {
  setSearchResults,
  clearSearchResults,
  updateSearchModel,
  updateSearchResultsModel
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchResults)
