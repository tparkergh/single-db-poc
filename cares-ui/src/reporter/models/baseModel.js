import { Model } from "survey-react";
import marked from 'marked'

export default class BaseModel extends Model {
  constructor(json) {
    super(json)

    this.onErrorCustomText.add((survey, options) => {
      if (options.name === "required") {
        options.text = "Required"
      }
    })
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
}

