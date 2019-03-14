import BaseModel from './baseModel'
import ReporterJSON from "../jsonForms/reporter"
import axios from 'axios'
import {
  createReporterRoute
} from '../../routes'
import {
  getBreRuleSetRoute
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
  }

  update(props) {
    this.data = props.data
    this.props = props
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
    relationship
  }) {
    return {
      first_name,
      last_name,
      phone_number: parseInt(phone_number),
      relation_to_child: relationship
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

