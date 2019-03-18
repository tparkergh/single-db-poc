import { Model } from "survey-react";
import JsonBRE from 'JsonBRE'
import marked from 'marked'

export default class BaseModel extends Model {
  RULE_PREFIX = ""

  constructor(json) {
    super(json)
    this.showQuestionNumbers = "off"
    this.engine = new JsonBRE()

    this.onErrorCustomText.add((survey, options) => {
      if (options.name === "required") {
        options.text = "Required"
      }
    })
    //this.showCompletedPage=false
    this.onCompleting.add((result, options) => {
      options.allowComplete=false
    })
    this.onTextMarkdown.add((survey, options) => {
      marked.setOptions({
        breaks: true
      })
      options.html = marked(options.text)
    })
    this.onValidateQuestion.add(this.validateRules.bind(this))
  }

  validateRules(sender, options) {
    const ruleName = `${this.RULE_PREFIX}${options.name}`
    const rules = this.engine.find((rule) => rule.applies(ruleName))
    if (rules)
    {
      const results = rules.map((rule) => this.engine.evaluate(rule, this.validationData()))
      const errors = results.filter((result) => result !== true)
      options.error = errors.join("\n") || undefined
    }
  }

  update () {
  }
  loadJsonRules () {}
  validationData() {
    return {}
  }
}

