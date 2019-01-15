import { render } from 'react-dom'
import { AppContainer } from 'react-hot-loader'
import { App } from './App'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import rootReducer from './reducers'

const store = createStore(rootReducer)

const root = document.getElementById('root')
const load = () => render((
  <Provider store={store}>
    <AppContainer>
      <App />
    </AppContainer>
  </Provider>
), root)

// This is needed for Hot Module Replacement
if (module.hot) {
  module.hot.accept('./App', load)
}

load()
