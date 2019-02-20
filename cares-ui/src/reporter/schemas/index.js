import { schema } from 'normalizr'

export const person = new schema.Entity('person', {}, {
  idAttribute: 'identifier'
})

export const hit = new schema.Object({person})

export const searchResults = new schema.Array(hit)
