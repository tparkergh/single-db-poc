import SearchJSON from "../jsonForms/search"
import {
  searchRoute,
  getBreRuleSetRoute
} from '../../routes'
import BaseModel from "./baseModel.js"
import axios from 'axios'

export default class SearchModel extends BaseModel {
  RULE_PREFIX = "search.reporter."

  constructor(props) {
    super(SearchJSON)
    this.props = props

    this.onCompleting.add(this.search.bind(this))
  }

  update(props) {
    this.props = props
  }

  search(result, options) {
    const {
      setSearchResults,
      updateSearchModel,
      updateSearchResultsModel,
      errorSearchResults
    } = this.props
    return axios({
      url: searchRoute(), 
      method: 'post',
      data: this.buildSearchQuery(result.data)})
      .then((result) => {
        this.onCompleting.error = false
        setSearchResults && setSearchResults(result.data)
        updateSearchModel && updateSearchModel({
          active: false,
          data: this.data
        })
        updateSearchResultsModel && updateSearchResultsModel({active: true})
      })
      .catch((error) => {
        this.onCompleting.error = true
        errorSearchResults && errorSearchResults(error)
      })
  }

  validationData() {
    return {
      search: {
        reporter: {
          ...this.data
        }
      }
    }
  }

  loadJsonRules() {
    const { errorSearchResults } = this.props
    return axios({
      url: getBreRuleSetRoute('ReporterSearchScreenBusinessRules'),
      method: 'get',
    }).then((result) => {
      result.data.rules.map((rule) =>
        this.engine.define({
          identifier: rule.name,
          definition: rule.logic
        })
      )
    }).catch((error) => {
      errorSearchResults && errorSearchResults(error)
    })
  }

  buildSearchQuery({first_name, last_name, phone_number}) {
    return {
      limit: 10,
      sources: [
        "reporter"
      ],
      query: {
        person: {
          first_name,
          last_name,
          primary_phone_number: phone_number
        }
      }
    }
  }
}
