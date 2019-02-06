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

export const selectAddress = (rawState, addressId) => createSelector(
  (state) => {
      let clientAddress = state.getIn(['entities', 'clientAddress'], Map())
        .first((clientAddress) => clientAddress.get('address') == addressId)
      let address = state.getIn(['entities', 'address', addressId], Map())
      return {
         identifier: address.get('identifier', ''),
         streetNumber: address.get('street_number', ''),
         streetName:  address.get('street_name', ''),
         city:  address.get('city', ''),
         stateCode:  address.get('state_code', ''),
         zipCode:  address.get('zip_code', ''),
         latitude:  address.get('latitude', ''),
         longitude:  address.get('longitude', ''),
         addressType:  clientAddress.get('address_tytpe_code', ''),
      }
    },
  rawState
)

export const selectAddressIds = (rawState) => createSelector(
  (state) => state.getIn(['entities', 'address'], Map())
    .toList()
    .map((address) => (address.get('identifier'))),
  rawState
)

export const selectPutAddressRequest = (rawState, addressId) => createSelector(
  (state) => state.getIn(['entities', 'address', addressId], Map()),
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
    selectOptionsByMetaName('STATE_C', 'user_defined_logical_id'),
    rawState
)

const selectOptionsByMetaName = (metaName, filedName = 'short_description') => (rawState) => createSelector(
  (state) => state.getIn(['entities', 'systemCode'], Map())
  .filter((systemCode) => systemCode.get('meta_name').includes(metaName))
  .toList()
  .map((systemCode) => Map({
    key: systemCode.get('system_id'),
    option: systemCode.get(filedName, '').trim()
  }))
  .sortBy((systemCode) => systemCode.get('key')) ,
  rawState
)
