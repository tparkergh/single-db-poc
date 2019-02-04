import JsonEntity from './jsonEntity'

export default class JsonArray {
  constructor (values) {
    this.entities = values.map((value) => new JsonEntity(value))
  }

  directlyApplies (selector) {
    return this.entities.some((entity) => entity.directlyApplies(selector))
  }

  applies (selector) {
    return this.entities.some((entity) => entity.applies(selector))
  }
}
