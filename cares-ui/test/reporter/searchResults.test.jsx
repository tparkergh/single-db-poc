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
})
