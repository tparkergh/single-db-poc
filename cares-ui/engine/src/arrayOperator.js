import Operator from './operator'
import { tokenize } from './tokenizer'

export default class ArrayOperator {
  constructor (values) {
    this.values = values.map((value) => new Operator(value))
  }

  applies (selector) {
    return this.values.reduce((accumulator, current) => accumulator || current.applies(selector), false)
  }
}
