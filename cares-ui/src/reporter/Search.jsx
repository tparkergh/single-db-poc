import { Component } from  'react'
import {Survey, Model } from 'survey-react'
import 'survey-react/survey.css'

export default class Search extends Component {
  json = {
    // checkErrorsMode: 'onComplete',
    checkErrorsMode: 'onValueChanged',
    completeText: 'Continue',
    questionErrorLocation: 'bottom',
    elements: [

      { type: 'text', name: 'first_name', title: 'First Name', isRequired: true, text: 'Enter first name' },
      { type: 'text', name: 'last_name', title: 'Last Name', isRequired: true, startWithNewLine: false },

      {
        type: 'text',
        name: 'number',
        title: 'Phone Number',
        width: '30%'
      },
      {
        type: 'text',
        name: 'extension',
        title: 'Ext.',
        width: '20%',
        startWithNewLine: false
      },
      {
        type: 'dropdown',
        name: 'relationship',
        title: 'Relationship to Child',
        choices: ['Reporter'],
        startWithNewLine: false
      }
    ],
    completeSurveyText: 'Save'
  };

  constructor(props) {
    super(props)

    this.model = new Model(this.json)
    this.model.onErrorCustomText.add(this.onErrorCustomText)

  }

  onComplete (survey, options) {
    // Write survey results into database
    console.log('Survey results: ' + JSON.stringify(survey.data))
  }

  onErrorCustomText (sender, options) {
    if (options.name === 'required') {
      options.text = 'Required'
    }
  }

  render () {
    
    return(
      <Survey
        model={this.model}
        onComplete={this.onComplete}
      />
    ) 
  }
}