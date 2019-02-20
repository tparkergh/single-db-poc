import {
  SET_SEARCH_RESULTS,
  CLEAR_SEARCH_RESULTS
} from '../actions'
import { searchResults } from '../schemas'
import { normalize } from 'normalizr'

export default (state = {}, action) => {
  console.log('reporter store state: ', state)
  switch (action.type) {
    case SET_SEARCH_RESULTS:
      return {
        ...state,
        entities: {
          ...state.entities,
          ...normalize(action.payload.hits, searchResults).entities
        }
      }
    case CLEAR_SEARCH_RESULTS:
      const { person, ...rest } = state.entities
      return {
        ...state,
        entities: {
          ...rest
        }
      }
    default:
      return state
  }
}
