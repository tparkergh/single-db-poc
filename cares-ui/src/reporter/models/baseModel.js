import { Model } from "survey-react";
import JsonBRE from 'JsonBRE'
import marked from 'marked'

export default class BaseModel extends Model {
  constructor(json) {
    super(json)
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
  }
  update () {
  }
  loadJsonRules () {}
}

