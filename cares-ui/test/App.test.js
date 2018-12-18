import { shallow } from 'enzyme'
import { App } from '../src/App'

describe('App', () => {
  it('should welcome you', () => {
    const app = shallow(<App/>)
    const welcome = app.find('.App h1')
    expect(app.children().text()).toEqual('Welcome to cares-ui')
  })
})
