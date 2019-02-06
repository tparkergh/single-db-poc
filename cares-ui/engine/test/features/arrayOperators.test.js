import JsonBRE from '../../src/jsonBRE'
import Rule from '../../src/rule'

import { ARRAY_OPERATIONS } from '../../src/operations'

describe('Array Operators', () => {
  ARRAY_OPERATIONS.map((operation) =>
    describe(`"${operation}"`, () => {
      it('must support basic operations', () => {
        const definition = {}
        definition[operation] = [
              { "var": "client" },
              { "*": [{"var": ""}]}
            ]
        const rule = new Rule({
          identifier: `${operation}`,
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        engine.reset()
      })

      it('must support dot notation with basic operations', () => {
        const definition = {}
        definition[operation] = [
              { "var": "client.name" },
              { "*": [{"var": ""}]}
            ]
        const rule = new Rule({
          identifier: 'map',
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
        engine.reset()
      })

      it('must support accessing object attributes', () => {
        const definition = {}
        definition[operation] = [
              { "var": "client" },
              { "*": [{"var": "name"}]}
            ]
        const rule = new Rule({
          identifier: 'map',
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
        engine.reset()
      })

      it('must support accessing nested object attributes', () => {
        const definition = {}
        definition[operation] = [
          { "var": "screening.client" },
          { "*": [{"var": "name"}]}
        ]
        const rule = new Rule({
          identifier: 'map',
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("screening.client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("screening.client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
        engine.reset()
      })

      it('must support dot accessing nested object attributes', () => {
        const definition = {}
        definition[operation] = [
          { "var": "screening.client" },
          { "*": [{"var": "name.first"}]}
        ]
        const rule = new Rule({
          identifier: 'map',
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("screening.client.name.first"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("screening.client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("screening.client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
        expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
        engine.reset()
      })

      it('must support selector tokens in order', () => {
        const definition = {}
        definition[operation] = [
              { "var": "screening.client" },
              { "*": [{"var": "name.first"}]}
            ]
        const rule = new Rule({
          identifier: 'map',
          definition
        })
        const engine = new JsonBRE()
        engine.define(rule)
        expect(engine.find((rule) => rule.applies("screening.client.first"))).toEqual([])
        engine.reset()
      })
    })
  )
})
