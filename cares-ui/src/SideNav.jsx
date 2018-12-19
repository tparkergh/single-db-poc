import { Nav, NavItem, NavLink } from '@cwds/reactstrap'
import '@cwds/core/dist/styles.css'

export const SideNav = () => {
  return (
    <Nav vertical pills>
      <NavItem>
        <NavLink className='joe-smoe' active>
          <div>Joe Smoe</div>
          <div className='id'>ID: XYZ</div>
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink className='sally-jane'>
          <div>Sally Jane</div>
          <div className='id'>ID: XYZ</div>
        </NavLink>
      </NavItem>
    </Nav>
  )
}
