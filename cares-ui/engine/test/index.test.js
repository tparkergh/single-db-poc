import JsonBRE from '../src/index.js'

describe('JsonBRE', () => {
  describe('evaluate', () => {
    it('should evaluate a rule based on JsonLogic', () => {
      const simpleRule = {
        '==': [
          'asdf',
          { 'var': 'data.value' }
        ]
      }
      const simpleData = {
        'data': { 'value': 'asdf' }
      }
      expect(JsonBRE.evaluate(simpleRule, simpleData)).toBe(true)
    })
  })
})
