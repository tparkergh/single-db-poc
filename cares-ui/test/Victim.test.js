import { shallow } from 'enzyme'
import { Victim } from '../src/Victim'
describe('Victim', () => {
  it('should render a card for Joe Smoe', () => {
    const main = shallow(<Victim/>)
    const card = main.find('Card')
    const cardHeader = card.find('CardHeader')
    expect(cardHeader.children().text()).toEqual('Joe Smoe')
  })

  it('should render a form without autocomplete', () => {
    const main = shallow(<Victim/>)
    const form = main.find('Form')
    expect(form.props().autoComplete).toEqual('off')
  })

  it('should render first name, middle name, and last name for Joe Smoe', () => {
    const main = shallow(<Victim/>)
    const cardBody = main.find('CardBody')
    const formGroup = cardBody.find('FormGroup.name')
    const firstName = formGroup.find('Input[name="first_name"]')
    const middleName = formGroup.find('Input[name="middle_name"]')
    const lastName = formGroup.find('Input[name="last_name"]')
    expect(firstName.props().value).toEqual('Joe')
    expect(middleName.props().value).toBeUndefined()
    expect(lastName.props().value).toEqual('Smoe')
  })

  it('should render date of birth for joe smoe', () => {
    const main = shallow(<Victim/>)
    const cardBody = main.find('CardBody')
    const formGroup = cardBody.find('FormGroup.date_of_birth')
    const dateOfBirth = formGroup.find('Input')
    expect(dateOfBirth.props().value).toEqual('2015-09-03')
  })

  it('should render save and cancel buttons', () => {
    const main = shallow(<Victim/>)
    const form = main.find('Form')
    const saveButton = form.find('Button.save')
    const cancelButton = form.find('Button.cancel')
    expect(saveButton.children().text()).toEqual('Save')
    expect(cancelButton.children().text()).toEqual('Cancel')
  })
})
