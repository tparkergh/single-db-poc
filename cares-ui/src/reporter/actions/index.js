export const SET_SEARCH_RESULTS = 'SET_SEARCH_RESULTS'
export const CLEAR_SEARCH_RESULTS = 'CLEAR_SEARCH_RESULTS'
export const ERROR_SEARCH_RESULTS = 'ERROR_SEARCH_RESULTS'
export const UPDATE_SEARCH_MODEL = 'UPDATE_SEARCH_MODEL'
export const UPDATE_SEARCH_RESULTS_MODEL = 'UPDATE_SEARCH_RESULTS_MODEL'

export const setSearchResults = payload => ({
  type: SET_SEARCH_RESULTS,
  payload
})

export const errorSearchResults = error => ({
  type: ERROR_SEARCH_RESULTS,
  payload: error,
  error: true
})

export const clearSearchResults = () => ({
  type: CLEAR_SEARCH_RESULTS
})

export const updateSearchModel = ({ active, data }) => ({
  type: UPDATE_SEARCH_MODEL,
  active,
  data
})

export const updateSearchResultsModel = ({ active, data }) => ({
  type: UPDATE_SEARCH_RESULTS_MODEL,
  active,
  data
})
