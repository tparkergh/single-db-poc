import { shallow, mount } from 'enzyme'
import { SearchResults } from '../../src/reporter/SearchResults'
import SearchResultsJSON from "../../src/reporter/jsonForms/searchResults"
import SearchResultsModel from "../../src/reporter/models/searchResultsModel"

import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'
import {
  searchRoute,
  getBreRuleSetRoute,
  createReporterRoute
} from '../../src/routes'

export const mockAxios = new MockAdapter(axios)

describe('SearchResults', () => {
  it('renders a search results model when it is active', () => {
    const search = shallow(<SearchResults active={true}/>, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    const searchModel = new SearchResultsModel(search.props())
    expect(survey.props().model.toJSON()).toEqual(searchModel.toJSON())
  })

  it('renders nothing when the search results amodel is not active', () => {
    const search = shallow(<SearchResults active={false} />, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    expect(survey.exists()).toBeFalsy()
  })

  it('on completing the search results it sets the search model as active', (done) => {
    mockAxios.onPost(createReporterRoute()).reply(200)
    const updateSearchModel = jasmine.createSpy('updateSearchModel')
    const search = mount(<SearchResults
      active={true}
      updateSearchModel={updateSearchModel}
    />,
    { disableLifecycleMethods: true }
    )
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onCompleting.add((data) => {
      expect(updateSearchModel).toHaveBeenCalledWith({
        active: true,
      })
      done()
    })

    survey.props().model.doComplete()
  })

  it('on completing the search results it sets the search results model as inactive with data', (done) => {
    mockAxios.onPost(createReporterRoute()).reply(200)
    const updateSearchResultsModel = jasmine.createSpy('updateSearchResultsModel')
    const search = mount(<SearchResults
      active={true}
      data={{}}
      updateSearchResultsModel={updateSearchResultsModel}
    />, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onCompleting.add((data) => {
      expect(updateSearchResultsModel).toHaveBeenCalledWith({
        active: false,
        data: jasmine.anything()
      })
      done()
    })

    survey.props().model.doComplete()
  })

  it('on completing the search results it clears the search results', (done) => {
    mockAxios.onPost(createReporterRoute()).reply(200)
    const clearSearchResults = jasmine.createSpy('clearSearchResults')
    const search = mount(<SearchResults
      active={true}
      data={{}}
      clearSearchResults={clearSearchResults}
    />, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    mockAxios.onPost(searchRoute()).reply(200, {})

    survey.props().model.onCompleting.add((data) => {
      expect(clearSearchResults).toHaveBeenCalled()
      done()
    })

    survey.props().model.doComplete()
  })
})
