import Operation from './operation'
export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
    this.operation = new Operation(definition)
  }

  applies (selector) {
    return this.operation.applies(selector)
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
