import JsonObject from '../src/jsonObject'

describe('JsonObject', () => {
  describe('applies', () => {
    it('must support simple operations', () => {
      const operation = new JsonObject({'var': 'client'})
      expect(operation.applies('client')).toEqual(true)
    })

    it('must support full dot operations', () => {
      const operation = new JsonObject({'var': 'client.name'})
      expect(operation.applies('client.name')).toEqual(true)
    })

    it('must support partial dot operations', () => {
      const operation = new JsonObject({'var': 'client.name'})
      expect(operation.applies('client')).toEqual(true)
      expect(operation.applies('name')).toEqual(true)
    })

    it('must support nested operations', () => {
      const operation = new JsonObject({
        'var': 'client.name'
      })
      expect(operation.applies('client.name')).toEqual(true)
      expect(operation.applies('client')).toEqual(true)
      expect(operation.applies('name')).toEqual(true)
    })
  })
})
