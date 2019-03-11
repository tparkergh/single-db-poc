import JsonSelector from './jsonSelector'

export default class JsonSelectorArray {
  constructor (values) {
    this.entities = values.map((value) => new JsonSelector(value))
  }

  applies (selector) {
    return this.entities.some((entity) => entity.applies(selector))
  }
}
