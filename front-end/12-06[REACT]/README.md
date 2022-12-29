# 배열.map 변환

> 배열의 데이터를 순회하면서 매개변수로 받은 함수를 요소단위로 수행한 후 그 결과를 모아서 다시 배열로 리턴하는 함수

- map 에 설정하는 매개변수
- 첫번재는 callback 함수 (필수)
  - 매개변수는 3개까지 될 수 있고 반드시 하나의 데이터를 리턴해야한다
    - 첫번재는 매개변수는 순회하는 각 요소
    - 두번째 매개변수는 인덱스
    - 세번째 매개변수는 배열 그 자체
- 두번째 callback 함수 내부에서 사용할 this참조

### filter

> 매개변수가 1개이고 boolean을 리턴하는 함수를 매개변수로 대입하는데 함수의 결과가 true인 데이터만 모아서 다시 배열로 리턴하는 함수

```javascript

  handleRemove = (index) => {
    const { names } = this.state;
    // this.setState({
    //   names: [names.slice(0, index), names.slice(index + 1, names.length)],
    // });
    this.setState({
      names: names.filter((item, e) => e !== index),
    });
```

# Life Cycle

> 인스턴스가 생서되고 소멸될 때 까지의 과정

- 생성은 생성자가 담당하고 소멸은 소멸자가담당한다
- IoC(제어의 역전)
  - 클래스는 개발자가 생성하지만 인스턴스의 생성과 소멸은 개발자가 하지 않고 다른 프레임워크 나 컨테이너 등 이 하는 형태 가 적용되는 경우에는 특별한 경우가 아니면 생성자를 이용해서 인스턴스를 생성하지않음
  - IoC가 적용되면 일반적으로 생ㅇ성자를 직접 호출하지 않기 때문에 수명 주기 관련 메서드를 이용햇 ㅓ생성과 소멸될 때 작업을 수행

### React의 Component 의 수명주기

- Mount(컴포넌트가 메모리 할당을 받아서 출력)-> update(컴포넌트 정보를 업데이트 하는 것으로 리렌더링) -> UnMount(컴포넌트가 화면에서 제거)

### Mount 될 때 호출되는 메서드

- constructor : 생성자
  - state 초기화를 수행
  - 가장먼저 호출
- getDerivedStateFromProps
  - props에 있는 값을 state동기화 할 때 호출
- render
  - 렌더링할 때 호출되는 메서드로 this.props 와 this.state를 이용해서 prop와 state접근이 가능
- componentDidMount
  - 화면에 보여지고 난 후 호출되는 메서드, 비동기 작업(네트워크 사용이나 타이머작업수행)

### update 할 때 호출되는 메서드

- getDerivedStateFromProps
  - props 에 있는 값을 state에 동기화 할때 호출
- shouldComponentUpdate
  - 리랜더링을 결정하는 메서드로 여기서 false를 리턴하면 리렌더링 되지 않음
- render
- getSnapshotBeforeUpdate
  - 변경된 내용을 DOM에 적용하기 직전에 호출되는 메서드
- componentDidUpdate

### Unmount 될 때 호출되는 메서드

- componentWillUnmount
  - 사라지기 전에 호출되는 메서드
  - 메모리 leak(대표적으로 타이머)이 발생할 수 있는 객체의 제거 작업을 수행

### 라이프 사이클 이용 시 주의 할 점

> 리액트의 개발모드가 React.StrictMode 로 설정되어 있으면 개발 환경에서는 Mount를 2번씩한다

- StrictMode
  - 에러를 발견해줄수있는 부분

### 에러를 화면에 출력

- 에러를 발생시키기 위해서 Iteration.jsx 파일에서 없는 프로퍼티를 호출

```javascript
...
...

 state = {
    error: false,
  };
  componentDidCatch(error, info) {
    //컴포넌트 예외처림 ?
    this.setState({
      error: true,
    });
    console.log({ error, info });
  }
  render() {
    if (this.state.error) {
      return <div>에러가 발생했다십라아</div>;
    } else {
      return this.props.children;
    }
  }
```

# Hook

> React.16.8 버전에 추가

- class Component 의 기능을 Function Component에서 사용할 수 있도록 해주는 기능

### useState

> state 를 함수 컴포넌트 안에서 사용할 수 있도록 해주는 hook

- useState 의 매개변수는 state의 초기값이 되고 리턴하는 데이터는 state와 state의 값을 변경할 수 있는 setter 함수의 배열
- 함수형

```javascript
const FunctionState = () => {
  const [count, setCount] = useState(0);
  return (
    <div>
      클릭을 {count}q번 수행
      <button
        onClick={(e) => {
          setCount(count + 1);
        }}
      >
        버튼
      </button>
    </div>
  );
};
```

- 클래스형

- 정훈 갑자기 문득 생각이난 부분 (이해가안됐음)
- 참조 https://developer-talk.tistory.com/136
- 참조2 https://1995-dev.tistory.com/107?category=732601
  - state초기화
  - constructor를쓰는이유
  - super를 쓰는이유 괄호안에 props가 왜 들어가야할까 ?
    - 생성자 내부에서 this.props를 사용하기 위해서입니다
- super ?
  - 자식 클래스가 생성될 때, 부모 클래스의 생성자를 참조합니다
    - 여기서 부모클래스는 React.Component를 말한다 .
    - 여기서 중요한 점은 JavaScript에서 super()를 선언 전까지 constructor() 안에 this 키워드를 사용할 수 없습니다.

```javascript
class ClassCate extends Component {
  //생성자를 만들지 않고 이렇게 state를 초기화해도 된다
  state = {
    count: 0,
  };

  constructor(props) {
    super(props);
    this.state = {
      count: 0,
    };
  }
  render() {
    return (
      <div>
        클릭을 {this.state.count}q번 수행
        <button
          onClick={(e) => {
            this.setState({
              count: this.state.count + 1,
            });
          }}
        >
          버튼
        </button>
      </div>
    );
  }
}
```

### useRef

> ref -> 컴포넌트를 조작하기 위해서 붙이는 일종의 id 와 같은 변수

- useRef 로 만들어진 변수는 값이 변경된다고 해서 컴포넌트가 리랜더링 되진않음

- useRef(초기값) 으로 변수를 생성하고 컴포넌트 나 DOM에 설정할 대는 ref속성에 생성된 변수를 대입해주면 된다

### useEffect

> state가 변경된 후 수행할 side effect 를 설정하는 Hook

- LifeCycle 중에서 componentDidMount와 componentDidUpdate
- ComponentWillUnmount가 합쳐진 형태

```javascript


class ClassEffect extends React.Component {
  constructor(props) {
    super(props);
    console.log("생성자 - 가장 먼저 호출되는 메서드");

    this.state = { count: 0 };
  }

  componentDidMount() {
    console.log("마운트 된 후 호출되는 메서드");
    document.title = `you clicked ${this.state.count}time`;
  }
  // 컴포넌트가 update된 후 호출되는 메서드
  componentDidUpdate() {
    console.log("업데이트 된 후 호출되는 메서드");
    document.title = `you clicked ${this.state.count}time`;
  }
...
...
...

/*
output :
생성자 콘솔
마운트 된후 호출되는메서드 콘솔
마운트 된후 호출되는 메서드 호출
*/
```

- 위 구문에서 마운트 된 후 호출되는 메서드가 두번 찍히는이유는 이는 개발환경에서 strict mode가 적용되면 이렇게된다고함 .

### useEffect 함수

> useEffect(()=>{수행내용},[]) 이런형태 의 원형

- deps 배열을 생략하면 mount 된 경우 와 모든 state가 변경될 때 마다 호출
- deps 배열을 비워두게 되면 ([]) Mount된 후에만 호출
- deps 배열에 state를 설정하게 되면 state값이 변경될 때도 호출

- 수행할 내용에서 함수를 리턴하면 cleanup함수 가된다
- cleanup이란 ?
  - unMount될 때 호출되는 함수
    - 정리 작업을 할때

### 객체 배열을 함수형 컴포넌트로 출력하고 삽입,삭제,갱신을 수행하기

- 하나의 객체를 출력할 컴포넌트와 객체 배열을 출력할 컴포넌트를 작성

```javascript
const User = ({ users }) => {
  return (
    <div>
      <h1>User</h1>
      <h2>UseList</h2>

      {users.map((user) => (
        <UserList key={user.id} user={user} />
      ))}
    </div>
```

### 데이터 추가구현

> 추가 화면에 해당하는 컴포넌트 생성

```javascript
const onCreate = (e) => {
  const user = {
    id: nextId,
    usernickname,
    email,
  };
  setUsers([...users, user]);

  setInputs({
    email: "",
    usernickname: "",
  });
  nextId.current += 1;
};
```

```javascript
const onChange = (e) => {
  setInputs({
    ...inputs, //원본배열 항상생각하기
    [e.target.name]: e.target.value,
  });
};
```

### 데이터 삭제구현

```javascript
const onRemove = (id) => {
  //ㅕuser state 에서 id가 id인 데이터 삭제
  // id가 일치하지 않는 데이터만 삭제
  //실제로는 id가 일치하지 않는 데이터만 가지고 배열을 만들어서 수정한다.
  setUsers(users.filter((user) => user.id !== id));
};
```

### 배열의 데이터 수정

- 배열의 데이터수정
  - 계정 이름을 클릭하면 active 속성 값을 toggle(반전) 시켜서 글자 색상르 변경하도록하기

```javascript
const onToggle = (id) => {
  setUsers(
    users.map((user) =>
      user.id === id ? { ...user, active: !user.active } : user
    )
  );
  //active 속성의 값을 반전하는것
  // setUsers(
  //   users.map((user) => {
  //     user.id === id ? { ...user, active: !user.active } : user;
  //   })
  // );
};


...
...
//UserList.js
const UserList = ({ user, onRemove, onToggle }) => {
  return (
    <div>
      <b
        style={{ cursor: "pointer", color: user.active ? "tomato" : "red" }}
        onClick={(e) => {
          onToggle(user.id);
        }}
      >

```

### userEffect 활용

```javascript
useEffect(() => {
  console.log("화면에 나타남");
  return () => {
    console.log("화면에 사라짐");
    console.log(user);
  };
}, [user]);
```

### useMemo

> 연산된 값을 재사용하는 hook

- 성능 최적화를 위해서 사용
- 매개변수로 연산을 수행하는 함수 와 배열을 대입받는데 배열에 변화가 생긴 경우에만 연산을 수행하는 함수를
  수행하고 그렇지 않은 경우는 함수를 호출해도 결과만 다시 제공한다

```javascript
const countActiveUser = (user) => {
  console.log("사용자 개수세기 ");
  return user.filter((user) => user.active).length;
};
const count = countActiveUser(users);
```

- 위구문처럼 하였을때 input의 내용을 치면 콘솔이 계속 뜨게된다 그이유는
  state가 변경될때마다 리렌더링이되기때문이다 참고하자
- 위 내용의 해결책이 useMemo

```javascript
const count = useMemo(() => {
  countActiveUser(users);
}, [users]);
//이런식으로 하였을경우 리렌더링이 더 이상안된다
```

### useMemo를 사용했을때 정훈 궁금한것 (리렌더링에대해서 ..)

> 참고 https://seungddak.tistory.com/109, https://velog.io/@eunbinn/when-does-react-render-your-component

- state가 변경될때
  - state 값을 바꿔주기 위해서는 state 를 직접 조작해서는 안되고 setState() 메서드를 이용해 주어야한다. 왜냐하면 리액트는 state 의 변경이 감지되면 리렌더링을 해주는 데 메서드를 사용하지 않고 직접 바꿔주게 되면 리액트가 state 의 변경을 감지하지 못하게 된다.
- 새로운 props가 들어올때
  - 전달받은 props 값이 업데이트 됬다면 리 렌더링 된다.
- 부모 컴포넌트가 렌더링 될 때
  - 새로운 prop 이 들어오지 않더라고 부모컴포넌트가 리렌더링 된다면 자식컴포넌트 역시 리렌더링이 된다.
