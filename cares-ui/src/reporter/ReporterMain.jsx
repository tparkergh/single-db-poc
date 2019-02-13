import { Component } from  'react'
import { Page } from '@cwds/components'
import Search from './Search';

export default class ReporterMain extends Component {

  render () {
    return(
      <Page
        layout='jumpnav'
        title='Reporter Search'
        breadcrumb={<div>Reporter Search</div>}
        main={Search}
      /> 
    
    ) 
  }
}