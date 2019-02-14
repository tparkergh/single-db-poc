import marked from 'marked'
import { Model } from "survey-react";

export const setupModel = (json) => {
  const model = new Model(json)
  model.onErrorCustomText.add((survey, options) => {
    if (options.name === "required") {
      options.text = "Required"
    }
  })
  model.onComplete.add((result) => {
    console.log("Survey results: " + JSON.stringify(model.data))
  })
  model.onTextMarkdown.add((survey, options) => {
    marked.setOptions({
      breaks: true
    })
    options.html = marked(options.text)
  })
  return model
}
