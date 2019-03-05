import { shallow, mount } from 'enzyme'
import { Search } from '../../src/reporter/Search'
import SearchJSON from "../../src/reporter/jsonForms/search"
import SearchResultsJSON from "../../src/reporter/jsonForms/searchResults"
import axios from 'axios'
import MockAdapter from 'axios-mock-adapter'

export const mockAxios = new MockAdapter(axios)

import SearchModel from "../../src/reporter/models/searchModel"

import {
  searchRoute,
  getBreRuleSetRoute
} from '../../src/routes'


describe('Search', () => {
  it('renders a search model when it is active', () => {
    const search = shallow(<Search active={true} />, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    expect(survey.length).toEqual(1)
    const searchModel = new SearchModel(search.props())
    expect(survey.props().model.toJSON()).toEqual(searchModel.toJSON())
  })

  it('renders a search with error', () => {
    const search = shallow(<Search active={true} error={'error here'}/>)
    expect(search.find('Alert').length).toEqual(1)
  })

  it('renders nothing when the search model is not active', () => {
    const search = shallow(<Search active={false} />, { disableLifecycleMethods: true })
    const survey = search.find('Survey')
    expect(survey.exists()).toBeFalsy()
  })

  it('loads business rules before the component renders', () => {
    mockAxios.onGet(getBreRuleSetRoute('ReporterSearchScreenBusinessRules')).reply(200, { rules: [] })
    const search = shallow(<Search active={true}/>)
    const model = search.instance().model
    const spy = jest.spyOn(model, 'loadJsonRules')
    search.instance().componentDidMount()
    expect(spy).toHaveBeenCalled()
  })
})
