import ArrayOperation from './arrayOperation'
import JsonEntity from './jsonEntity'

export default class JsonOperation {
  constructor (definition) {
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
    const operation = new JsonEntity(this.value)
    return operation.applies(selector)
  }
}
