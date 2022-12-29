# Redux 실습

```
yarn add redux reaxt-redux

```

```javascript
//counter.js
//액션타입 정의
const INCREASE = "counter/INCREASE";
const DECREASE = "counter/DECREASE";

//액션 생성 함수 정의
export const increase = () => ({ type: INCREASE });
export const decrease = () => ({ type: DECREASE });
//초기 상태를 정의

const initialize = {
  number: 0,
};

const counter = (state = initialize, action) => {
  switch (action.type) {
    case INCREASE:
      return { number: state.number + 1 };
    case DECREASE:
      return { number: state.number - 1 };
      break;
    default:
      return state;
      break;
  }
};

export default counter;

//리듀서 함수를 생성
```

```javascript
//index.js

import { legacy_createStore as createStore } from "redux";
import { Provider } from "react-redux";
import rootReducer from "./redux";

const store = createStore(rootReducer);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <App />
  </Provider>
);
...
...
```

# 컨테이너 컴포넌트 작업

> 컨테이너 컴포넌트

- redux를 사용하는 컴포넌트

- connect(mapStateToProps,mapDispatchToProps) (연동할 컴포넌트)
  - mapStateToProps
    - 리덕스 스토어 안의 상태를 컴포넌트의 props 로 넘겨주기 위해서 생성하는 함수
  - mapDispatchToProps
    - 액션 생성 함수를 컴포넌의 props로 넘겨주기 위해 사용하는 함수

```javascript
import React from "react";
import { connect } from "react-redux";
import Counter from "../components/Counter";

import { increase, decrease } from "../redux/count";

const CounterContainer = ({ number, increase, decrease }) => {
  return (
    <Counter number={number} onIncrease={increase} onDecrease={decrease} />
  );
};

const mapStateProps = (state) => ({
  number: state.counter.number,
});

const mapDispatchProps = (dispatch) => ({
  increase: () => {
    dispatch(increase());
  },
  decrease: () => {
    dispatch(decrease());
  },
});

export default connect(mapStateProps, mapDispatchProps)(CounterContainer);
```

### redux MiddleWare

> 미들웨어는 액션이 디스패치 된 후 리듀서에서 해당 액션을 받아서 작업을 수행하기 전이나 후에 추가적인 작업을 할 수 있도록 해주는것

- <b>작업을 수행하기 전에는 유효성 검사 같은 작업을 많이하고 작업이 수행 된 후에는 로그 기록을 많이함</b>

  - Filter,Interceptor,AOP

- 직접 생성한 미들웨어 적용

```javascript
//middleware.js
const mymiddleware = (store) => (next) => (action) => {
  //동작로깅
  console.log(action, "action middlewares");

  //다음 미들웨어나리듀서에게 전달

  const result = next(action);

  console.log(result, "result middlewares");

  console.log(store.getState()); //상태확인
  return result;
};

export default mymiddleware;
```

```javascript
//index.js
import mymiddleware from "./middlewares/mymiddleware";

const middlewares = applyMiddleware(mymiddleware);
const store = createStore(rootReducer, middlewares);
```

### redux logger

```
yarn add redux-logger
```

```javascript
//index.js
const middlewares = applyMiddleware(mymiddleware, logger);
```

- 이전상택값 이후 변경된 상태값이 보이게된다

### json -server

- 설치

```
<!-- npm install --location=global json-server -->
yarn global add json-server
```

- 서버실행

```
json-server ./data.json --port 4000
```

```
//data.json
{
  "posts": [
    { "id": 1, "content": "리액트", "done": true },
    { "id": 2, "content": "노드", "done": false }
  ]
}
```

- 이렇게하게되면 localhost:4000/posts 하게되면 보이게됨
- posts/1 이렇게해도 나옴

### 외부 서버 데이터를 proxy를 통해서 가져오기

```javascript
//package.json

  "proxy": "http://localhost:4000"

```
