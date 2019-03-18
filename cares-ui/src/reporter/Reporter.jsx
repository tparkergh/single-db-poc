import { connect } from "react-redux"
import ReporterModel from './models/reporterModel'
import JsonForm from './JsonForm'
import {
  selectReporterActive,
  selectSearchResultsModelData
} from './selectors'
import {
  updateSearchModel,
  updateReporterModel,
  createReporterSuccess,
  createReporterError
} from './actions'

const model = new ReporterModel({})

const mapStateToProps = (state, ownProps) => ({
  active: selectReporterActive(state),
  data: selectSearchResultsModelData(state)
})
const mapDispatchToProps = ({
  updateSearchModel,
  updateReporterModel,
  createReporterSuccess,
  createReporterError
})

const mergeProps = (stateProps, dispatchProps, ownProps) => ({
  ...stateProps,
  ...dispatchProps,
  ...ownProps,
  model
})

export default connect(mapStateToProps, mapDispatchToProps, mergeProps)(JsonForm)
