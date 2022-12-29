### global body

> web application 에서 body나 모든 box 태그에 margin 과 padding을 0 오 설정하는 경우가 있는데 이유는 IE 구버전 과의 호환성 문제 때문이다<br/>IE 구버전은 width 와 height안에 padding 과 margin그리고 border 의 크기가 포함되고 나머지 브라우저는 content 의 크기만 포함된다

# To Do List

### ToDoTemplate

- main컴포넌트

### ToDoInsert

- 데이터 삽입을 위해서 하나의 input과 버튼을 가진 컴포넌트

### ToDoListItem

- 하나의 항목을 출력하기 위한 컴포넌트

### ToDoList

- ToDoListItem 의 목록을 출력하기 위한 컴포넌트

### index.js (node를 기반으로하는 프로젝트에서 )

> 디렉토리 안의 모든것들을 외부에서 사용할 수 있도록 export하는 것이다

- require나 import 할 때 디렉토리 이름을 사용하면 디렉토리 안에 있는 index.js 파일에서 export한 내용을 가져온다 .

## ToDoList 기능 구현

### 데이터 배열 출력

- App.js파일을 수정해서 샘플 데이터 배열을 state로 생성하고 ToDoList에게 전달

```javascript
..
..
  const [todos, setTodos] = useState([
    { id: 1, text: "HTML,CSS, Javascript", checked: true },
    { id: 2, text: "정훈이 어지러워요", checked: true },
    { id: 3, text: "정훈이 술먹은거같아요", checked: false },
  ]);
  return (
    <>
      <ToDoTemplate>
        <ToDoInsert />
        {/* <ToDoListItem /> */}
        <ToDoList todos={todos} />


..
..

```

### 데이터 추가 기능

- 데이터를 처리하는 함수는 App.js (state가 app.js에 존재) 에 만들어서 넘겨주는 구조

- useRef 는 변수를 새성하거나 변수를 만들어서 DOM에 할당하기 위해서
- useCallback은 함수를 효율적으로 생성하기 위해서

### 컴포넌트가 리랜더링 되는 경우

- 전달받은 Props 가 변경되는 경우
- 자신의 state가 변경되는 경우
- 상위 컴포넌트가 리랜더링 되는 경우

### 하나의 데이터가 수정되면 전체가 리랜더링 문제를 해결

- 현재 컴포넌트가 200개 인데 하나의 데이터에 수정이 발생하면 todos 에 변경이 일어나고 todos는 App컴포넌트의 state이다
- todos는 app컴포넌트의 state이므로 App이 리렌더링 할텐데 이렇게 되면 화면 전체가 리랜더링하는것과 같다
- 자신의 props가 변경될때만 리렌더링을 하도록 할수있다 .
  - class Component에서는 shouldComponentUpdate 라는 수명주기 메서드를 이용하고 Function Component에서는 React.memo를 이용한다

```javascript
React.memo(ToDoListItem);
```

### useReducer

- App.js 를수정
  - state를 수정하는 함수를 컴포넌트 외부
- 함수는 변경할 state와 action을 매개변수로 받아서 action의 type을 가지고 분기를 만들어서 state에 작업을 수행해주면된다
- 컴포넌트 내부에서는 useState를 사용하지 않고 useReducer (함수이름,초기값,초기화하는함수)

```javascript
const [todos, dispatch] = useReducer(todoReducer, undefined, createBulkTodos);

//첫번째 매개변수는 호출될 함수
// 두번째는 매개변수는 초기값
// 세번째 매개변수는 호출할 메서드로 리턴하는 값이 초기값으로 설정됨

//리턴될 결과는 state이름 과 state를 수정할 함수
```

- 컴포넌트 내부에서는 state를 수정하는 함수를 직접 생성하지 않는다
- state가 변경되더라도 함수들을 다시 만드는 작업을 하지 않는다
- 참고 https://goddaehee.tistory.com/311
- react에서도 외부 라이브러리를 이용하면 위와 유사하게 화면에 보여지는 만큼만 렌더링을 하고 나머지 데이터를 스크롤을 할 때 렌더링을 하도록 할 수 있다
  - 이는 SPA에서 굉장히 중요하다고 함
- 이게 안될 때 어쩔 수 없이 서버렌더링을 함

### react-virtualized라는 라이브러리를 이용해서 지연로딩을 구현할 수 있다

> 하나의 항목의 너비와높이를 알아야하고 목록의높이를 알아야한다 .

```
yarn add react-virtualized
```

```javascript
import { List } from "react-virtualized";

const ToDoList = ({ todos, onRemove, onToggle }) => {
  //하나의 항목을 렌더링하기 위한 함수생성

  const rowRender = useCallback(
    ({ index, key, style }) => {
      const todo = todos[index];
      return (
        <ToDoListItem
          todo={todo}
          key={key}
          onRemove={onRemove}
          onToggle={onToggle}
          style={style}
        />
      );
    },
    [onRemove, onToggle, todos]
  );

  return (
    <List
      className="ToDoList"
      width={512}
      height={513}
      rowCount={todos.length}
      rowHeight={57} //항목의 높이
      rowRenderer={rowRender} //행을 만들어주는 함수
      list={todos} //데이터
      style={{ outline: "none" }}
    />
  );
};
```

#### 데이터 불변성

> 불변성

- React에서는 props와 useState로 만든 데이터는 원본을 수정할 수 없다 .
  - React는 Virtual DOM의 개념을 사용해서 렌더링을 구현
  - React는 현재 화면의 DOM과 Memory 상의 Virtual DOM을 비교해서 수정된 부분만 다시 랜더링 하는 구조로 랜더링 속도를 향상시킨다
  - 원본에 출력한 내용을 수정하면 안된다
- 자바스크립트는 1개의 데이터를 가진 것 과 여러 개의 데이터를 가진 것이 사용할때 다른원리로 동작을한다 .

```javascript
let obj = { num: 100 };
let copyojb = obj;
// console.log(a == b);
console.log(obj === copyojb);
copyojb.num = 10;

console.log(copyojb.num);
//10
console.log(obj.num);
//10
```

- 객체나 배열은 = 로 복사하게되면 참조가 복사된다
- 동일한 데이터를 가리키게 된다
- 하나의 객체가 내부 속성을 변경하면 다른객체에게도 영향을준다

### 얕은복사

> 가장 바깥쪽 데이터를 복제

- object.assign
- spread연산자 {...객체}

```javascript
let obj = { num: 100 };
let copydata = { ...obj };
copydata.num = 300;
console.log(copydata); // num = 300
console.log(obj); // num = 100

//object.assign
let copydata = Object.assign({}, obj);
copydata.num = 300;
//결과는 위와 같다
```

- 객체 안에 존재하는 객체의 속성이나 배열 내부의 데이터를 변경하면 같이 변경됨

### 깊은복사

```javascript
//깊은복사 (deep copy) 재귀적으로 복제 하는 것
//데이터만 복제한다싶으면 깊은복제
//JSON문자열로 변환후 JSON파싱을 수행

let deepCopy = JSON.parse(JSON.stringify(obj));
copydata.ar[0] = "오잉";
//결과

console.log(deepCopy); //ar[0]="오잉"
console.log(obj); //ar[0]="a"
```

### HOC

- 참고
  - https://jiyoon-park.tistory.com/entry/React-HOC%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
  - https://velopert.com/3537
  - https://gist.github.com/velopert/3bdd08cb135587ffc481102c38134f6d
