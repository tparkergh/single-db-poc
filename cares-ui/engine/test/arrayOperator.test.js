import ArrayOperator from '../src/arrayOperator'

describe('ArrayOperator', () => {
  describe('applies', () => {
    it('must support simple operations', () => {
      const arrayOperator = new ArrayOperator([ 'client' ])
      expect(arrayOperator.applies('client')).toEqual(true)
    })

    it('must support full dot operations', () => {
      const arrayOperator = new ArrayOperator(['client.name'])
      expect(arrayOperator.applies('client.name')).toEqual(true)
    })

    it('must support partial dot operations', () => {
      const arrayOperator = new ArrayOperator(['client.name'])
      expect(arrayOperator.applies('client')).toEqual(true)
      expect(arrayOperator.applies('name')).toEqual(true)
    })

    it('must support nested operations', () => {
      const arrayOperator = new ArrayOperator([{ 'var': 'client.name' }])
      expect(arrayOperator.applies('client.name')).toEqual(true)
      expect(arrayOperator.applies('client')).toEqual(true)
      expect(arrayOperator.applies('name')).toEqual(true)
    })
  })
})
