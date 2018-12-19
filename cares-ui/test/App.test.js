import { shallow } from 'enzyme'
import { App } from '../src/App'
import { Main } from '../src/Main'
import { SideNav } from '../src/SideNav'

describe('App', () => {
  it('should render a page layout with side navigation and main area', () => {
    const app = shallow(<App/>)
    const page = app.find('Page')
    expect(page).toBeDefined()
    expect(page.props().main).toBe(Main)
    expect(page.props().sidenav).toBe(SideNav)
  })

})
