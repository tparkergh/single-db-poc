import JsonMap from './jsonMap'
import JsonEntity from './jsonEntity'
import { ARRAY_OPERATIONS } from './operations'

export default class JsonObject {
  constructor (definition) {
    const operator = Object.keys(definition)[0]
    this.value = definition[operator]

    if (ARRAY_OPERATIONS.includes(operator)) {
      return new JsonMap(this.value)
    }
    return new JsonEntity(this.value)
  }
}
