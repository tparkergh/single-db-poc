import { fromJS, Map, isImmutable } from 'immutable'

const createSelector = (f, state) => {
  if (isImmutable(state)) {
    return maybeToJS(f(state))
  }
  return maybeToJS(f(fromJS(state)))
}

const maybeToJS = (result) => {
  if (result && result.toJS) {
    return result.toJS()
  }
  return result
}

export const selectReporterSearchResults = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'person'], Map()).toList(),
  rawState
)

export const selectSearchResultsModelActive = (rawState) => createSelector(
  (state) => state.getIn(['models', 'search_results', 'active']) || false,
  rawState
)

export const selectSearchModelActive = (rawState) => createSelector(
  (state) => state.getIn(['models', 'search', 'active']) || false,
  rawState
)

export const selectReporterActive = (rawState) => createSelector(
  (state) => state.getIn(['models', 'reporter', 'active']) || false,
  rawState
)

export const selectSearchModelData = (rawState) => createSelector(
  (state) => state.getIn(['models', 'search', 'data']) || {},
  rawState
)

export const selectSearchResultsModelData = (rawState) => createSelector(
  (state) => state.getIn(['models', 'search_results', 'data']) || {},
  rawState
)

export const selectReporterModelData = (rawState) => createSelector(
  (state) => state.getIn(['models', 'reporter', 'data']) || {},
  rawState
)
