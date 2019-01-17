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

export const ReferralInformation = () => {
  return (
    <Card>
      <CardHeader>Referral Information</CardHeader>
      <CardBody>
        <Form autoComplete='off'>
          <FormGroup row>
            <Col>
              <Label>Approval Status</Label>
              <Input type='select' name='approval_status' id='approval_status' />
            </Col>
            <Col>
              <Label>Communication Entity</Label>
              <Input type='select' name='communication_entity' id='communication_entity' />
            </Col>
            <Col>
              <Label>Government Entity</Label>
              <Input type='select' name='government_entity' id='government_entity' />
            </Col>
          </FormGroup>
          <FormGroup row>
            <Col>
              <Label>Received Date</Label>
              <Input type='date' name='received_date' id='received_date' value='2015-09-03' disabled />
            </Col>
            <Col>
              <Label>Received Time</Label>
              <Input type='time' name='received_time' id='received_time' value='03:14' disabled />
            </Col>
          </FormGroup>
          <FormGroup row>
            <Col>
              <Label>Response Determination Date</Label>
              <Input type='date' name='response_determination_date' id='response_determination_date' value='2015-09-03' disabled />
            </Col>
            <Col>
              <Label>Response Determination Time</Label>
              <Input type='time' name='response_determination_time' id='response_determination_time' value='03:14' disabled />
            </Col>
          </FormGroup>
          <FormGroup row>
            <Col>
              <Label>Responsible Agency</Label>
              <Input type='select' name='responsible_agency' id='responsible_agency' />
            </Col>
            <Col>
              <Label>Referral Response</Label>
              <Input type='select' name='referral_response' id='referral_response' />
            </Col>
          </FormGroup>
        </Form>
      </CardBody>
    </Card>
  )
}
