import { Component } from "react";
import { Survey, StylesManager } from "survey-react";
// import * as Survey  from 'survey-react'
import "survey-react/survey.css"
import { connect } from 'react-redux'
import {
  updateSearchModel,
  updateSearchResultsModel
} from './actions'
import SearchModel from './models/searchModel'
import {
  selectSearchModelActive
} from './selectors'

export class Search extends Component {
  constructor(props) {
    super(props)

    const search = new SearchModel(this.props)
    search.onCompleting.add((result) => {
      updateSearchModel({
        active: false,
        data: this.model.data
      })
      updateSearchResultsModel({ active: true })
    })
    this.model = search
  }

  render() {
    // StylesManager.applyTheme('bootstrap')
    // Survey.cssType = 'bootstrap'

    if (this.props.active)
      return <Survey model={this.model} />;
    return null
  }
}

const mapStateToProps = (state, ownProps) => ({
  active: selectSearchModelActive(state)
})

export default connect(mapStateToProps)(Search)
