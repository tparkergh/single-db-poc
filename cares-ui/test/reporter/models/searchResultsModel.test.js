import SearchResultsModel from '../../../src/reporter/models/searchResultsModel'
import { searchRoute } from '../../../src/routes'

describe('SearchResultsModel', () => {
  it('clears the search results on continue', (done) => {
    const props = {
      clearSearchResults: (data) => true
    }

    spyOn(props, 'clearSearchResults').and.callFake((data) => {
      expect(props.clearSearchResults).toHaveBeenCalled()
      done()
    })

    const model = new SearchResultsModel(props)
    model.doComplete()
  })

  it('loads the search results from the props when there is an update', () => {
    const props = { }
    const model = new SearchResultsModel(props)
    spyOn(model, 'loadResults')
    model.update(props)

    expect(model.loadResults).toHaveBeenCalledWith(props)
  })
})
