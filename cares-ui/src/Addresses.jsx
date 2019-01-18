import React from 'react'
import {
  FormGroup,
  Input,
  Col,
  Label,
  ListGroup,
  ListGroupItem,
  Collapse,
  Button
} from '@cwds/reactstrap'

import '@cwds/core/dist/styles.css'

export class Addresses extends React.Component {
  state = {
    currentIndex: -1
  }
  render () {
    const addresses = [1, 2]
    return (
      <div>
        <Label>Addresses</Label>
        <ListGroup>
          {
            addresses.map((address, index) =>
              <ListGroupItem>
                <div>
                  First St First City, CA 94716
                  <Button
                    color='link'
                    className='float-right'
                    onClick={
                      ({ target }) => this.setState({ currentIndex: index })
                    }
                  >Edit</Button>
                </div>
                <Collapse isOpen={index === this.state.currentIndex}>
                  <br />
                  <FormGroup row>
                    <Col>
                      <Label>Address</Label>
                      <Input type='text' name='street' value='First St' />
                    </Col>
                    <Col>
                      <Label>City</Label>
                      <Input type='text' name='street' value='First City' />
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Col>
                      <Label>State</Label>
                      <Input type='text' name='street' value='CA' />
                    </Col>
                    <Col>
                      <Label>Zip</Label>
                      <Input type='text' name='street' value='94716' />
                    </Col>
                    <Col>
                      <Label>Address Type</Label>
                      <Input type='text' name='street' value='Home' />
                    </Col>
                    <Col>
                      <Label>Latitude/Longitude</Label>
                      <Input type='text' name='latitudelongitude' value='102.756,110.295' />
                    </Col>
                  </FormGroup>
                  <FormGroup check row>
                    <Button className='save float-right' color='primary'>Save</Button>
                    <Button className='cancel float-right' color='secondary'
                      onClick={
                        ({ target }) => this.setState({ currentIndex: -1 })
                      }
                    >Cancel</Button>
                  </FormGroup>
                </Collapse>
              </ListGroupItem>
            )
          }
        </ListGroup>
      </div>
    )
  }
}
