import JsonEntity from './jsonEntity'
import { tuplize } from './tokenizer'

export default class JsonMap {
  constructor (value) {
    this.accessor = new JsonEntity(value[0])
    this.mapper = new JsonEntity(value[1])
  }

  directlyApplies (selector) {
    return this.applies(selector)
  }

  applies (selector) {
    const tuples = tuplize(selector)
    const subMatch = tuples.some((tuple) => this.accessor.applies(tuple[0]) && this.mapper.directlyApplies(tuple[1]))
    const directMatch = this.accessor.applies(selector) || this.mapper.directlyApplies(selector)
    return subMatch || directMatch
  }
}
