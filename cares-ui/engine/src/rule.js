export default class Rule {
  constructor ({ identifier, definition }) {
    this.identifier = identifier
    this.definition = definition
  }

  applies (selector) {
    return this.find(this.definition, selector)
  }

  find (object, data) {
    const callback = (accumulator, current) => (accumulator || this.find(current, data))
    const values = Object.values(object).flat()
    const stringifiedObject = JSON.stringify(object)
    const stringifiedData = JSON.stringify(data)
    if (stringifiedObject === stringifiedData) { return true }
    if (stringifiedObject.length <= 3) { return false }
    if (values) { return values.reduce(callback.bind(this), false) }
  }

  render () {
    return JSON.stringify(this.definition)
  }
}
