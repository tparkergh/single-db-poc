import { combineReducers } from 'redux'
import { errorReducer } from './errorReducer';
import entitiesReducer from './entitiesReducer';
import modelsReducer from './modelsReducer';

const reducer = combineReducers({ 
  errors: errorReducer,
  entities: entitiesReducer,
  models: modelsReducer
})

export default reducer