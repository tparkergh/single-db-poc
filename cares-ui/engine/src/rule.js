export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
  }

  applies (selector) {
    return this.find(this.definition, { "var": selector })
  }

  find (object, data) {
    const callback = (accumulator, current) => (accumulator || this.find(current, data))
    const values = Object.values(object).flat()
    if (JSON.stringify(object) === JSON.stringify(data)) { return true }
    if (values && values.length <= 1) { return false }
    if (values) { return values.reduce(callback.bind(this), false) }
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
