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

const store = createStore(rootReducer)

const root = document.getElementById('root')
const load = () => render((
  <Provider store={store}>
    <AppContainer>
      <Router>
        <div>
          <Route exact path="/" component={App}/>
          <Route path="/single-db-poc" component={SingleDbPoc} />
        </div>
      </Router>
    </AppContainer>
  </Provider>
), root)

// This is needed for Hot Module Replacement
if (module.hot) {
  module.hot.accept('./App', load)
}

load()
