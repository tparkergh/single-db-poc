import SearchModel from '../../../src/reporter/models/searchModel'
import { searchRoute } from '../../../src/routes'
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

export const mockAxios = new MockAdapter(axios)

describe('SearchModel', () => {
  it('makes an api call to search on complete and sets search results', (done) => {
    const props = {
      setSearchResults: (data) => true
    }

    mockAxios.onPost(searchRoute()).reply(200, {})
    spyOn(props, 'setSearchResults').and.callFake((data) => {
      expect(mockAxios.history.post.length).toEqual(1)
      expect(props.setSearchResults).toHaveBeenCalledWith({})
      done()
    })

    const model = new SearchModel(props)
    model.doComplete()
  })
})
