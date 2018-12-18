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

import '@cwds/core/dist/styles.css'

export const Main = () => {
  return (
    <Card>
      <CardHeader>
      Joe Smoe
      </CardHeader>
      <CardBody>
        <Form autoComplete='off'>
          <FormGroup row className='name'>
            <Col>
              <Label>First Name</Label>
              <Input type='text' name='first_name' id='first_name' value='Joe' />
            </Col>
            <Col>
              <Label>Middle Name</Label>
              <Input type='text' name='middle_name' id='middle_name' />
            </Col>
            <Col>
              <Label>Last Name</Label>
              <Input type='text' name='last_name' id='last_name' value='Smoe' />
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
