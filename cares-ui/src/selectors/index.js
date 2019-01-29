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

export const selectApprovalStatusOptions = (rawState) => createSelector(
  selectOptionsByMetaName('APV_STC'),
  rawState
)

export const selectCommunicationMethodOptions = (rawState) => createSelector(
  selectOptionsByMetaName('CMM_MTHC'),
  rawState
)

export const selectGovernmentEntityOptions = (rawState) => createSelector(
  selectOptionsByMetaName('GVR_ENTC'),
  rawState
)

export const selectResponsibleAgencyOptions = (rawState) => createSelector(
  selectOptionsByMetaName('AGN_RSPC'),
  rawState
)

export const selectReferralResponseOptions = (rawState) => createSelector(
  selectOptionsByMetaName('RFR_RSPC'),
  rawState
)

const selectOptionsByMetaName = (metaName) => (rawState) => createSelector(
  (state) => state.getIn(['entities', 'systemCode'], Map())
  .filter((systemCode) => systemCode.get('meta_name').includes(metaName))
  .toList()
  .map((systemCode) => Map({
    key: systemCode.get('system_id'),
    option: systemCode.get('short_description', '').trim()
  }))
  .sortBy((systemCode) => systemCode.get('key')) ,
  rawState
)
