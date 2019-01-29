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

const selectCurrentReferralId = (rawState) => createSelector(
  (state) => state.get('openReferralId'),
  rawState
)

export const selectReferrals = (rawState) => createSelector(
  (state) => state
    .getIn(['entities', 'referral'], Map())
    .toList(),
  rawState
)

export const selectCurrentReferral = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'referral', selectCurrentReferralId(state)]) || Map(),
  rawState
)

export const selectOpenClient = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'client', selectOpenClientId(state)]) || Map(),
  rawState
)

export const selectOpenClientId = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'allegation', selectOpenAllegationId(state), 'victim_client_id']),
  rawState
)

export const selectOpenAllegationId = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'referral', selectCurrentReferralId(state), 'allegations', 0]),
  rawState
)
