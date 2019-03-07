import JsonMap from './jsonMap'
import JsonEntity from './jsonEntity'
import JsonSelector from './jsonSelector'
import JsonSelectorArray from './jsonSelectorArray'
import JsonSome from './jsonSome'
import {
  ARRAY_OPERATIONS,
  DATA_OPERATIONS
} from './operations'

export default class JsonObject {
  constructor (definition) {
    const operator = Object.keys(definition)[0]
    this.value = definition[operator]

    if (ARRAY_OPERATIONS.includes(operator)) {
      return new JsonMap(this.value)
    }
    if (operator === 'missing') {
      return new JsonSelectorArray(this.value)
    }
    if (operator === 'missing_some') {
      return new JsonSome(this.value)
    }
    if (DATA_OPERATIONS.includes(operator)) {
      return new JsonSelector(this.value)
    }
    return new JsonEntity(this.value)
  }
}
