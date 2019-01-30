import JsonString from './jsonString'
import JsonNumber from './jsonNumber'
import JsonOperation from './jsonOperation'

export default class JsonEntity {
  constructor (definition) {
    this.entity = typeof definition
    switch (this.entity) {
      case 'string':
        return new JsonString(definition)
      case 'number':
        return new JsonNumber(definition)
      case 'object':
        return new JsonOperation(definition)
      default:
        return this
    }
  }

  applies (selector) {
    throw new Error(`JsonBRE: '${this.entity}' Entity is not supported`)
  }
}
