import Rule from '../src/rule'

describe('Rule', () => {
  it('must have an identifier and a definition', () => {
    const rule = new Rule({identifier: 'a', definition: {}})
    expect(rule.identifier).toEqual('a')
    expect(rule.definition).toEqual({})
  })

  describe('applies', () => {
    it('should return true if there is an operation on the data selector', () => {
      const rule = new Rule({
        identifier: 'simple-rule',
        definition: {
          "var": "client.yob"
        }
      })
      expect(rule.applies("client.yob")).toEqual(true)
    })

    it('should return false if there is no operation on the data selector', () => {
      const rule = new Rule({
        identifier: 'simple-rule',
        definition: {
          "var": "client.dob"
        }
      })
      expect(rule.applies("client.yob")).toEqual(false)
    })

    it('should return true if it finds an operation on the data selector in a nested structure', () => {
      const rule = new Rule({
        identifier: 'nested-rule',
        definition: {
          "<=": [{"var": "client.yob"}, 2]
        }
      })

      expect(rule.applies("client.yob")).toEqual(true)
    })

    it('should return true if it finds an operation on the data selector in a deeply nested structure', () => {
      const rule = new Rule({
        identifier: 'nested-rule',
        definition: {
          "<=": [{"var": "client.dob"}, {">=":['4',{"var": "client.yob"}]}]
        }
      })

      expect(rule.applies("client.yob")).toEqual(true)
    })
  })

  describe('render', () => {
    it('should render the rule as a string', () => {
      const rule = new Rule({
        identifier: 'rendered-rule',
        definition: {
          "<=": [{"var": "client.dob"}, {">=":[4,{"var": "client.yob"}]}]
        }
      })
      expect(rule.render()).toEqual('{"<=":[{"var":"client.dob"},{">=":[4,{"var":"client.yob"}]}]}')
    })
  })
})
