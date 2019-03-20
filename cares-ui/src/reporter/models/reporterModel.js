import BaseModel from './baseModel'
import ReporterJSON from "../jsonForms/reporter"
import axios from 'axios'
import {
  createReporterRoute,
  getBreRuleSetRoute,
  stateSystemCodesRoute
} from '../../routes'

export default class ReporterModel extends BaseModel {
  RULE_PREFIX="create.reporter."

  constructor(props) {
    super(ReporterJSON)
    this.props = props

    this.onAfterRenderQuestion.add((sender, options) => {
      this.update(this.props)
    })
    this.onCompleting.add(this.createReporter.bind(this))

    this.loadStates()

    // hook for demoing dynamically adding rules
    window.rules = this.engine.rules
    window.addRule = this.engine.define
    window.removeRule = (id) => this.engine.destroy((rule) => rule.identifier === id)
  }

  update(props) {
    this.data = props.data
    this.props = props
  }

  loadStates () {
    let response 
    axios({
      url: stateSystemCodesRoute(),
      method: 'get'
    }).then((result) => {
      response = result.data.map(r => ({
          value: r.system_id,
          text: r.short_description.trim()
        })
      )
      this.getQuestionByName('state').choices = response 
    })
  }

  createReporter (result, options) {
    const {
      updateSearchModel,
      updateReporterModel,
      createReporterSuccess,
      createReporterError
    } = this.props
    return axios({
      url: createReporterRoute(),
      method: 'post',
      data: this.buildReporter(result.data)
    })
      .then((result) => {
        updateReporterModel && updateReporterModel({
          active: false,
          data: this.data
        })
        updateSearchModel && updateSearchModel({ active: true })
        createReporterSuccess && createReporterSuccess()
      })
      .catch((error) => {
        createReporterError && createReporterError(error)
      })
  }

  buildReporter ({
    first_name,
    last_name,
    phone_number,
    relationship,
    employer,
    title,
    address,
    city,
    zip_code,
    state
  }) {
    const addressRegex = address && address.match(/^\s*(\d+)\s*(.*)/) || []
    var street_number = addressRegex[1] || ""
    var street_name = addressRegex[2] || ""
    return {
      first_name,
      last_name,
      primary_phone_number: parseInt(phone_number),
      relation_to_child: relationship,
      employer_name: employer,
      title_description: title,
      address: {
        street_name,
        street_number,
        city,
        zip_code,
      }
    }
  }

  validationData() {
    return {
      create: {
        reporter: {
          ...this.data
        }
      }
    }
  }


  loadJsonRules() {
    const { createReporterError } = this.props
    return axios({
      url: getBreRuleSetRoute('ReporterCreateScreenBusinessRules'),
      method: 'get',
    }).then((result) => {
      result.data.rules.map((rule) =>
        this.engine.define({
          identifier: rule.name,
          definition: rule.logic
        })
      )
    }).catch((error) => {
      createReporterError && createReporterError(error)
    })
  }
}
