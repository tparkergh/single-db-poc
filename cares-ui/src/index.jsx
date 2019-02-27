import { render } from 'react-dom'
import { AppContainer } from 'react-hot-loader'
import App from './App'
import { SingleDbPoc } from './SingleDbPoc'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import rootReducer from './reducers'
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom'
import reporterReducer from './reporter/reducers'
import ReporterMain from './reporter/ReporterMain';

const store = createStore(rootReducer)

const reporterStore = createStore(reporterReducer, {
  models: {
    search: {
      active: true
    }
  }
})

const root = document.getElementById('root')
const load = () => render((
  <AppContainer>
    <Router>
      <div>
        <Route exact path="/" render={()=>(
          <Provider store={store}>
            <App/>
          </Provider>
        )}/>
        <Route path="/single-db-poc" render={()=>(
          <Provider store={store}>
            <SingleDbPoc/>
          </Provider>
        )} />
        <Route path='/report' render={()=>(
          <Provider store={reporterStore}>
            <ReporterMain/>
          </Provider>
        )} />
      </div>
    </Router>
  </AppContainer>
), root)

// This is needed for Hot Module Replacement
if (module.hot) {
  module.hot.accept('./App', load)
}

load()
