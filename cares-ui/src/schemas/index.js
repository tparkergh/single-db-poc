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

export const systemMeta = new schema.Entity('systemMeta', {}, {
  idAttribute: 'meta_name'
})

export const systemMetas = new schema.Array(systemMeta)

export const systemCode = new schema.Entity('systemCode', {}, {
  idAttribute: 'system_id'
})

export const systemCodes = new schema.Array(systemCode)

export const client = new schema.Entity('client', {}, {
  idAttribute: 'identifier'
})

export const address = new schema.Entity('address', {}, {
  idAttribute: 'identifier'
})

export const clientAddress = new schema.Entity('clientAddress', {address: address}, {
  idAttribute: 'identifier'
})

export const clientAddresses = new schema.Array(clientAddress)
