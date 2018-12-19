import jsonLogic from 'json-logic-js'

const JsonBRE = {
  evaluate: (rule, data) => jsonLogic.apply(rule, data)
}

export default JsonBRE
