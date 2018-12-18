import './App.css'
import { Page } from '@cwds/components'
import { Main } from './Main.jsx'
import { SideNav } from './SideNav'

import '@cwds/core/dist/styles.css'

export const App = () => {
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
