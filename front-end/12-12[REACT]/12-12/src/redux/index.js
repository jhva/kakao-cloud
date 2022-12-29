import { combineReducers } from "redux";
import counter from "./count";
import todo from "./todo";

const rootReducer = combineReducers({
  counter,
  todo,
});

export default rootReducer;
