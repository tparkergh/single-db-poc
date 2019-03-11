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
import JsonForm from './JsonForm'

const model = new SearchModel({})

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

const mergeProps = (stateProps, dispatchProps, ownProps) => ({
  ...stateProps,
  ...dispatchProps,
  ...ownProps,
  model
})

export default connect(mapStateToProps, mapDispatchToProps, mergeProps)(JsonForm)

