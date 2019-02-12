import { Main } from './Main'
import { Page } from '@cwds/components'
import SideNav from './SideNav'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

export const SingleDbPoc = () => {
  return (
    <Page
      layout='jumpnav'
      title='CARES Single DB POC'
      breadcrumb={<div>Welcome to Single DB POC</div>}
      sidenav={SideNav}
      main={Main}
    />
  )
}
