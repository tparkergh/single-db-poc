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
      expect(JsonBRE.evaluate(simpleRule, simpleData)).toBe(true)
    })
  })

  describe('define', () => {
    it('should define a rule that is passed in', () => {
      expect(JsonBRE.define(simpleRule)).toEqual(simpleRule)
      expect(JsonBRE.rules['simple-rule']).toEqual(new Rule(simpleRule))
    })

    it('should re-define a rule that already exists', () => {
      const newRule = new Rule({
        identifier: 'simple-rule',
        definition: {}
      })
      expect(JsonBRE.define(simpleRule)).toEqual(new Rule(simpleRule))
      expect(JsonBRE.rules['simple-rule']).toEqual(simpleRule)
      expect(JsonBRE.define(newRule)).toEqual(newRule)
      expect(JsonBRE.rules['simple-rule']).toEqual(newRule)
    })
  })

  describe('destroy', () => {
    it('should remove a rule definition that exists', () => {
      JsonBRE.define(simpleRule)
      JsonBRE.destroy(({identifier}) => (identifier === 'simple-rule'))
      expect(JsonBRE.rules['simple-rule']).toBe(undefined)
    })

    it('should not remove non-matching rules', () => {
      JsonBRE.define(simpleRule)
      JsonBRE.destroy(({identifier}) => (identifier === 'complex-rule'))
      expect(JsonBRE.rules['simple-rule']).toEqual(new Rule(simpleRule))
    })
  })

  describe('find', () => {
    it('should find rules based on their identifiers', () => {
      JsonBRE.define(simpleRule)
      expect(JsonBRE.find((rule) => ( rule.identifier === 'simple-rule' ))).toEqual([new Rule(simpleRule)])
    })

    it('should return empty array when it does not find a rule based on its identifier', () => {
      JsonBRE.define(simpleRule)
      expect(JsonBRE.find((rule) => ( rule.identifier === 'some rule that doesnt exist' ))).toEqual([])
    })
  })
})
