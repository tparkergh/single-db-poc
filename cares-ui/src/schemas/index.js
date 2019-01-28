import { schema } from 'normalizr'

export const allegation = new schema.Entity('allegation', {}, {
  idAttribute: 'identifier'
})

export const referral = new schema.Entity('referral', {
  allegations: [allegation]
}, {
  idAttribute: 'identifier'
})

export const referrals = new schema.Array(referral)

export const systemCode = new schema.Entity('systemCode', {}, {
  idAttribute: 'meta_name'
})

export const systemCodes = new schema.Array(systemCode)

export const client = new schema.Entity('client', {}, {
  idAttribute: 'identifier'
})
