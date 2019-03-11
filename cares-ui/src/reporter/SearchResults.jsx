import { connect } from 'react-redux'
import {
  setSearchResults,
  clearSearchResults,
  updateSearchModel,
  updateSearchResultsModel,
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
  createReporterSuccess,
  createReporterError
}

const mergeProps = (stateProps, dispatchProps, ownProps) => {
  const props = {
    ...stateProps,
    ...dispatchProps,
    ...ownProps
  }
  const model = new SearchResultsModel(props)
  return ({
    ...props,
    model
  })
}

export default connect(mapStateToProps, mapDispatchToProps, mergeProps)(JsonForm)
