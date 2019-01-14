import './App.css'
import React from 'react'
import { Page } from '@cwds/components'
import { Main } from './Main.jsx'
import { SideNav } from './SideNav'
import JsonBRE from 'JsonBRE'
import { Rules } from './rules'

import '@cwds/core/dist/styles.css'

// this is just for debugging purposes during poc
window.engine = JsonBRE
console.log('engine:', window.engine)

export class App extends React.Component {
  constructor (props) {
    super(props)
    Rules.map((rule) => JsonBRE.define(rule))
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
