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

import '@cwds/core/dist/styles.css'

export const Victim = () => {
  return (
    <Card>
      <CardHeader>
      Victim: Joe Smoe
      </CardHeader>
      <CardBody>
        <Form autoComplete='off'>
          <FormGroup row className='name'>
            <Col>
              <Label>First Name</Label>
              <Input type='text' name='first_name' id='first_name' value='Joe' disabled />
            </Col>
            <Col>
              <Label>Middle Name</Label>
              <Input type='text' name='middle_name' id='middle_name' disabled />
            </Col>
            <Col>
              <Label>Last Name</Label>
              <Input type='text' name='last_name' id='last_name' value='Smoe' disabled />
            </Col>
          </FormGroup>
          <FormGroup row className='date_of_birth'>
            <Col md={4}>
              <Label>Date of Birth</Label>
              <Input type='date' name='date_of_birth' id='date_of_birth' value='2015-09-03' disabled />
            </Col>
          </FormGroup>
          <Addresses />
        </Form>
      </CardBody>
    </Card>
  )
}
