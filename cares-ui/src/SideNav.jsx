import { Nav, NavItem, NavLink } from '@cwds/reactstrap'
import React from 'react'
import { connect } from 'react-redux'
import {
  selectReferrals,
  selectCurrentReferralId
} from './selectors'
import axios from 'axios'
import { getAllReferralsRoute } from './routes'
import {
  setReferrals,
  openReferral
} from './actions'

import '@cwds/core/dist/styles.css'

class SideNav extends React.Component {
  state = {
    activeIndex: -1
  }
  componentDidMount () {
    axios({
      url: getAllReferralsRoute(),
      method: 'get'
    }).then((response) => {
      this.props.setReferrals(response.data)
      if (this.props.currentReferralId === undefined) {
        const referralId = this.props.referrals[0].id
        this.props.openReferral(referralId)
      }
    })
  }

  render () {
    const referrals = this.props.referrals
    return (
      <Nav vertical pills>
      {
        referrals && referrals.map(({id, name}, index) =>
          <NavItem
            key={`nav-item-referral-${id}`}
            onClick={ ({target}) => {
              this.props.openReferral(id)
            }}
          >
          { this.isActive(id) &&
            <NavLink className={`referral-${id}`} active>
              <div>{ name }</div>
              <div className='id'>ID: { id }</div>
            </NavLink>
          }
          { !this.isActive(id) &&
            <NavLink className={`referral-${id}`}>
              <div>{ name }</div>
              <div className='id'>ID: { id }</div>
            </NavLink>
          }
          </NavItem>
        )
      }
      </Nav>
    )
  }

  isActive(id) {
    return this.props.currentReferralId === id
  }
}

const mapStateToProps = (state, ownProps) => ({
  referrals: selectReferrals(state).map((referral) => ({
    id: referral.identifier,
    name: referral.referral_name
  })),
  currentReferralId: selectCurrentReferralId(state)
})

const mapDispatchToProps = { setReferrals, openReferral }

export default connect(mapStateToProps, mapDispatchToProps)(SideNav)
