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

import { selectStateOptions, selectAddressTypeOptions, selectAddress, selectPutAddressRequest} from './selectors'
import { updateAddress } from './actions'
import { CodedOptions } from './CodedOptions'
import { putAddressRoute } from './routes'
import { ValidateInput } from './ValidateInput'

import '@cwds/core/dist/styles.css'

export class Address extends React.Component {
  state = { isOpen: false }

  renderFullAddress = ({
      streetNumber,
      streetName,
      city,
      stateCode,
      zipCode
      }, stateOptions) => {
    const stateOption = stateOptions.find(({key}) => key === stateCode).option
    return [streetNumber, streetName].join(' ')
        + ['', city, stateOption].join(', ') + ['', zipCode].join(' ')
  }

  renderStreetAddress = (streetNumber, streetName) => {
    let streetAddress = streetNumber
    if (streetAddress && streetName && (!streetAddress.endsWith(' ')) && (!streetName.startsWith(' '))) {
      streetAddress += ' '
    }
    streetAddress += streetName
    return streetAddress
  }

  parseStreetAddress = (streetAddress) => {
    let streetNumber = ''
    let streetName = ''
    const pattern = /\s+/g
    if (streetAddress && streetAddress.startsWith(' ')) {
      pattern.test(streetAddress)
    }
    pattern.test(streetAddress)
    const splitIndex = pattern.lastIndex
    if (splitIndex == 0) {
      if (isNaN(streetAddress)) {
        streetName = streetAddress
      } else {
        streetNumber = streetAddress
      }
    } else {
      const firstToken = streetAddress.substring(0, splitIndex)
      const secondToken = streetAddress.substring(splitIndex)
      if (isNaN(firstToken)) {
        streetName = streetAddress
      } else {
        streetNumber = firstToken
        streetName = secondToken
      }
    }
    return {
      streetNumber: streetNumber,
      streetName: streetName
    }
  }

  onChangeAddress = (field, value) => {
    this.props.updateAddress(this.props.id, field, value)
  }

  onChangeStreetAddress = (streetAddress) => {
    let address =  this.parseStreetAddress(streetAddress)
    this.props.updateAddress(this.props.id, 'street_number', address.streetNumber)
    this.props.updateAddress(this.props.id, 'street_name', address.streetName)
  }

  render() {
    const address = this.props.address
    const stateOptions = this.props.stateOptions
    const addressTypeOptions = this.props.addressTypeOptions
    const putAddressRequest = this.props.putAddressRequest

    return (
        <div>
          <div>
            {this.renderFullAddress(address, stateOptions)}
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
                       value={ this.renderStreetAddress(address.streetNumber, address.streetName) }
                       onChange={(e) => this.onChangeStreetAddress(e.target.value)}/>
              </Col>
              <Col>
                <Label>City</Label>
                <Input id={'city-' + this.props.id} type='text' name='city'
                       value={ address.city }
                       onChange={ (e) => this.onChangeAddress('city', e.target.value) }/>
              </Col>
            </FormGroup>
            <FormGroup row>
              <Col>
                <Label>State</Label>
                <CodedOptions
                    name='state_code'
                    options={ stateOptions }
                    code={ address.stateCode }
                />
              </Col>
              <Col>
                <Label>Zip</Label>
                <ValidateInput
                  id={'zipCode-' + this.props.id}
                  type='text'
                  name='zipCode'
                  value={address.zipCode}
                  onChange={ (e) => this.onChangeAddress('zip_code', e.target.value) }
                />
              </Col>
              <Col>
                <Label>Address Type</Label>
                <CodedOptions
                    name='address_type'
                    options={ addressTypeOptions }
                    code={ address.addressType }
                />
              </Col>
              <Col>
                <Label>Latitude/Longitude</Label>
                <Input id={'latLng-' + this.props.id} type='text' name='latitudelongitude'
                       value={ address.latitude + ', ' + address.longitude }/>
              </Col>
            </FormGroup>
            <FormGroup check row>
              <Button className='save float-right' color='primary'
                onClick={
                  ({ target }) => {
                    axios({
                      url: putAddressRoute(this.props.id),
                      method: 'put',
                      data: putAddressRequest
                    }).then((response) => {
                      this.props.updateAddressesCallback({})
                    })
                    .catch(function (error) {
                      // handle error
                      console.log(error);
                    })
                    this.setState({ isOpen: false })
                  }
                }
              >Save</Button>
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
    addressTypeOptions: selectAddressTypeOptions(state),
    putAddressRequest: selectPutAddressRequest(state, props.id)
})

const mapDispatchToProps = { updateAddress }

export default connect(mapStateToProps, mapDispatchToProps)(Address)


