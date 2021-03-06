import JsonString from './jsonString'
import JsonArray from './jsonArray'
import JsonNumber from './jsonNumber'
import JsonObject from './jsonObject'
import JsonBoolean from './jsonBoolean'
import { type } from './helpers'

export default class JsonEntity {
  constructor (definition) {
    this.entity = type(definition)
    switch (type(definition)) {
      case 'string':
        return new JsonString(definition)
      case 'number':
        return new JsonNumber(definition)
      case 'array':
        return new JsonArray(definition)
      case 'object':
        return new JsonObject(definition)
      case 'boolean':
        return new JsonBoolean(definition)
      default:
        return this
    }
  }

  directlyApplies (selector) {
    throw new Error(`JsonBRE: '${this.entity}' Entity is not supported`)
  }

  applies (selector) {
    throw new Error(`JsonBRE: '${this.entity}' Entity is not supported`)
  }
}
