import {
  UPDATE_SEARCH_MODEL,
  UPDATE_SEARCH_RESULTS_MODEL
} from '../actions'

export default (state = {}, action) => {
  const { active, data } = action
  switch (action.type) {
    case UPDATE_SEARCH_MODEL:
      return {
          ...state,
          search: {
            active,
            data
          }
        }
    case UPDATE_SEARCH_RESULTS_MODEL:
      return {
          ...state,
          search_results: {
            active,
            data
          }
        }
    default:
      return state
  }
}
