import Operator from '../src/operator'

describe('Operator', () => {
  describe('applies', () => {
    it('must support simple operations', () => {
      const operator = new Operator({value: 'client'})
      expect(operator.applies('client')).toEqual(true)
    })

    it('must support full dot operations', () => {
      const operator = new Operator({value: 'client.name'})
      expect(operator.applies('client.name')).toEqual(true)
    })

    it('must support partial dot operations', () => {
      const operator = new Operator({value: 'client.name'})
      expect(operator.applies('client')).toEqual(true)
      expect(operator.applies('name')).toEqual(true)
    })

    it('must support nested operations', () => {
      const operator = new Operator({
        value: { 'var': 'client.name' }
      })
      expect(operator.applies('client.name')).toEqual(true)
      expect(operator.applies('client')).toEqual(true)
      expect(operator.applies('name')).toEqual(true)
    })
  })
})
