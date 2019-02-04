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
import { updateAddress } from './actions'
import { DictionaryOptions } from './DictionaryOptions'

import '@cwds/core/dist/styles.css'

const getFullAddress = (address, stateOptions) => {
  return address.streetNumber + ' '
      + address.streetName + ', '
      + address.city + ", "
      + (address.stateCode && stateOptions && stateOptions.find((o) => {return o.key == address.stateCode}).option) + ' '
      + address.zipCode;
}

export class Address extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      isOpen: false
    }

    this.onChangeAddress = this.onChangeAddress.bind(this)
    this.onChangeStreetAddress = this.onChangeStreetAddress.bind(this)
  }

  onChangeAddress = (field, event) => {
    this.props.updateAddress(this.props.address.identifier, field, event.target.value)
  }

  onChangeStreetAddress = (event) => {
    let streetAddress = event.target.value.trim()
    let i = streetAddress.indexOf(' ')
    let streetNumber = '', streetName = ''
    if (i == -1) {
      streetName = streetAddress
    } else {
      streetNumber = streetAddress.substring(0, i)
      if (isNaN(streetNumber)) {
        streetNumber = ''
        streetName = streetAddress
      } else {
        streetName = streetAddress.substring(i + 1)
      }
    }
    this.props.updateAddress(this.props.address.identifier, 'street_number', streetNumber)
    this.props.updateAddress(this.props.address.identifier, 'street_name', streetName)
  }

  render() {
    const address = this.props.address
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
                       value={ address.streetNumber + ' ' + address.streetName }
                       onChange={(e) => this.onChangeStreetAddress(e)}/>
              </Col>
              <Col>
                <Label>City</Label>
                <Input id={'city-' + this.props.id} type='text' name='city'
                       value={ address.city }
                       onChange={ (e) => this.onChangeAddress('city', e) }/>
              </Col>
            </FormGroup>
            <FormGroup row>
              <Col>
                <Label>State</Label>
                <DictionaryOptions
                    name='state_code'
                    options={ stateOptions }
                    code={ address.stateCode }
                />
              </Col>
              <Col>
                <Label>Zip</Label>
                <Input id={'zipCode-' + this.props.id} type='text' name='zipCode'
                       value={ address.zipCode }
                       onChange={ (e) => this.onChangeAddress('zip_code', e) }/>
              </Col>
              <Col>
                <Label>Address Type</Label>
                <DictionaryOptions
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
                    this.props.updateAddressesCallback({})
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

const mapDispatchToProps = { updateAddress }

export default connect(mapStateToProps, mapDispatchToProps)(Address)


