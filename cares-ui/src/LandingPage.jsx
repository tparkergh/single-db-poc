import React from 'react'
import { connect } from 'react-redux'
import {
  Row,
  Col,
  Card,
  CardTitle,
  Button
} from '@cwds/components'
import { Link } from 'react-router-dom'

export class LandingPage extends React.Component {
  render() {
    return (
      <div>
        <Card body>
          <CardTitle>Single DB POC</CardTitle>
          <p>A proof of concept that exercises a single database to get information about referrals and update the addresses of victims.</p>
          <Button><Link to="/single-db-poc" style={{display: 'block', height: '100%'}}>Go</Link></Button>
        </Card>
        <Card body>
          <CardTitle>CARES Intake</CardTitle>
          <p>A product that gives social workers the ability to add and search reporters through CARES</p>
          <Button><Link to="/report" style={{display: 'block', height: '100%'}}>Go</Link></Button>
        </Card>
      </div>
    )
  }
}

const mapStateToProps = (state, ownProps) => ({})
export default connect(mapStateToProps)(LandingPage)
