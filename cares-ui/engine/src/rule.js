import JsonOperation from './jsonOperation'
export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
    this.operation = new JsonOperation(definition)
  }

  applies (selector) {
    return this.operation.applies(selector)
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
