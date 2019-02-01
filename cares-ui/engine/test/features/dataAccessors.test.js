import JsonBRE from '../../src/jsonBRE'
import Rule from '../../src/rule'
import { DATA_OPERATIONS } from '../../src/operations.js'

describe('Data Accessor Operators', () => {
  DATA_OPERATIONS.map((operation) =>
    describe(`"${operation}"`, () => {
      it('must be supported', () => {
        const definition = {}
        definition[operation] = "client"
        const rule = new Rule({
          identifier: `${operation}`,
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        engine.reset()
      })

      it('must support dot notation', () => {
        const definition = {}
        definition[operation] = "client.name"
        const rule = new Rule({
          identifier: `${operation}`,
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
        engine.reset()
      })
    })
  )
})
