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
  Label,
  Button
} from '@cwds/reactstrap'

import { Validation } from './Validation'
import React from 'react'
import JsonBRE from 'JsonBRE'

import '@cwds/core/dist/styles.css'

export class Main extends React.Component {
  state = {
    firstName: '',
    lastName: ''
  }

  render () {
    return (
      <Card>
        <CardHeader>
        Joe Smoe
        </CardHeader>
        <CardBody>
          <Form autoComplete='off'>
            <FormGroup row className='name'>
              <Col className='firstName'>
                <Label>First Name</Label>
                <Input
                  type='text'
                  name='first_name'
                  id='first_name'
                  onBlur={(event) => this.setState({ firstName: event.target.value })}
                />
                <Validation
                  rules={JsonBRE.find((rule) => rule.applies('firstName'))}
                  data={{ firstName: this.state.firstName }}
                />
              </Col>
              <Col>
                <Label>Middle Name</Label>
                <Input type='text' name='middle_name' id='middle_name' />
              </Col>
              <Col className='lastName'>
                <Label>Last Name</Label>
                <Input
                  type='text'
                  name='last_name'
                  id='last_name'
                  onBlur={(event) => this.setState({ lastName: event.target.value })}
                />
                <Validation
                  rules={JsonBRE.find((rule) => rule.applies('lastName'))}
                  data={{ lastName: this.state.lastName }}
                />
              </Col>
            </FormGroup>
            <FormGroup row className='date_of_birth'>
              <Col md={4}>
                <Label>Date of Birth</Label>
                <Input type='date' name='date_of_birth' id='date_of_birth' value='2015-09-03' />
              </Col>
            </FormGroup>
            <FormGroup check row>
              <Button className='save float-right' color='primary'>Save</Button>
              <Button className='cancel float-right' color='secondary'>Cancel</Button>
            </FormGroup>
          </Form>
        </CardBody>
      </Card>
    )
  }
}
