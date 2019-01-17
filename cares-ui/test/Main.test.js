import { shallow } from 'enzyme'
import { Main } from '../src/Main'
describe('Main', () => {
  it('should render a victim card', () => {
    const main = shallow(<Main/>)
    expect(main.find('Victim')).toBeTruthy()
  })
})
