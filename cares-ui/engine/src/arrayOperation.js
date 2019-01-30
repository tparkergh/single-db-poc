import Operation from './operation'

export default class ArrayOperation {
  constructor (values) {
    this.values = values.map((value) => new Operation(value))
  }

  applies (selector) {
    return this.values.reduce((accumulator, current) => accumulator || current.applies(selector), false)
  }
}
