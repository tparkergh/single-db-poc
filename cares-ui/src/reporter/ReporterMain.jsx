import { Component } from  'react'
import { Page } from '@cwds/components'
import { ReportWizard } from './ReportWizard'

export default class ReporterMain extends Component {

  render () {
    return(
      <Page
        layout='jumpnav'
        title='Reporter Search'
        breadcrumb={<div>Reporter Search</div>}
        main={ReportWizard}
      /> 
    ) 
  }
}
