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
import { ValidateInput } from './validateInput'

import '@cwds/core/dist/styles.css'

const getFullAddress = ({
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

export class Address extends React.Component {
  state = { isOpen: false }

  onChangeAddress = (field, event) => {
    this.props.updateAddress(this.props.id, field, event.target.value)
  }

  onChangeStreetAddress = (event) => {
    let streetAddress = event.target.value.trim()
    let [streetNumber, ...streetName] = streetAddress.split(' ')
    if (isNaN(streetNumber)) {
      streetNumber = ''
      streetName = streetAddress
    } else {
      streetName = streetName.join(' ')
    }
    this.props.updateAddress(this.props.id, 'street_number', streetNumber)
    this.props.updateAddress(this.props.id, 'street_name', streetName)
  }

  render() {
    const address = this.props.address
    const stateOptions = this.props.stateOptions
    const addressTypeOptions = this.props.addressTypeOptions
    const putAddressRequest = this.props.putAddressRequest

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
                <CodedOptions
                    name='state_code'
                    options={ stateOptions }
                    code={ address.stateCode }
                />
              </Col>
              <Col>
                <Label>Zip</Label>
                <ValidateInput>
                  type='text'
                  name='zipCode'
                  value={address.zipCode}
                  onChange={ (e) => this.onChangeAddress('zip_code', e) }
                </ValidateInput>
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


