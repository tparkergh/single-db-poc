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

export const selectCurrentReferralId = (rawState) => createSelector(
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

export const selectAddresses = (rawState) => createSelector(
  (state) => {
    let addresses = [];
    let clientAddresses = state.getIn(['entities', 'clientAddress'], Map());
      clientAddresses.forEach(function(clientAddress) {
        let address = clientAddress.get('address', Map());
        addresses.push({
            street_number: address.get('street_number', '').trim(),
            street_name: address.get('street_name', '').trim(),
            city: address.get('city', '').trim(),
            state_code: address.get('state_code', ''),
            zip_code: address.get('zip_code', ''),
            latitude: address.get('latitude', ''),
            longitude: address.get('longitude', ''),
            address_type: clientAddress.get('address_tytpe_code', '')
          });
      });
    return addresses;
  },
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

export const selectAddressTypeOptions = (rawState) => createSelector(
  selectOptionsByMetaName('ADDR_TPC'),
  rawState
)

export const selectStateOptions = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'systemCode'], Map())
  .filter((systemCode) => systemCode.get('meta_name').includes('STATE_C'))
  .toList()
  .map((systemCode) => Map({
    key: systemCode.get('system_id'),
    option: systemCode.get('user_defined_logical_id', '').trim()
  }))
  .sortBy((systemCode) => systemCode.get('key')) ,
  rawState
)

const selectOptionsByMetaName = (metaName, filedName = 'short_description') => (rawState) => createSelector(
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
