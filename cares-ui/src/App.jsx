import './App.css'
import React from 'react'
import { Page } from '@cwds/components'
import { Main } from './Main.jsx'
import SideNav from './SideNav'
import JsonBRE from 'JsonBRE'
import { Rules } from './rules'
import axios from 'axios'
import { getAllSystemCodesRoute } from './routes'
import { setSystemCodes } from './actions'
import { connect } from 'react-redux'

import '@cwds/core/dist/styles.css'

class App extends React.Component {
  componentDidMount () {
    const engine = new JsonBRE()
    Rules.map((rule) => engine.define(rule))
    axios({
      url: getAllSystemCodesRoute(),
      method: 'get'
    }).then((response) => this.props.setSystemCodes(response.data))
  }

  render () {
    return (
      <div className='App'>
        <Page
          layout='jumpnav'
          title='CARES POC'
          breadcrumb={<div>Proof of concept for CARES validation</div>}
          sidenav={SideNav}
          main={Main}
        />
      </div>
    )
  }
}

const mapDispatchToProps = { setSystemCodes }

export default connect(undefined, mapDispatchToProps)(App)
