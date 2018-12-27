import './App.css'
import React from 'react'
import { Page } from '@cwds/components'
import { Main } from './Main.jsx'
import { SideNav } from './SideNav'
import JsonBRE from 'JsonBRE'
import { Rules } from './rules'

import '@cwds/core/dist/styles.css'

export class App extends React.Component {
  componentDidMount () {
    const engine = new JsonBRE()
    Rules.map((rule) => engine.define(rule))
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
