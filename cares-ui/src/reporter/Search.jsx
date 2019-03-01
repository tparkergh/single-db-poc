import { Component, Fragment } from "react"
import { Survey, StylesManager } from "survey-react";
// import * as Survey  from 'survey-react'
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
    const { updateSearchModel, updateSearchResultsModel } = this.props
    search.onComplete.add((result) => {
      updateSearchModel && updateSearchModel({
        active: false,
        data: this.model.data
      })
      updateSearchResultsModel && updateSearchResultsModel({ 
        active: true 
      })
    })
    this.model = search
  }

  render() {
    if (this.props.active)
    {
      const errorMsg = this.props.searchError
      return (
        <Fragment>
          {errorMsg && 
            <Alert className="errorMessage-customizable" color="danger">
              {errorMsg.user_message ? errorMsg.user_message : errorMsg}
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
  searchError: state.searchError
})

const mapDispatchToProps = ({
  updateSearchModel,
  updateSearchResultsModel,
  setSearchResults,
  errorSearchResults
})

export default connect(mapStateToProps, mapDispatchToProps)(Search)

