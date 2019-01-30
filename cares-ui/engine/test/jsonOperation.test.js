import JsonOperation from '../src/jsonOperation'

describe('JsonOperation', () => {
  describe('applies', () => {
    it('must support simple operations', () => {
      const operation = new JsonOperation({value: 'client'})
      expect(operation.applies('client')).toEqual(true)
    })

    it('must support full dot operations', () => {
      const operation = new JsonOperation({value: 'client.name'})
      expect(operation.applies('client.name')).toEqual(true)
    })

    it('must support partial dot operations', () => {
      const operation = new JsonOperation({value: 'client.name'})
      expect(operation.applies('client')).toEqual(true)
      expect(operation.applies('name')).toEqual(true)
    })

    it('must support nested operations', () => {
      const operation = new JsonOperation({
        value: { 'var': 'client.name' }
      })
      expect(operation.applies('client.name')).toEqual(true)
      expect(operation.applies('client')).toEqual(true)
      expect(operation.applies('name')).toEqual(true)
    })
  })
})
