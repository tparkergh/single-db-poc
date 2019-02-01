import JsonBRE from '../src/jsonBRE'
import Rule from '../src/rule'

describe('JsonBRE', () => {
  const simpleRule = {
    identifier: 'simple-rule',
    definition: {
      '==': [
        'asdf',
        { 'var': 'data.value' }
      ]
    }
  }

  const simpleData = {
    'data': { 'value': 'asdf' }
  }

  describe('evaluate', () => {
    it('should evaluate a rule based on JsonLogic', () => {
      const engine = new JsonBRE()
      expect(engine.evaluate(simpleRule, simpleData)).toBe(true)
    })
  })

  describe('define', () => {
    it('should define a rule that is passed in', () => {
      const engine = new JsonBRE()
      expect(engine.define(simpleRule)).toEqual(new Rule(simpleRule))
      expect(engine.rules['simple-rule']).toEqual(new Rule(simpleRule))
    })

    it('should re-define a rule that already exists', () => {
      const newRule = new Rule({
        identifier: 'simple-rule',
        definition: { "==": [1,2] }
      })
      const engine = new JsonBRE()
      expect(engine.define(simpleRule)).toEqual(new Rule(simpleRule))
      expect(engine.rules['simple-rule']).toEqual(new Rule(simpleRule))
      expect(engine.define(newRule)).toEqual(newRule)
      expect(engine.rules['simple-rule']).toEqual(newRule)
    })
  })

  describe('destroy', () => {
    it('should remove a rule definition that exists', () => {
      const engine = new JsonBRE()
      engine.define(simpleRule)
      engine.destroy(({identifier}) => (identifier === 'simple-rule'))
      expect(engine.rules['simple-rule']).toBe(undefined)
    })

    it('should not remove non-matching rules', () => {
      const engine = new JsonBRE()
      engine.define(simpleRule)
      engine.destroy(({identifier}) => (identifier === 'complex-rule'))
      expect(engine.rules['simple-rule']).toEqual(new Rule(simpleRule))
    })
  })

  describe('find', () => {
    it('should find rules based on their identifiers', () => {
      const engine = new JsonBRE()
      engine.define(simpleRule)
      expect(engine.find((rule) => ( rule.identifier === 'simple-rule' ))).toEqual([new Rule(simpleRule)])
    })

    it('should return empty array when it does not find a rule based on its identifier', () => {
      const engine = new JsonBRE()
      engine.define(simpleRule)
      expect(engine.find((rule) => ( rule.identifier === 'some rule that doesnt exist' ))).toEqual([])
    })
  })
})
