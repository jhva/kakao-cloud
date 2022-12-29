### 운영모드로 실행

```
yarn global add serve

```

### SPA (Single Page Application)

- 웹 브라우저가 서버에게 요청을 전송하면 서버가 HTML을 전송해서 전체를 다시 출력하는 방식
- 사용자와 인터럭션이 많은 웹 어플리케이션에서는 속도 측면에서 문제가 발생할 수 있음

### Code Spliting

- https://velog.io/@velopert/react-code-splitting
- https://xtring-dev.tistory.com/entry/Reactjs-%EC%BD%94%EB%93%9C-%EB%B6%84%ED%95%A0Code-Splitting-React-%EB%8D%94-%EC%9E%98-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
- 우리가 자바스크립트로 애플리케이션을 개발하게 되면, 기본적으로는 하나의 파일에 모든 로직들이 들어가게 됩니다. 그럼, 프로젝트의 규모가 커질수록 자바스크립트 파일 용량도 커짐

  - 용량이 커지면, 인터넷이 느린 환경에서는 페이지 로딩속도도 느려짐.

- 코드 스플리팅을 하게 되면, 지금 당장 필요한 코드가 아니라면 따로 분리시켜서, 나중에 필요할때 불러와서 사용 할 수 있습니다. 이를 통하여, 페이지의 로딩 속도를 개선 할 수 있죠.

### SPA 단점

- 앱의 규모가 커지면 자바스크립트 파일의 사이즈가 너무 커지게 되서 트래픽 과 로딩 속도에 문제가 발생
  - 이 문제를 해결하기 위해서는 Code Spliting 을 이용해서 해결
- 브라우저에서 자바스크립트 코드를 관리하는 경우 크롤러가 페이지의 정보를 제대로 받아가지 못하는 현상이 발생해서 검색엔진에서 페이지의 정보를 검색 결과에 포함하지 못하는 경우가 발생
- 처음 자바스크립트가 실행될때 까지 페이지가 비어 있기 때문에 빈페이지가 보여질수 있음 이런 경우를 방지하기 위해서는 첫번째 페이지는 서버에서 렌더링을 해서 보여지고 다음페이지부터 클라이언트 랜더링을 하는것이좋다.

### Server Rendering

- 웹 브라우저가 서버에게 요청을 전소앟면 서버가 html을 전송해서 전체를 다시 출력하는 방식

### REACT-ROUTER

> URL 뒤에 ? 를 추가하고 이름 과 값을 전달할 때 사용

### 공통 레이아웃

- 공통된 레이아웃을 위한 컴포넌트를 만들고 각 페이지 컴포넌트에서 직접 출력
- 중첩된 라우트 와 outlet을 이용해서 구현
  - 한번만 설정하면됨

```javascript
import { Outlet } from "outlet";
//Layout.js
<div>
  ... ...
  <Outlet />
</div>;

//app.js

<Route element={<Layout />}>
  // 공통된 레이아웃 가질 컴포넌트 및 라우터들
</Route>;
```

```javascript
<Route index element={<Home/>}>
// 이렇게하게되면 홈으로 가짐

```

### forwarding

> 요청 (reqeust) 객체를 유지하면서 이동,새로고침을 하면 요청이 다시이루어지는데 이경우는 서버에서 처리하는 로직이있으면 다시 로직을수행

- 새로고침을 했을대 작업을 다시 수행하고자 하는 경우 사용
  - 조회에 이용

### Redirect

> 요청 객체를 소멸시키면서 이동, 새로고침을 하면 요청이 다시 이루어지는 것이 아니고 현재 보여지고 있는 결과를 다시 출력한다

- 작업을 다시 수행하지 않아야하는 경우 사용
  - 삽입 ,삭제 갱신

### useNavigate

- Link 컴포넌트를 이용하지 않고 다른페이지로 이동하고자 할 때 사용하는 hook
- Redirect 하고 하는 경우 사용
- useNavigate hook이 리턴한 함수를 호출해서 처리할 수 있는데 매개변수로는 정수 나 문자열 하나 그리고 옵션을 설정함
  - 첫번째 매개변수가 숫자면 숫자만큼뒤나 앞으로 이동
  - 문자열 이동할 url이 된다

```javascript
const navigate = useNavigate();

const goBack = () => {
  navigate(-1);
};
```

### NavLink

- Link 와 거의 유사한데 현재 경로와 Link 에서 사용하는 경로가 일치하는 경우 특정 스타일을 적용 할 수 있도록 해주는 컴포넌트

### Navigate

> Navigate 컴포넌트는 컴포넌트를 화면에 보여주는 순간 다른 페이지로 이동을 하고 싶을때 사용하는 컴포넌트 ,리다이렉트 하고자 하는 경우 사용

```javascript
import { Navigate } from "react-router-dom";

<Navigate to="/" replace={true} />;
```

### Notfound

> 404 에러 잘못된 url일경우

- Route 를 만들 때 path를 "\*" 로 설정하면 모든 경우에 반응함

```javascript
<Route path="*" element={<NotFound />} />
```

# Context

> Component의 props를 이용해서 하위 Component 에게 넘겨주는 구조를 이용

- https://mingule.tistory.com/74
- https://kyounghwan01.github.io/blog/React/react-context-api/
- https://dev-yakuza.posstree.com/ko/react/context-api/
- 구조가 간단할 때는 크게 어려움이 없지만 구조가 복잡해지면 번거로운 작업이 많아진다.
- 여러 곳에서 하나의 데이터를 사용하는 경우도 유사한 작업이 반복된다
- provider 수정
- consumer 등록

```javascript
import { createContext } from "react";

//공유한 데이터 생성
const ColorContext = createContext({ color: "black" });

export default ColorContext;
```

- 공유 데이터를 사용할 컴포넌트 생성

```javascript
<ColorContext.Consumer>
  {(value) => <div style={{ width: "64px", background: value.color }}></div>}
</ColorContext.Consumer>
```

# Redux

> 상태 관리 라이브러리

- 프로젝트의 규모가 크거나 비동기 작업을 주로하는 경우에 사용

## 키워드

### action

- 상태에 어떠한 변화가 필요하게 될 때 발생시키는 객체
- 이 객체는 type이 필수이고 나머지는 옵션
- type을 가지고 동작을 구분해서 작업을 수행

### action creater

- Action을 생성하는 함수
- 필수는 아님
- 직접 Action 객체를 생성해도 되지만 별도의 함수를 만들어서 사용하기도 한다 .

### reducer

> 상태 변화를 일으키는 함수

```javascript
function 이름 (state,action){
  return 변경된 state
}
```

### store

> redux를 사용하는 프로젝트에서는 하나의 Store가 생성되는데 이 Store에는 애플리케이션의 state와 reducer가 들어간다

- 내장함수
  - dispatch
    - 실제 액션을 발생시키는 함수
    - 이 함수에 action 객체를 대입하면 dispatch가 reducer 를 호출해서 함수를 실행
  - subscribe
    - 이 함수는 함수 형태의 파라미터를 받아서 action이 dispatch 될 때 호출됨

### 규칙

- 하나의 애플리케이션에 하나의 스토어를 갖는 것이 원칙
- state는 읽기 전용
- reducer 는 pure function이여야한다 ,
  - 순수 함수
    - 외부에서 넘겨받은 매개변수는 수정하지 않고 복제를 해서 수정 후 return
    - 동일한 입력이면 동일한 출력이 만들어져여함
    - 랜덤이나 new Date 또는 네트워크에서 다운로드 받는 작업 등은 일정한 출력을 만들어내지 못하므로
      reducer에서 처리하면 안되고 별도의 미들웨어를 만들어서 처리
    - 참고 https://velog.io/@pest95/%EC%88%9C%EC%88%98%ED%95%A8%EC%88%98

### 설치

```
yarn add redux
```

```javascript
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
store.dispatch(changeText("이름"));
store.dispatch(changeText("한번"));
store.dispatch(changeText("두번"));
store.dispatch(addToList({ id: 1, name: "김정훈" }));
```

# Redux Module

> 리덕스 모듈은 액션 타입, 액션 생성 함수, 리듀서를 포함하는 자바스크립트 파일

- 실제 개발 환경에서는 대부분 액션과 리듀서를 하나의 파일에 만드는 경우가 많다 이렇게 하나의 파일에 만드는 것을 Ducks패턴이라고함
- 이전의 예제를 모듈화 시킨다면 counter를 변화를 주는 부분과 text에 변화를 주는 부분 그리고 배열에 변화를 주는 부분을 별도의 파일에 작성하고 다른파일에서 이를 combine 한 후 export 하는 형태로 만드는 것이 일반적이다

- 액션을 구분하기 위해서 액션 이름을 만들 때 액션 이름 앞에 접두어를 사용한다

```
yarn add react-redux
```

### 컴포넌트 분류

- Presentation Component
  - 리덕스 스토어에 직접 접근하지 않고 필요한 값이나 함수를 넘겨받아서 사용하느 컴포넌트로 UI를 선언하는 것에만 집중
- Container Component
  - 리덕스 스토어에 접근해서 상태를 조회하거나 액션을 디스패치하는 컴포넌트로 HTML태그를 사용하지않고 Component를 가져와서 사용만 함
