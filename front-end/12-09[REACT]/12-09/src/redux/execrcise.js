import { createStore } from "redux";

//사용자 상태 정의

const initialize = {
  count: 0,
  text: "",
  list: [],
};

//액션 타입 생성
const INCREASE = "INCREASE";
const DECREASE = "DECREASE";
const CANGE_TEXT = "CANGE_TEXT";
const ADD_TO_LIST = "ADD_TO_LIST";

//type이 네가지면 action 생성함수도 네가지이다

const increase = () => {
  return { type: INCREASE };
};

const decrease = () => {
  return { type: DECREASE };
};
const changeText = (text) => {
  return { type: CANGE_TEXT, text };
};
const addToList = (item) => {
  return { type: ADD_TO_LIST, item };
};

//reducer
function reducer(state = initialize, action) {
  switch (action.type) {
    case INCREASE:
      return { ...state, count: state.count + 1 };
      break;
    case DECREASE:
      return { ...state, count: state.count - 1 };
      break;
    case CANGE_TEXT:
      return { ...state, text: action.text };
      break;
    case ADD_TO_LIST:
      return { ...state, list: state.list.concat(action.item) };
      break;
    default:
      return state;
      break;
  }
}

//store만들기

const store = createStore(reducer);

// console.log(store.getState());

// listener 설정  -ㄴstore 상태가 변경될 떄 호출

const listener = () => {
  const state = store.getState();
  console.log(state);
};

//구독 설정
const unsubscribe = store.subscribe(listener);

store.dispatch(increase());
store.dispatch(increase());
store.dispatch(increase());
store.dispatch(increase());
store.dispatch(increase());
store.dispatch(changeText("씨발아"));
store.dispatch(changeText("씨발아"));
store.dispatch(changeText("씨발아"));
store.dispatch(addToList({ id: 1, name: "김정훈" }));
