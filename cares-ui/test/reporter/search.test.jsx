import { shallow, mount } from 'enzyme'
import { Search } from '../../src/reporter/Search'
import SearchJSON from "../../src/reporter/jsonForms/search"
import SearchResultsJSON from "../../src/reporter/jsonForms/searchResults"

import SearchModel from "../../src/reporter/models/searchModel"

import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import { searchRoute } from '../../src/routes'

export const mockAxios = new MockAdapter(axios)

describe('Search', () => {
  it('renders a search model when it is active', () => {
    const search = shallow(<Search active={true} />)
    const survey = search.find('Survey')
    const searchModel = new SearchModel(search.props())
    expect(survey.props().model.toJSON()).toEqual(searchModel.toJSON())
  })

  it('renders nothing when the search model is not active', () => {
    const search = shallow(<Search active={false} />)
    const survey = search.find('Survey')
    expect(survey.exists()).toBeFalsy()
  })

  it('on completing the search it sets the search model as inactive with data', (done) => {
    const updateSearchModel = jasmine.createSpy('updateSearchModel')
    const search = mount(<Search active={true} updateSearchModel={updateSearchModel} />)
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onCompleting.add((data) => {
      expect(updateSearchModel).toHaveBeenCalledWith({
        active: false,
        data: jasmine.anything()
      })
      done()
    })

    survey.props().model.doComplete()
  })

  it('on completing the search it sets the search results model as active', (done) => {
    const updateSearchResultsModel = jasmine.createSpy('updateSearchModel')
    const search = mount(<Search active={true} updateSearchResultsModel={updateSearchResultsModel} />)
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onCompleting.add((data) => {
      expect(updateSearchResultsModel).toHaveBeenCalledWith({
        active: true
      })
      done()
    })

    survey.props().model.doComplete()
  })
})
