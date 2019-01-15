import { Nav, NavItem, NavLink } from '@cwds/reactstrap'
import '@cwds/core/dist/styles.css'

export const SideNav = () => {
  return (
    <Nav vertical pills>
      <NavItem>
        <NavLink className='referral-one' active>
          <div>Referral One</div>
          <div className='id'>ID: XYZ</div>
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink className='referral-two'>
          <div>Referral Two</div>
          <div className='id'>ID: ABC</div>
        </NavLink>
      </NavItem>
    </Nav>
  )
}
