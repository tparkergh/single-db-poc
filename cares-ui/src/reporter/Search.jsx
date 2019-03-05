import { Component, Fragment } from "react"
import { Survey, StylesManager } from "survey-react";
import "survey-react/survey.css"
import { connect } from "react-redux"
import {
  updateSearchModel,
  updateSearchResultsModel,
  setSearchResults,
  errorSearchResults
} from './actions'
import SearchModel from './models/searchModel'
import {
  selectSearchModelActive
} from './selectors'
import { Alert } from "@cwds/components";

export class Search extends Component {
  constructor(props) {
    super(props)

    const search = new SearchModel(this.props)
    this.model = search
  }

  componentDidMount() {
    this.model.loadJsonRules()
  }

  render() {
    if (this.props.active)
    {
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
  active: selectSearchModelActive(state),
  error: state.errors.SEARCH_RESULTS
})

const mapDispatchToProps = ({
  updateSearchModel,
  updateSearchResultsModel,
  setSearchResults,
  errorSearchResults
})

export default connect(mapStateToProps, mapDispatchToProps)(Search)

