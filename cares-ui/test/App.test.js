import { shallow } from 'enzyme'
import { App } from '../src/App'
import { Main } from '../src/Main'
import { SideNav } from '../src/SideNav'
import JsonBRE from 'JsonBRE'

describe('App', () => {
  it('should render a page layout with side navigation and main area', () => {
    const app = shallow(<App/>)
    const page = app.find('Page')
    expect(page).toBeDefined()
    expect(page.props().main).toBe(Main)
    expect(page.props().sidenav).toBe(SideNav)
  })

  it('should define all the rules when the component is mounted', () => {
    const app = shallow(<App/>)
    expect(Object.keys(JsonBRE.rules)).toContain('first-name-required-rule')
    expect(Object.keys(JsonBRE.rules)).toContain('last-name-required-rule')
  })
})
