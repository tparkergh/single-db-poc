import { fromJS, Map, isImmutable } from 'immutable'

const createSelector = (f, state) => {
  const result = f(fromJS(state))
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
  (state) => state.getIn(['entities', 'referral', selectCurrentReferralId(rawState)]) || Map(),
  rawState
)

export const selectOpenClient = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'client', selectOpenClientId(rawState)]) || Map(),
  rawState
)

export const selectOpenClientId = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'allegation', selectOpenAllegationId(rawState), 'victim_client_id']),
  rawState
)

export const selectOpenAllegationId = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'referral', selectCurrentReferralId(rawState), 'allegations', 0]),
  rawState
)
