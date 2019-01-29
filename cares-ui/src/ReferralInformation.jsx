import { connect } from 'react-redux'
import {
  selectCurrentReferral,
  selectApprovalStatusOptions,
  selectCommunicationMethodOptions,
  selectGovernmentEntityOptions,
  selectResponsibleAgencyOptions,
  selectReferralResponseOptions
} from './selectors'
import React from 'react'
import axios from 'axios'
import { getSystemCodesRoute } from './routes'
import { addSystemCodes } from './actions'
import {
  Card,
  CardHeader,
  CardBody
} from '@cwds/components'

import {
  Form,
  FormGroup,
  Col,
  Input,
  Label
} from '@cwds/reactstrap'

import '@cwds/core/dist/styles.css'

const METAS = [
  'APV_STC',
  'CMM_MTHC',
  'GVR_ENTC',
  'AGN_RSPC',
  'RFR_RSPC'
]

class ReferralInformation extends React.Component {
  componentDidMount () {
    METAS.map((meta) =>
      axios({
        url: getSystemCodesRoute(meta),
        method: 'get'
      }).then((response) => this.props.addSystemCodes(response.data))
    )
  }

  render () {
    const {
      referral: {
        identifier: id,
        referral_name: name,
        approval_status_code: approvalStatusCode,
        communication_method_code: communicationMethodCode,
        government_entity_code: governmentEntityCode,
        responsible_agency_code: responsibleAgencyCode,
        response_determination_time: responseDeterminationTime,
        response_determination_date: responseDeterminationDate,
        response_type_code: responseTypeCode,
        received_date: receivedDate,
        received_time: receivedTime
      },
      approvalStatusOptions,
      communicationMethodOptions,
      governmentEntityOptions,
      responsibileAgencyOptions,
      referralResponseOptions
    } = this.props
    return (
      <div>
        <h2>Referral: { name }</h2>
        <Card>
          <CardHeader>Information</CardHeader>
          <CardBody>
            <Form autoComplete='off'>
              <FormGroup row>
                <Col>
                  <Label>Approval Status</Label>
                  <Input type='select' name='approval_status' id='approval_status'>
                    { approvalStatusOptions.map(({key, option}) =>
                      <option
                        key={key}
                        selected={ key === approvalStatusCode }
                      >{option}</option>) 
                    }
                  </Input>
                </Col>
                <Col>
                  <Label>Communication Method</Label>
                  <Input type='select' name='communication_entity' id='communication_entity'>
                    { communicationMethodOptions.map(({key, option}) =>
                      <option
                        key={key}
                        selected={ key === communicationMethodCode }
                      >{option}</option>) 
                    }
                  </Input>
                </Col>
                <Col>
                  <Label>Government Entity</Label>
                  <Input type='select' name='government_entity' id='government_entity'>
                    { governmentEntityOptions.map(({key, option}) =>
                      <option
                        key={key}
                        selected={ key === governmentEntityCode }
                      >{option}</option>) 
                    }
                  </Input>
                </Col>
              </FormGroup>
              <FormGroup row>
                <Col>
                  <Label>Received Date</Label>
                  <Input type='date' name='received_date' id='received_date' value={receivedDate} disabled />
                </Col>
                <Col>
                  <Label>Received Time</Label>
                  <Input type='time' name='received_time' id='received_time' value={receivedTime} disabled />
                </Col>
              </FormGroup>
              <FormGroup row>
                <Col>
                  <Label>Response Determination Date</Label>
                  <Input type='date' name='response_determination_date' id='response_determination_date' value={ responseDeterminationDate } disabled />
                </Col>
                <Col>
                  <Label>Response Determination Time</Label>
                  <Input type='time' name='response_determination_time' id='response_determination_time' value={ responseDeterminationTime } disabled />
                </Col>
              </FormGroup>
              <FormGroup row>
                <Col>
                  <Label>Responsible Agency</Label>
                  <Input type='select' name='responsible_agency' id='responsible_agency'>
                    { responsibileAgencyOptions.map(({key, option}) =>
                      <option
                        key={key}
                        selected={ key === responsibleAgencyCode }
                      >{option}</option>) 
                    }
                  </Input>
                </Col>
                <Col>
                  <Label>Referral Response</Label>
                  <Input type='select' name='referral_response' id='referral_response'>
                    { referralResponseOptions.map(({key, option}) =>
                      <option
                        key={key}
                        selected={ key === responseTypeCode }
                      >{option}</option>) 
                    }
                  </Input>
                </Col>
              </FormGroup>
            </Form>
          </CardBody>
        </Card>
      </div>
    )
  }
}

const mapDispatchToProps = { addSystemCodes }

const mapStateToProps = (state, ownProps) => {
  return ({
    referral: selectCurrentReferral(state),
    approvalStatusOptions: selectApprovalStatusOptions(state),
    communicationMethodOptions: selectCommunicationMethodOptions(state),
    governmentEntityOptions: selectGovernmentEntityOptions(state),
    responsibileAgencyOptions: selectResponsibleAgencyOptions(state),
    referralResponseOptions: selectReferralResponseOptions(state)
  })
}

export default connect(mapStateToProps, mapDispatchToProps)(ReferralInformation)
