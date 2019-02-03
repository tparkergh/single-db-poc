import React from 'react'
import { connect } from 'react-redux'
import axios from 'axios'

import {
  Label,
  ListGroup,
  ListGroupItem,
} from '@cwds/reactstrap'

import { selectAddressIds } from './selectors'
import { getClientAddressesByClientIdRoute } from './routes'
import { setAddresses } from './actions'
import Address from './Address'

import '@cwds/core/dist/styles.css'

export class Addresses extends React.Component {

  componentDidUpdate (prevProps) {
    if (this.props.clientId !== prevProps.clientId) {
      axios({
        url: getClientAddressesByClientIdRoute(this.props.clientId),
        method: 'get'
      }).then((response) => this.props.setAddresses(response.data))
    }
  }

  render () {
    const addressIds = this.props.addressIds

    return (
      <div>
        <Label>Addresses</Label>
        <ListGroup>
          {
            addressIds && addressIds.map((addressId) =>
              <ListGroupItem>
                <Address id={addressId}/>
              </ListGroupItem>
            )
          }
        </ListGroup>
      </div>
    )
  }
}

const mapStateToProps = (state) => ({
  addressIds: selectAddressIds(state)
})

const mapDispatchToProps = { setAddresses }

export default connect(mapStateToProps, mapDispatchToProps) (Addresses)

