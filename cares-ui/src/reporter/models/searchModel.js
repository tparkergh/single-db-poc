import SearchJSON from "../jsonForms/search"
import { searchRoute } from '../../routes'
import axios from 'axios'

import marked from 'marked'
import BaseModel from "./baseModel.js"

export default class SearchModel extends BaseModel {
  constructor(props) {
    super(SearchJSON)

    this.onCompleting.add((result, options) => {
      axios({
        url: searchRoute(),
        method: 'post',
        data: this.buildSearchQuery(result.data)
      }).then((result) => {
        props.setSearchResults && props.setSearchResults(result.data)
      })
    })
  }

  buildSearchQuery({first_name, last_name, number}) {
    return {
      limit: 10,
      sources: [
        "reporter"
      ],
      query: {
        person: {
          first_name,
          last_name,
          primary_phone_number: number
        }
      }
    }
  }
}
