import { shallow, mount } from 'enzyme'
import { Search } from '../../src/reporter/Search'
import SearchJSON from "../../src/reporter/jsonForms/search"
import SearchResultsJSON from "../../src/reporter/jsonForms/searchResults"

import SearchModel from "../../src/reporter/models/searchModel"
import SearchResultsModel from "../../src/reporter/models/searchResultsModel"

import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import { searchRoute } from '../../src/routes'

export const mockAxios = new MockAdapter(axios)

describe('Search', () => {
  it('renders a search model', () => {
    const search = shallow(<Search/>)
    const survey = search.find('Survey')
    const searchModel = new SearchModel(search.props())
    expect(survey.props().model.toJSON()).toEqual(searchModel.toJSON())
  })

  it('on completing the search it renders a search results model', () => {
    const search = mount(<Search/>)
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onComplete.add((data) => {
      const searchResultsModel = new SearchResultsModel(search.props())
      expect(survey.props().model.toJSON()).toEqual(searchResultsModel.toJSON())
      done()
    })

    survey.props().model.doComplete()
  })

  it('on completing the search results it renders a search model', () => {
    const search = mount(<Search/>)
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onComplete.add((data) => {
      survey.props().model.onComplete.add((data) => {
        const searchModel = new SearchModel(search.props())
        expect(survey.props().model.toJSON()).toEqual(searchModel.toJSON())
      })
      survey.props().model.doComplete()
      done()
    })

    survey.props().model.doComplete()
  })
})
