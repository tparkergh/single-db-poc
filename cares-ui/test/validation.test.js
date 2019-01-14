import { shallow } from 'enzyme'
import { Validation } from '../src/Validation'
import JsonBRE from 'JsonBRE'

jest.mock('JsonBRE')

describe('validation', () => {
  it('runs the specified rules against the data', () => {
    const rule = {
      definition: {
        missing: 'name'
      }
    }
    const data = {
      name: 'asdf'
    }
    JsonBRE.evaluate.mockReturnValue([true])
    const validation = shallow(<Validation rules={[rule]} data={data} />)
    expect(JsonBRE.evaluate).toBeCalledWith(rule, data)
  })

  it('displays errors', () => {
    const rule = {
      definition: {
        missing: 'name'
      }
    }

    const data = {
      name: 'asdf'
    }

    JsonBRE.evaluate.mockReturnValue([
      true,
      'first name is required',
      'last name is required'
    ])
    const validation = shallow(<Validation rules={[rule]} data={data} />)
    expect(validation.find('Alert ul li').children().at(1).text()).toEqual('first name is required')
    expect(validation.find('Alert ul li').children().at(2).text()).toEqual('last name is required')
  })
})
