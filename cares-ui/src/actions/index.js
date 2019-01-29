export const SET_REFERRALS = 'SET_REFERRALS'
export const SET_CLIENT = 'SET_CLIENTS'
export const OPEN_REFERRAL = 'OPEN_REFERRAL'
export const SET_SYSTEM_METAS = 'SET_SYSTEM_METAS'
export const SET_SYSTEM_CODES = 'SET_SYSTEM_CODES'

export const setReferrals = payload => ({
  type: SET_REFERRALS,
  payload
})

export const setClient = payload => ({
  type: SET_CLIENT,
  payload
})

export const setSystemMetas = payload => ({
  type: SET_SYSTEM_METAS,
  payload
})

export const setSystemCodes = payload => ({
  type: SET_SYSTEM_CODES,
  payload
})

export const openReferral = (id) => ({
  type: OPEN_REFERRAL,
  id
})
