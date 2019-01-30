import JsonEntity from './jsonEntity'

export default class ArrayOperation {
  constructor (values) {
    this.entities = values.map((value) => new JsonEntity(value))
  }

  applies (selector) {
    return this.entities.some((entity) => entity.applies(selector))
  }
}
