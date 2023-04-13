import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import authReducer from './authentication/authReducer';


const store = createStore(authReducer, applyMiddleware(thunk));

export default store;