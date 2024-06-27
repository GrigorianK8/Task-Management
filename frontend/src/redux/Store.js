import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { thunk } from "redux-thunk";
import authReducer from "./AuthSlice";
import TasksSlice from "./TasksSlice";



const rootReducer = combineReducers({
  auth: authReducer,
  task: TasksSlice
});

const store = configureStore({
  reducer:rootReducer,
  middleware:(getDefaultMiddleware)=>getDefaultMiddleware().concat(thunk)
});

export default store;