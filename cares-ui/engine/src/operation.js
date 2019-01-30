import ArrayOperation from './arrayOperation'
import StringOperation from './stringOperation'
import { tokenize } from './tokenizer'
export default class Operation {
  constructor (definition) {
    if (typeof(definition) === 'string') {
      return new StringOperation(definition)
    }
    // otherwise this is a true operation
    const operator = Object.keys(definition)[0]
    this.value = definition[operator]

    switch (operator) {
      case '<=':
        return new ArrayOperation(this.value)
      case '>=':
        return new ArrayOperation(this.value)
      case 'missing':
        return new ArrayOperation(this.value)
      default:
        return this
    }
  }

  applies (selector) {
    const operation = new Operation(this.value)
    return operation.applies(selector)
  }
}
