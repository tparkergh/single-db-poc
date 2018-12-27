import jsonLogic from 'json-logic-js'
import Rule from './rule'

// singleton business rules engine
export default class JsonBRE {
  constructor () {
    if (JsonBRE.instance) { return JsonBRE.instance }
    this.rules = {}
    JsonBRE.instance = this
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
      .map((rule) => (this.rules[rule.identifier] = undefined))
  }

  find (predicate) {
    return Object.values(this.rules)
      .filter(predicate)
  }
}
