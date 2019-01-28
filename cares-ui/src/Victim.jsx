import {
  Card,
  CardHeader,
  CardBody
} from '@cwds/components'
import {
  Form,
  FormGroup,
  Input,
  Col,
  Label
} from '@cwds/reactstrap'

import { Addresses } from './Addresses'
import { connect } from 'react-redux'
import React from 'react'
import axios from 'axios'
import { getClientByIdRoute } from './routes'
import { setClient } from './actions'
import {
  selectOpenClientId,
  selectOpenClient
} from './selectors'

import '@cwds/core/dist/styles.css'

class Victim extends React.Component {
  componentDidUpdate (prevProps) {
    if (this.props.openClientId !== prevProps.openClientId) {
      axios({
        url: getClientByIdRoute(this.props.openClientId),
        method: 'get'
      }).then((response) => this.props.setClient(response.data))
    }
  }

  render () {
    const {
      client: {
        common_first_name: firstName,
        common_middle_name: middleName,
        common_last_name: lastName,
        birth_date: birthDate
      }
    } = this.props

    return (
      <Card>
        <CardHeader>
        Victim: {firstName} {lastName}
        </CardHeader>
        <CardBody>
          <Form autoComplete='off'>
            <FormGroup row className='name'>
              <Col>
                <Label>First Name</Label>
                <Input type='text' name='first_name' id='first_name' value={firstName} disabled />
              </Col>
              <Col>
                <Label>Middle Name</Label>
                <Input type='text' name='middle_name' id='middle_name' value={middleName} disabled />
              </Col>
              <Col>
                <Label>Last Name</Label>
                <Input type='text' name='last_name' id='last_name' value={lastName} disabled />
              </Col>
            </FormGroup>
            <FormGroup row className='date_of_birth'>
              <Col md={4}>
                <Label>Date of Birth</Label>
                <Input type='date' name='date_of_birth' id='date_of_birth' value={birthDate} disabled />
              </Col>
            </FormGroup>
            <Addresses />
          </Form>
        </CardBody>
      </Card>
    )
  }
}

const mapStateToProps = (state, ownProps) => ({
    openClientId: selectOpenClientId(state),
    client: selectOpenClient(state)
})

const mapDispatchToProps = { setClient }

export default connect(mapStateToProps, mapDispatchToProps)(Victim)
