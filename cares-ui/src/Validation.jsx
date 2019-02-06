import {
    Alert
} from '@cwds/components'
import React from 'react'
import JsonBRE from 'JsonBRE'

export class Validation extends React.Component {
    render () {
      const { rules, data } = this.props
      const engine = new JsonBRE()
      const results = rules.map((rule) => engine.evaluate(rule, data))
      const errors = results.filter((result) => result !== true)
      if (errors.length === 0) {
        return null
      }
      return (
        <div>
          <br />
          <Alert color='danger'>
            <ul style={{ listStyleType: '\x1f44e' }}>
              { errors.map((error, index) => <li key={index}>{error}</li>) }
            </ul>
          </Alert>
        </div>
      )
    }
}
