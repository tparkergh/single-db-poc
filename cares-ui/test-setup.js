import { configure } from 'enzyme'
import Adapter from 'enzyme-adapter-react-16'

global.XMLHttpRequest = undefined
configure({ adapter: new Adapter() })
