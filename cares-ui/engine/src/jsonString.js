export default class JsonString {
  constructor (definition) {
    this.value = definition
  }

  directlyApplies (string) {
    return this.value === string
  }

  applies (string) {
    return this.value.includes(string)
  }
}
