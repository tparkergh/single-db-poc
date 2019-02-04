export default class JsonString {
  constructor (definition) {
    this.value = definition
  }

  directlyApplies (selector) {
    return false
  }

  applies (selector) {
    return false
  }
}
