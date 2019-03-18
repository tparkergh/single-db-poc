import BaseModel from '../../../src/reporter/models/baseModel'

describe('BaseModel', () => {
  describe('validateRules', () => {
    it('validates the rules based on options.name and a prefix', () => {
      const model = new BaseModel({})
      model.data = {
        first_name: 'first',
        last_name: 'last'
      }
      const options = {
        name: 'first_name'
      }
      spyOn(model, 'validationData').and.returnValue({
        first_name: '_first'
      })
      model.engine.define({
        identifier: 'rule',
        definition: {
          "if": [{ "===": [{
            "substr": [{
              "var": "first_name"
            }, 0, 1]
          }, "_"]},
            "A first name cannot start with _ under a prefix",
            true
          ]
        }
      })
      model.validateRules(undefined, options)
      expect(options.error).toEqual("A first name cannot start with _ under a prefix")
    })
  })
})
