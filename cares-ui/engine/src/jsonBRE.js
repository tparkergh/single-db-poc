import jsonLogic from 'json-logic-js'
import Rule from './rule'

// singleton business rules engine
class JsonBRE {
  constructor () {
    this.rules = {}
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

const instance = new JsonBRE()

Object.freeze(instance)

export default instance
