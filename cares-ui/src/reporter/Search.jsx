import { Component } from "react";
import { Survey, Model, StylesManager } from "survey-react";
// import * as Survey  from 'survey-react'
import "survey-react/survey.css";

export default class Search extends Component {
  json = {
    // checkErrorsMode: 'onComplete',
    checkErrorsMode: "onValueChanged",
    completeText: "Continue",
    questionErrorLocation: "bottom",
    elements: [
      {
        type: "text",
        name: "first_name",
        title: "First Name",
        text: "Enter first name",
        validators: [
          {
            type: 'expression',
            expression: '{first_name} notempty or {last_name} notempty',
            text: 'First Or Last Name is required'
          }
        ]
      },
      {
        type: "text",
        name: "last_name",
        title: "Last Name",
        startWithNewLine: false,
        validators: [
          {
            type: 'expression',
            expression: '{first_name} notempty or {last_name} notempty',
            text: 'First Or Last Name is required'
          }
        ]
      },

      {
        type: "text",
        name: "number",
        isRequired: true,
        title: "Phone Number",
        width: "30%"
      },
      {
        type: "text",
        name: "extension",
        title: "Ext.",
        width: "20%",
        startWithNewLine: false
      },
      {
        type: "dropdown",
        name: "relationship",
        title: "Relationship to Child",
        optionsCaption: 'Reporter',
        defaultValue: 'Reporter',
        startWithNewLine: false
      }
    ],
    completeSurveyText: "Save"
  };

  constructor(props) {
    super(props);

    this.model = new Model(this.json);
    this.model.onErrorCustomText.add((survey, options) => {
      if (options.name === "required") {
        options.text = "Required";
      }
    });
  }

  onComplete(survey, options) {
    // Write survey results into database
    console.log("Survey results: " + JSON.stringify(survey.data));
  }

  render() {
    // StylesManager.applyTheme('bootstrap')
    // Survey.cssType = 'bootstrap'

    return <Survey model={this.model} onComplete={this.onComplete} />;
  }
}
