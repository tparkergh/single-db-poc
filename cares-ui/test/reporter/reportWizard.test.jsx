import { ReportWizard } from '../../src/reporter/ReportWizard'
import { shallow } from 'enzyme'

describe('ReportWizard', () => {
  it('renders a search component', () => {
    const reportWizard = shallow(<ReportWizard />)
    expect(reportWizard.find('Connect(Search)').exists()).toBeTruthy()
  })

  it('renders a search results component', () => {
    const reportWizard = shallow(<ReportWizard/>)
    expect(reportWizard.find('Connect(SearchResults)').exists()).toBeTruthy()
  })
})
