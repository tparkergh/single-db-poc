import { shallow, mount } from 'enzyme'
import { LandingPage } from '../src/LandingPage'

describe('LandingPage', () => {
  it('should display a selectable option for going to the old poc', () => {
    const landingPage = shallow(<LandingPage/>)
    const lastCardTitle = landingPage
      .find('CardTitle')
      .first()
      .shallow()
    expect(lastCardTitle.text()).toContain('Single DB POC')
  })

  it('should display a selectable option for going to the new product', () => {
    const landingPage = shallow(<LandingPage/>)
    const lastCardTitle = landingPage
      .find('CardTitle')
      .last()
      .shallow()
    expect(lastCardTitle.text()).toContain('CARES Intake')
  })
})
