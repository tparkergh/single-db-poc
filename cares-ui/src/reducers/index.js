import {
  referrals,
  systemMetas,
  systemCodes,
  client
} from '../schemas'
import {
  SET_REFERRALS,
  SET_CLIENT,
  SET_SYSTEM_METAS,
  SET_SYSTEM_CODES,
  OPEN_REFERRAL
} from '../actions'
import { normalize } from 'normalizr'

export default (state = {}, action) => {
  console.log('store state: ', state)
  switch (action.type) {
    case SET_REFERRALS:
      return {
        ...state,
        entities: {
          ...state.entities,
          ...normalize(action.payload, referrals).entities
        }
      }
    case SET_CLIENT:
      return {
        ...state,
        entities: {
          ...state.entities,
          ...normalize(action.payload, client).entities
        }
      }
    case SET_SYSTEM_METAS:
      return {
        ...state,
        entities: {
          ...state.entities,
          ...normalize(action.payload, systemMetas).entities
        }
      }
    case SET_SYSTEM_CODES:
      const sysCodes = state.entities && state.entities.systemCode || {}
      return {
        ...state,
        entities: {
          ...state.entities,
          systemCode: {
            ...sysCodes,
            ...normalize(action.payload, systemCodes).entities.systemCode
          }
        }
      }
    case OPEN_REFERRAL:
      return {
        ...state,
        openReferralId: action.id,
      }
    default:
      return state
  }
}
