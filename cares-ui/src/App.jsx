import './App.css'
import React from 'react'
import { Page } from '@cwds/components'
import LandingPage from './LandingPage'
import JsonBRE from 'JsonBRE'
import { Rules } from './rules'
import axios from 'axios'
import { getAllSystemMetasRoute } from './routes'
import { setSystemMetas } from './actions'
import { connect } from 'react-redux'

import '@cwds/core/dist/styles.css'

export class App extends React.Component {
  constructor (props) {
    super(props)
    const engine = new JsonBRE()
    Rules.map((rule) => engine.define(rule))
  }
  componentDidMount () {
    axios({
      url: getAllSystemMetasRoute(),
      method: 'get'
    }).then((response) => this.props.setSystemMetas(response.data))
  }

  render () {
    return (
      <div className='App'>
        <Page
          layout='jumpnav'
          title='CARES Dashboard'
          breadcrumb={<div>Welcome to CARES Dashboard</div>}
          main={LandingPage}
        />
      </div>
    )
  }
}

const mapStateToProps = (state, ownProps) => ({})

const mapDispatchToProps = { setSystemMetas }

export default connect(mapStateToProps, mapDispatchToProps)(App)
