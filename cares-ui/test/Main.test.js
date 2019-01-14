import { shallow } from 'enzyme'
import { Main } from '../src/Main'
describe('Main', () => {
  it('should render a card for the person', () => {
    const main = shallow(<Main/>)
    const card = main.find('Card')
    expect(card.find('CardHeader')).toBeTruthy()
  })

  it('should render a form without autocomplete', () => {
    const main = shallow(<Main/>)
    const form = main.find('Form')
    expect(form.props().autoComplete).toEqual('off')
  })

  it('should render first name, middle name, and last name', () => {
    const main = shallow(<Main/>)
    const cardBody = main.find('CardBody')
    const formGroup = cardBody.find('FormGroup.name')
    expect(formGroup.find('Input[name="first_name"]')).toBeTruthy()
    expect(formGroup.find('Input[name="middle_name"]')).toBeTruthy()
    expect(formGroup.find('Input[name="last_name"]')).toBeTruthy()
  })

  it('should render validation for first name and last name', () => {
    const main = shallow(<Main/>)
    const cardBody = main.find('CardBody')
    const formGroup = cardBody.find('FormGroup.name')
    expect(formGroup.find('.firstName Validation')).toBeTruthy()
    expect(formGroup.find('.lastName Validation')).toBeTruthy()
  })

  it('should render date of birth for joe smoe', () => {
    const main = shallow(<Main/>)
    const cardBody = main.find('CardBody')
    const formGroup = cardBody.find('FormGroup.date_of_birth')
    const dateOfBirth = formGroup.find('Input')
    expect(dateOfBirth.props().value).toEqual('2015-09-03')
  })

  it('should render save and cancel buttons', () => {
    const main = shallow(<Main/>)
    const form = main.find('Form')
    const saveButton = form.find('Button.save')
    const cancelButton = form.find('Button.cancel')
    expect(saveButton.children().text()).toEqual('Save')
    expect(cancelButton.children().text()).toEqual('Cancel')
  })
})
