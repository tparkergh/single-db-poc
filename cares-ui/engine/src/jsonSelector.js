import { tokenize } from './tokenizer'

export default class JsonSelector {
  constructor (definition) {
    this.value = definition
  }

  directlyApplies (selector) {
    return this.value.indexOf(selector) === 0
  }

  applies (selector) {
    return this.value === selector || tokenize(this.value).includes(selector) || this.directlyApplies(selector)
  }
}
