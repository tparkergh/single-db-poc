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
    const search = shallow(<Search active={true} searchError={'error here'}/>)
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

  it('on completing the search it sets the search model as inactive with data', (done) => {
    const updateSearchModel = jasmine.createSpy('updateSearchModel')
    const search = mount(
      <Search active={true} updateSearchModel={updateSearchModel} />,
      { disableLifecycleMethods: true })
    const survey = search.find('Survey')

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
    const search = mount(
      <Search active={true} updateSearchResultsModel={updateSearchResultsModel} />,
      { disableLifecycleMethods: true }
    )
    const survey = search.find('Survey')

    survey.props().model.onCompleting.add((data) => {
      expect(updateSearchResultsModel).toHaveBeenCalledWith({
        active: true
      })
      done()
    })

    survey.props().model.doComplete()
  })
})
