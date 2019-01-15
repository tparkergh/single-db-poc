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
                  address {index + 1}
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
                      <Input type='text' name='street' value='First St' disabled />
                    </Col>
                    <Col>
                      <Label>City</Label>
                      <Input type='text' name='street' value='First St' disabled />
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Col>
                      <Label>State</Label>
                      <Input type='text' name='street' value='First St' disabled />
                    </Col>
                    <Col>
                      <Label>Zip</Label>
                      <Input type='text' name='street' value='First St' disabled />
                    </Col>
                    <Col>
                      <Label>Address Type</Label>
                      <Input type='text' name='street' value='First St' disabled />
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
