import { shallow, mount } from 'enzyme'

import JsonForm from "../../src/reporter/JsonForm"
import BaseModel from "../../src/reporter/models/baseModel"

describe("JsonForm", () => {
  it('renders a Survey model when its active', () => {
    const model = new BaseModel({})
    const jsonForm = shallow(<JsonForm active={true} model={model}/>, { disableLifecycleMethods: true })
    const survey = jsonForm.find('Survey')
    expect(survey.exists()).toBeTruthy()
    expect(survey.props().model).toEqual(model)
  })

  it('does not render a Survey model when its inactive', () => {
    const model = new BaseModel({})
    const jsonForm = shallow(<JsonForm active={false} model={model}/>, { disableLifecycleMethods: true })
    const survey = jsonForm.find('Survey')
    expect(survey.exists()).toBeFalsy()
  })

  it('renders errors', () => {
  	const model = new BaseModel({})
    const jsonForm = shallow(<JsonForm active={true} model={model} error={'error here'}/>)
    expect(jsonForm.find('Alert').length).toEqual(1)
  })
})
