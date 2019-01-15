import { shallow } from 'enzyme'
import { SideNav } from '../src/SideNav'

describe('SideNav', () => {
  it('should render a vertical Nav built with pills', () => {
    const sideNav = shallow(<SideNav/>)
    expect(sideNav.props().vertical).toEqual(true)
    expect(sideNav.props().pills).toEqual(true)
  })

  it('should render a navigation link for Referral One that is active', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.referral-one')
    expect(navLink.props().active).toEqual(true)
  })

  it('should render a navigation link for Referral One with his ID', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.referral-one')
    expect(navLink.find('.id').children().text()).toEqual('ID: XYZ')
  })

  it('should render a navigation link for Referral Two that is not active', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.referral-two')
    expect(navLink.props().active).toBeFalsy()
  })

  it('should render a navigation link for Referral Two with his ID', () => {
    const sideNav = shallow(<SideNav/>)
    const navLink = sideNav.find('NavLink.referral-two')
    expect(navLink.find('.id').children().text()).toEqual('ID: ABC')
  })
})
