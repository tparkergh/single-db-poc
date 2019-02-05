import React from 'react'
import JsonBRE from 'JsonBRE'
import { Validation } from './validation'
import {
  Input,
} from '@cwds/reactstrap'

export class ValidateInput extends React.Component {
  state = {
    validate: ''
  }
  render () {
    const engine = new JsonBRE()
    const {
      type,
      name,
      value,
      onChange
    } = this.props
    return(
      <div>
        <Input id={'zipCode-' + this.props.id} type={type} name={name}
               value={ value }
               onChange={ onchange }
               onBlur={ ({target: { value: v }}) => this.setState({validate: v})}
        />
        { this.state.validate &&
          <Validation
            rules={ engine.find((rule) => rule.applies('address.zip')) }
            data={{
              address: {
                zip: this.state.validate
              }
            }}
          />
        }
      </div>
    )
  }
}
