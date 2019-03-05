import { Component, Fragment } from "react";
import { Survey } from "survey-react";
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
import { Alert } from "@cwds/components";

export class SearchResults extends Component {
  constructor (props) {
    super(props)
    this.model = new SearchResultsModel(this.props)
  }

  componentDidMount() {
    this.model.loadJsonRules()
  }

  componentDidUpdate() {
    const { data } = this.props
    this.model.update(this.props, data)
  }

  render () {
    if (this.props.active) {
      const errorMsg = this.props.error
      return (
        <Fragment>
          {errorMsg && 
            <Alert className="errorMessage-customizable" color="danger">
              {errorMsg}
            </Alert>
          }
          <Survey model={this.model} />
       </Fragment>)
    } 
    return null
  }
}

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

export default connect(mapStateToProps, mapDispatchToProps)(SearchResults)
