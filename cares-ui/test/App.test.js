import { shallow } from 'enzyme'
import { App } from '../src/App'
import LandingPage from '../src/LandingPage'
import SideNav from '../src/SideNav'
import JsonBRE from 'JsonBRE'

describe('App', () => {
  it('should render a page layout with a Landing Page', () => {
    const app = shallow(<App />, { disableLifecycleMethods: true })
    const page = app.find('Page')
    expect(page).toBeDefined()
    expect(page.props().main).toBe(LandingPage)
  })

  xit('should define all the rules when the component is mounted', () => {
    const engine = new JsonBRE()
    const app = shallow(<App />, { disableLifecycleMethods: true })
    expect(Object.keys(engine.rules)).toContain('first-name-required-rule')
    expect(Object.keys(engine.rules)).toContain('last-name-required-rule')
  })
})
