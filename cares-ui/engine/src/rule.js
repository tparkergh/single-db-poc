import JsonEntity from './jsonEntity'
export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
    this.entity = new JsonEntity(definition)
  }

  applies (selector) {
    return this.entity.applies(selector)
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
