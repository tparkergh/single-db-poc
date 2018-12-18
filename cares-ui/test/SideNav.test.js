import { shallow } from 'enzyme'
import { SideNav } from '../src/SideNav'

describe('SideNav', () => {
  it('should render a vertical Nav built with pills', () => {
    const sideNav = shallow(<SideNav/>)
    expect(sideNav.props().vertical).toEqual(true)
    expect(sideNav.props().pills).toEqual(true)
  })

  it('should render a navigation link for Joe Smoe that is active', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.joe-smoe')
    expect(navLink.props().active).toEqual(true)
  })

  it('should render a navigation link for Joe Smoe with his ID', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.joe-smoe')
    expect(navLink.find('.id').children().text()).toEqual('ID: XYZ')
  })

  it('should render a navigation link for Sally Jane that is not active', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.sally-jane')
    expect(navLink.props().active).toBeFalsy()
  })

  it('should render a navigation link for Sally Jane with his ID', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.sally-jane')
    expect(navLink.find('.id').children().text()).toEqual('ID: XYZ')
  })
})
