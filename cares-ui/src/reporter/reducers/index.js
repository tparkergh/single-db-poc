import {
  SET_SEARCH_RESULTS,
  CLEAR_SEARCH_RESULTS,
  ERROR_SEARCH_RESULTS,
  UPDATE_SEARCH_MODEL,
  UPDATE_SEARCH_RESULTS_MODEL
} from '../actions'
import { searchResults } from '../schemas'
import { normalize } from 'normalizr'

export default (state = {}, action) => {
  console.log('reporter store state: ', state)
  const { active, data } = action
  switch (action.type) {
    case SET_SEARCH_RESULTS:
      return {
        ...state,
        searchError: undefined,
        entities: {
          ...state.entities,
          ...normalize(action.payload.hits, searchResults).entities
        }
      }
    case CLEAR_SEARCH_RESULTS:
      const { person, ...rest } = state.entities || {}
      return {
        ...state,
        searchError: undefined,
        entities: {
          ...rest
        }
      }
    case ERROR_SEARCH_RESULTS: 
      return {
        ...state,
        searchError: action.payload.message
      }
    case UPDATE_SEARCH_MODEL:
      return {
        ...state,
        models: {
          ...state.models,
          search: {
            active,
            data
          }
        }
      }
    case UPDATE_SEARCH_RESULTS_MODEL:
      return {
        ...state,
        models: {
          ...state.models,
          search_results: {
            active,
            data
          }
        }
      }
    default:
      return state
  }
}
