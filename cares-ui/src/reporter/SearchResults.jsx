import { connect } from 'react-redux'
import {
  setSearchResults,
  clearSearchResults,
  updateSearchModel,
  updateSearchResultsModel,
  updateReporterModel,
  createReporterSuccess, 
  createReporterError
} from './actions'
import SearchResultsModel from './models/searchResultsModel'
import {
  selectReporterSearchResults,
  selectSearchResultsModelActive,
  selectSearchModelData
} from './selectors'
import JsonForm from './JsonForm'

const model = new SearchResultsModel({})

const mapStateToProps = (state, ownProps) => ({
  searchResults: selectReporterSearchResults(state),
  active: selectSearchResultsModelActive(state),
  data: selectSearchModelData(state), 
  error: state.errors.CREATE_REPORTER
})

const mapDispatchToProps = {
  setSearchResults,
  clearSearchResults,
  updateSearchModel,
  updateSearchResultsModel,
  updateReporterModel,
  selectSearchModelData,
  createReporterSuccess,
  createReporterError
}

const mergeProps = (stateProps, dispatchProps, ownProps) => ({
  ...stateProps,
  ...dispatchProps,
  ...ownProps,
  model
})

export default connect(mapStateToProps, mapDispatchToProps, mergeProps)(JsonForm)
