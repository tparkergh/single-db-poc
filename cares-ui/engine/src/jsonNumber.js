export default class JsonNumber {
  constructor (definition) {
    this.value = definition
  }

  directlyApplies (selector) {
    return selector === this.value
  }

  applies (selector) {
    return selector === this.value
  }
}
