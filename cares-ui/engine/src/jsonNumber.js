export default class JsonNumber {
  constructor (definition) {
    this.value = definition
  }

  applies (selector) {
    return selector === this.value
  }
}
