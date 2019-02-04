import JsonBRE from '../../src/jsonBRE'
import Rule from '../../src/rule'

describe('Nested Operators', () => {
  it('must support complex rules that access data via "map" and "filter"', () => {
    const rule = new Rule({
      identifier: 'var-map',
      definition: {
        "map": [{
          "filter": [
            { "var": "clients" },
            { "===": [{"var": "name"}, "Tyler"]}
          ]},
          {
            "map": [
              { "var": "referrals"},
              { "var": "name"}
            ]
          }
        ]
      }
    })
    const engine = new JsonBRE()
    engine.define(rule)
    expect(engine.find((rule) => rule.applies("clients.referrals.name"))).toEqual([rule])
    expect(engine.find((rule) => rule.applies("clients.name"))).toEqual([rule])
  })

  it('must not select rules with matching selector patterns in strings', () => {
    const rule = new Rule({
      identifier: 'var-map',
      definition: {
        "map": [{
          "filter": [
            { "var": "clients" },
            { "===": [{"var": "field"}, "name"]}
          ]},
          {
            "map": [
              { "var": "referrals"},
              { "var": "name"}
            ]
          }
        ]
      }
    })
    const engine = new JsonBRE()
    engine.define(rule)
    expect(engine.find((rule) => rule.applies("clients.name"))).toEqual([])
    expect(engine.find((rule) => rule.applies("clients.field"))).toEqual([rule])
    expect(engine.find((rule) => rule.applies("clients.referrals.name"))).toEqual([rule])
  })
})
