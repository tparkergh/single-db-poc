import ArrayOperator from './arrayOperator'
import StringOperator from './stringOperator'
import { tokenize } from './tokenizer'
export default class Operator {
  constructor (definition) {
    if (typeof(definition) === 'string') {
      return new StringOperator(definition)
    }
    // otherwise this is a true operation
    const operator = Object.keys(definition)[0]
    this.value = definition[operator]

    switch (operator) {
      case '<=':
        return new ArrayOperator(this.value)
      case '>=':
        return new ArrayOperator(this.value)
      case 'missing':
        return new ArrayOperator(this.value)
      default:
        return this
    }
  }

  applies (selector) {
    const operation = new Operator(this.value)
    return operation.applies(selector)
  }
}
