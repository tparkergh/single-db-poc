import React from 'react'
import { connect } from 'react-redux'
import axios from 'axios'

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

import { selectStateOptions, selectAddressTypeOptions, selectAddresses} from './selectors'
import { getSystemCodesRoute, getClientAddressesByClientIdRoute } from './routes'
import { addSystemCodes, setAddresses, saveAddress } from './actions'
import { ReferralInformationOptions } from './ReferralInformationOptions'

import '@cwds/core/dist/styles.css'

const METAS = [
  'STATE_C',
  'ADDR_TPC'
]

const getFullAddress = (address, stateOptions) => {
  return address.streetNumber + ' '
      + address.streetName + ', '
      + address.city + ", "
      + (stateOptions && stateOptions.find((o) => {return o.key == address.stateCode}).option) + ' '
      + address.zipCode;
}

export class Addresses extends React.Component {
  state = {
    currentIndex: -1,
  }

  componentDidMount () {
    METAS.map((meta) =>
      axios({
        url: getSystemCodesRoute(meta),
        method: 'get'
      }).then((response) => this.props.addSystemCodes(response.data))
    )
  }

  componentDidUpdate (prevProps) {
    if (this.props.clientId !== prevProps.clientId) {
      axios({
        url: getClientAddressesByClientIdRoute(this.props.clientId),
        method: 'get'
      }).then((response) => this.props.setAddresses(response.data))
    }
/*
    this.setState({
      addresses: this.props.addresses
    })
*/
  }

  render () {
    //<!--Input type='text' name='street' value={address.city.trim()} onChange={(event) => this.setState({city: event.target.value})}/-->

    const addresses = this.props.addresses
    //const addresses = this.state.addresses
    const stateOptions = this.props.stateOptions
    const addressTypeOptions = this.props.addressTypeOptions

    return (
      <div>
        <Label>Addresses</Label>
        <ListGroup>
          {
            addresses && addresses.map((address, index) =>
              <ListGroupItem>
                <div>
                  {getFullAddress(address, stateOptions)}
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
                      <Input type='text' name='street' defaultValue={ address.streetNumber + ' ' + address.streetName } />
                    </Col>
                    <Col>
                      <Label>City</Label>
                      <Input type='text' name='city' defaultValue={ address.city }/>
                    </Col>
                  </FormGroup>
                  <FormGroup row>
                    <Col>
                      <Label>State</Label>
                      <ReferralInformationOptions
                          name='state_code'
                          options={ stateOptions }
                          code={ address.stateCode }
                      />
                    </Col>
                    <Col>
                      <Label>Zip</Label>
                      <Input type='text' name='zip' defaultValue={ address.zipCode } />
                    </Col>
                    <Col>
                      <Label>Address Type</Label>
                      <ReferralInformationOptions
                          name='address_type'
                          options={ addressTypeOptions }
                          code={ address.addressType }
                      />
                    </Col>
                    <Col>
                      <Label>Latitude/Longitude</Label>
                      <Input type='text' name='latitudelongitude' value={ address.latitude + ', ' + address.longitude } />
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

const mapStateToProps = (state, ownProps) => ({
    addresses: selectAddresses(state),
    stateOptions: selectStateOptions(state),
    addressTypeOptions: selectAddressTypeOptions(state)
})

const mapDispatchToProps = { addSystemCodes, setAddresses, saveAddress }

export default connect(mapStateToProps, mapDispatchToProps) (Addresses)

