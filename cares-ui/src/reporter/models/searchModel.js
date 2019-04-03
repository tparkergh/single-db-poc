import SearchJSON from "../jsonForms/search"
import {
  searchRoute,
  getBreRuleSetRoute,
  getSystemCodesRoute
} from '../../routes'
import BaseModel from "./baseModel.js"
import axios from 'axios'

export default class SearchModel extends BaseModel {
  RULE_PREFIX = "search.reporter."

  constructor(props) {
    super(SearchJSON)
    this.props = props

    this.onCompleting.add(this.search.bind(this))
    this.loadReporterTypes()
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

    if( result.data.decline_to_state ) {
      updateSearchModel && updateSearchModel({
        active: false,
        data: this.data
      })
    } else {
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

  loadReporterTypes () {
    axios({
      url: getSystemCodesRoute('COL_RELC'),
      method: 'get'
    }).then((result) => {
      const response = result.data
        .sort((a, b) => {
          if (a.other_code && b.other_code) {
            return a.other_code - b.other_code
          }
          if (a.other_code) {
            return -1
          }
          if (b.other_code) {
            return -1
          }
          return 0
        })
        .map(r => ({
          value: r.system_id,
          text: r.short_description.trim()
        }))
      this.getQuestionByName('relationship').choices = response
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
