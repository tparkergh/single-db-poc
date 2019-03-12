import JsonSelectorArray from './jsonSelectorArray'

export default class JsonSome {
  constructor (definition) {
    const selectorArray = definition[1]
    this.value = new JsonSelectorArray(selectorArray)
  }

  applies (selector) {
    return this.value.applies(selector)
  }
}
