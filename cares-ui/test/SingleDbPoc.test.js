import { shallow } from 'enzyme'
import { SingleDbPoc } from '../src/SingleDbPoc'
import { Main } from '../src/Main'
import SideNav from '../src/SideNav'

describe('SingleDbPoc', () => {
  it('should render a page with title "CARES Single DB POC"', () => {
    const singleDbPoc = shallow(<SingleDbPoc/>)
    expect(singleDbPoc.props().title).toEqual("CARES Single DB POC")
  })

  it('should render a page with breadcrumb "Welcome to Single DB POC"', () => {
    const singleDbPoc = shallow(<SingleDbPoc/>)
    const breadcrumb = shallow(singleDbPoc.props().breadcrumb)
    expect(breadcrumb.text()).toEqual("Welcome to Single DB POC")
  })

  it('should pass side nav and main components', () => {
    const singleDbPoc = shallow(<SingleDbPoc/>)
    expect(singleDbPoc.props().sidenav).toEqual(SideNav)
    expect(singleDbPoc.props().main).toEqual(Main)
  })
})
