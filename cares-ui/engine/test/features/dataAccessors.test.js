import JsonBRE from '../../src/jsonBRE'
import Rule from '../../src/rule'

describe('Data Accessor Operators', () => {
  describe('"var"', () => {
    it('must be supported', () => {
      const rule = new Rule({
        identifier: 'var',
        definition: { "var": "client" }
      })
      const engine = new JsonBRE()
      engine.define(rule, { client: 'client' })
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
    })

    it('must support dot notation', () => {
      const rule = new Rule({
        identifier: 'var',
        definition: { "var": "client.name" }
      })
      const engine = new JsonBRE()
      engine.define(rule, {
        client: {
          name: 'name'
        }
      })
      expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
      engine.reset()
    })
  })

  describe('"missing"', () => {
    it('must be supported', () => {
      const rule = new Rule({
        identifier: 'missing',
        definition: { "missing": ["client"] }
      })
      const engine = new JsonBRE()
      engine.define(rule, { client: 'client' })
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      engine.reset()
    })

    it('must support dot notation', () => {
      const rule = new Rule({
        identifier: 'var',
        definition: { "missing": ["client.name"] }
      })
      const engine = new JsonBRE()
      engine.define(rule, {
        client: {
          name: 'name'
        }
      })
      expect(engine.find((rule) => rule.applies("client.name"))).toEqual([rule])
      expect(engine.find((rule) => rule.applies("client"))).toEqual([rule])
      expect(engine.find((rule) => rule.applies("name"))).toEqual([rule])
      engine.reset()
    })
  })
})
