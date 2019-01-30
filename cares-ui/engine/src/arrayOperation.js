import Operation from './operation'

export default class ArrayOperation {
  constructor (values) {
    this.operations = values.map((value) => new Operation(value))
  }

  applies (selector) {
    return this.operations.some((operation) => operation.applies(selector))
  }
}
