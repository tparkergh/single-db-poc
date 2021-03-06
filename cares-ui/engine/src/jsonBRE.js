import jsonLogic from 'json-logic-js'
import Rule from './rule'
import { length } from './customOperators'

// singleton business rules engine
export default class JsonBRE {
  constructor () {
    this.rules = {}
    jsonLogic.add_operation('length', length)
  }

  evaluate (rule, data) {
    return jsonLogic.apply(rule.definition, data)
  }

  define ({ identifier, definition }) {
    const rule = new Rule({ identifier, definition })
    this.rules[identifier] = rule
    return rule
  }

  destroy (predicate) {
    Object.values(this.rules)
      .filter(predicate)
      .map((rule) => (delete this.rules[rule.identifier]))
  }

  reset () {
    this.destroy((rule) => true)
  }

  find (predicate) {
    return Object.values(this.rules)
      .filter(predicate)
  }

  empty () {
    return Object.values(this.rules).length === 0
  }
}
