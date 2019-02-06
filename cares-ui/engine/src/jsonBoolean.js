export default class JsonBoolean {
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
