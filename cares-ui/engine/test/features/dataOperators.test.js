import JsonBRE from '../../src/jsonBRE'
import Rule from '../../src/rule'

describe('Data Accessor Operators', () => {
  describe(`"var"`, () => {
    it('must be supported', () => {
      const definition = {
        "var": "client"
      }
      const rule = new Rule({
        identifier: "var",
        definition
      })
      const engine = new JsonBRE()
      engine.define(rule)
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      engine.reset()
    })

    it('must support dot notation', () => {
      const definition = {
        "var": "client.name"
      }
      const rule = new Rule({
        identifier: "var",
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

  describe(`"missing"`, () => {
    it('must be supported', () => {
      const definition = {
        "missing": ["client"]
      }
      const rule = new Rule({
        identifier: "missing",
        definition
      })
      const engine = new JsonBRE()
      engine.define(rule)
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      engine.reset()
    })

    it('must support dot notation', () => {
      const definition = {
        "missing": ["client.name"]
      }
      const rule = new Rule({
        identifier: "var",
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

  describe(`"missing_some"`, () => {
    it('must be supported', () => {
      const definition = {
        "missing_some": [1, ["client"]]
      }
      const rule = new Rule({
        identifier: "missing_some",
        definition
      })
      const engine = new JsonBRE()
      engine.define(rule)
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      engine.reset()
    })

    it('must support dot notation', () => {
      const definition = {
        "missing_some": [1, ["client.name"]]
      }
      const rule = new Rule({
        identifier: "missing_some",
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
})
