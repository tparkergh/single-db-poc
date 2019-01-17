import { tokenize } from './tokenizer'

export default class StringOperator {
  constructor (definition) {
    this.value = definition
  }

  applies(selector) {
    return this.value === selector || tokenize(this.value).includes(selector)
  }
}
