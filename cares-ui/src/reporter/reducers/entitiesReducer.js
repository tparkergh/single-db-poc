import {
  SEARCH_RESULTS_SUCCESS,
  CLEAR_SEARCH_RESULTS
} from '../actions'
import { searchResults } from '../schemas'
import { normalize } from 'normalizr'

export default (state = {}, action) => {
  const { active, data } = action
  switch (action.type) {
    case SEARCH_RESULTS_SUCCESS:
      return {
          ...state,
          ...normalize(action.payload.hits, searchResults).entities
        }
    case CLEAR_SEARCH_RESULTS:
      const { person, ...rest } = state || {}
      return {
          ...rest
        }
    default:
      return state
  }
}
