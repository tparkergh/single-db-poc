import ArrayOperation from '../src/arrayOperation'

describe('ArrayOperation', () => {
  describe('applies', () => {
    it('must support simple operations', () => {
      const arrayOperation = new ArrayOperation([ 'client' ])
      expect(arrayOperation.applies('client')).toEqual(true)
    })

    it('must support full dot operations', () => {
      const arrayOperation = new ArrayOperation(['client.name'])
      expect(arrayOperation.applies('client.name')).toEqual(true)
    })

    it('must support partial dot operations', () => {
      const arrayOperation = new ArrayOperation(['client.name'])
      expect(arrayOperation.applies('client')).toEqual(true)
      expect(arrayOperation.applies('name')).toEqual(true)
    })

    it('must support nested operations', () => {
      const arrayOperation = new ArrayOperation([{ 'var': 'client.name' }])
      expect(arrayOperation.applies('client.name')).toEqual(true)
      expect(arrayOperation.applies('client')).toEqual(true)
      expect(arrayOperation.applies('name')).toEqual(true)
    })
  })
})
