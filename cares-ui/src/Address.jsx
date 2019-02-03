import React from 'react'
import { connect } from 'react-redux'
import axios from 'axios'

import {
  FormGroup,
  Input,
  Col,
  Label,
  Collapse,
  Button
} from '@cwds/reactstrap'

import { selectStateOptions, selectAddressTypeOptions, selectAddress} from './selectors'
import { setAddresses } from './actions'
import { ReferralInformationOptions } from './ReferralInformationOptions'

import '@cwds/core/dist/styles.css'

const getFullAddress = (address, stateOptions) => {
  return address.streetNumber + ' '
      + address.streetName + ', '
      + address.city + ", "
      + (address.stateCode && stateOptions && stateOptions.find((o) => {return o.key == address.stateCode}).option) + ' '
      + address.zipCode;
}

export class Address extends React.Component {
  state = {
    isOpen: false
  }

  render() {
    const address = this.props.address
    if (address) {
      console.log('address', address)
    }
    const stateOptions = this.props.stateOptions
    const addressTypeOptions = this.props.addressTypeOptions

    return (
        <div>
          <div>
            {getFullAddress(address, stateOptions)}
            <Button
                color='link'
                className='float-right'
                onClick={
              ({ target }) => this.setState({ isOpen: true })
            }
            >Edit</Button>
          </div>
          <Collapse isOpen={this.state.isOpen}>
            <br />
            <FormGroup row>
              <Col>
                <Label>Address</Label>
                <Input id={'street-' + this.props.id} type='text' name='street'
                       value={ address.streetNumber + ' ' + address.streetName }/>
              </Col>
              <Col>
                <Label>City</Label>
                <Input id={'city-' + this.props.id} type='text' name='city'
                       value={ address.city } disabled='false' onChange='() => {}'/>
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
                <Input id={'zip-' + this.props.id} type='text' name='zip'
                       value={ address.zipCode }/>
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
                <Input type='text' name='latitudelongitude'
                       value={ address.latitude + ', ' + address.longitude }/>
              </Col>
            </FormGroup>
            <FormGroup check row>
              <Button className='save float-right' color='primary'>Save</Button>
              <Button className='cancel float-right' color='secondary'
                onClick={
                  ({ target }) => {
                    this.setState({ isOpen: false })
                  }
                }
              >Cancel</Button>
            </FormGroup>
          </Collapse>
        </div>
    )
  }
}

const mapStateToProps = (state, props) => ({
    address: selectAddress(state, props.id),
    stateOptions: selectStateOptions(state),
    addressTypeOptions: selectAddressTypeOptions(state)
})

const mapDispatchToProps = {}

export default connect(mapStateToProps, mapDispatchToProps)(Address)


