import { GET_REFERRALS } from '../actions'
export const referrals = (state = [], action) => {
  switch (action.type) {
    case GET_REFERRALS:
      return state
    default:
      return state
  }
}
