export const SEARCH_RESULTS_SUCCESS = 'SEARCH_RESULTS_SUCCESS'
export const CLEAR_SEARCH_RESULTS = 'CLEAR_SEARCH_RESULTS'
export const SEARCH_RESULTS_ERROR = 'SEARCH_RESULTS_ERROR'
export const UPDATE_SEARCH_MODEL = 'UPDATE_SEARCH_MODEL'
export const UPDATE_SEARCH_RESULTS_MODEL = 'UPDATE_SEARCH_RESULTS_MODEL'
export const CREATE_REPORTER_SUCCESS = 'CREATE_REPORTER_SUCCESS' 
export const  CREATE_REPORTER_ERROR = 'CREATE_REPORTER_ERROR'

export const setSearchResults = payload => ({
  type: SEARCH_RESULTS_SUCCESS,
  payload
})

export const errorSearchResults = error => ({
  type: SEARCH_RESULTS_ERROR,
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

export const createReporterSuccess = () => ({
  type: CREATE_REPORTER_SUCCESS
})

export const createReporterError = error => ({
  type: CREATE_REPORTER_ERROR,
  payload: error,
  error: true
})
