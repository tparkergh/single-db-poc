import Operator from './operator'
export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
    this.operator = new Operator(definition)
  }

  applies (selector) {
    return this.operator.applies(selector)
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
