# Component

> 화면을 구성하는 단위

- 클래스로 구성
  - component 라는 클래스로부터 상속을 받아서 render 라는 함수에 출력할 내용을
    리턴하는 형식으로 생성, 멤버 변수 사용이나 수명 주기 메서드를 사용하는 것이 편리

```javascript
class 이름 extends Component {
  render() {
    //출력할 내용
  }
}
```

- 함수로 구성
  - 출력할 내용을 리턴 클래로 만드는 것보다는 가볍고 속도가 빠름
    - 최근에는 함수로 구성하는 경우가 많다

```javascript
const 이름 = () => {
  //출력할 내용
};
```

### 확장자

> js , jsx , tsx

#### 만들때 주의사항

- 컴포넌트는 Root 가 1개이여야한다 .
- div 나 span <> 태그로 묶어서 표현한다
- <b>react에서는 데이터 원본을 직접 수정하지 않는다</b>
  - 다른 곳에서 넘겨준 데이터는 복제를 해서 수정한 후 다시 대입하는 형태를 취하게된다.
- react 의 출력시스템은 Virtual DOM 이라는 개념을 사용하는데 메모리 상에 DOM 을 생성해서 복재 화면에 출력된 DOM과 비교를 한후 변경된 부분만 리렌더링 하는 개념으로 독립성
  - 정훈 메모 위 말은 곧 불변성
    - 참조 https://hsp0418.tistory.com/171
    - https://gusehd66.tistory.com/entry/React-React%EA%B0%80-%EB%B6%88%EB%B3%80%EC%84%B1%EC%9D%84-%EC%A7%80%ED%82%A4%EB%8A%94-%EC%9D%B4%EC%9C%A0?category=1216213

### props 값을 전달하지 않으면 아무것도 출력이 되지않는다

- 함수형 컴포넌트안에서 컴포넌트이름.defaultProps ={props이름:값} 을 이용해서 기본값을 설정할수있다.

```javascript
//MyComponent.js

MyComponent.defaultProps = {
  name: "기본값",
};
```

### 태그 안의 내용 사용

> props.children 을 이용해서 상위 컴포넌트에서 태그 안에 삽입한 데이터를읽어낼 수 있다.

### 비구조화 할당

> 자바스크립트는 객체를 분해해서 할당하는 것이 가능

- 분해해서 할당할 때는 인덱스가 아니라 이름을 가지고 데이터를 할당함

### props 의 유효성 검사 기능

- 필수나 자료형을 설정해서 검사가 가능
- 화면 출력에는 영향이 없고 스크립트 오류를 발생시켜서 검사 창에서만 오류가 발생
- props 값을 전달할 때 문자열 아니면 {} 로 감싸야한다 .
  - 기본적으로 XML 에서는 태그 안의 속성은 문자열만 가능하기 때문이다.

```javascript
//MyComponent.js

MyComponent.propTypes = {
  name: PropTypes.string,
};
```

### class 형 props가져오기

```javascript
//MyComponent.js

class MyComponent extends Component {
  render() {
    //prop-types
    //클래스형 컴포넌트에는 props 속성이존재
    const { name } = this.props;
    return <div>{name}</div>;
  }
```

### State

> Component내부에서 읽고 쓸수 있는 값을 사용하자 할 때 사용

- 기본값을 설정할 수 있으며 setter메서드를 이용해서 수정하는 것도 가능
  - 클래스형 컴포넌트의 경우 초기값 설정은 constructor(props) 메서드에서 실행

```javascript
this.state ={state 이름: 값...}

//값 수정
this.setState({state값, ...})
```

- props 는 상위컴포넌트에서 하위 컴포넌트에게 데이터를 넘겨주는 개념

  - 하위 컴포넌트의 props는 읽기 전용으로만 사용이 가능

- setState는 개인적으로 중요하다고생각해서 찾아본 참고
  - 불변성 중요하다생각함
    - 불변성은, 메모리 영역에서의 직접적인 변경을 하지 않고, 기존의 값을 수정하지 않으면서 새로운 값을 만들어내는 것을 의미한다.
    - https://velog.io/@chloeee/TIL.-setState%EC%99%80-%EB%B6%88%EB%B3%80%EC%84%B1
    - https://velog.io/@ellie12/React-state-%EB%B6%88%EB%B3%80%EC%84%B1
  - 불변성을 지켜야하는이유
    - 얕은 비교를 통해 상태 변화를 감지하기 때문.
    - 컴포넌트 성능 최적화 작업을 위함.<br/>

### 클래스형 컴포넌트 state사용해보기

```javascript
class StateComponent extends Component {
constructor(props) {
  //상위 클래스 의 생성자 호출
  //super => 부모에 값 을 가져오는거라고생각하자
  // this.props. 해당 값을 출력해보면 props에서 보낸값을 알수있다.
  super(props);
  this.state = {
    number: 0,
  };
}
render() {
  return (
    <div>
      {this.props.name} // 해당 컴포넌트의state
      <div>{this.state.number}</div>
         <button
        onClick={(e) => {
          this.setState({ number: this.state.number + 1 });
        }}
      >
      </button>
    </div>
  )
};
```

### 생성자 외부 클래스 안에서 초기화 가능

### State 에 함수 전달

- state도 하나의 속성이고 자바스크립트는 함수도 하나의 데이터이므로 state에 함수를 전달하는 것도 가능

```javascript
...
...
<button onClick={(e)=>{
    this.setState(prevState=>{
        return {number : prevState+1}
    })
}}></button>

```

### state 설정이 끝 난 후 콜백

> setState 2번째 매개변수로 함수를 전달하면 state설정을 변경한 후 호출된는 함수를 설정할수있다

```javascript

...
...
<button onClick={(e)=>{
   this.setState({number:this.state.number+1},
   ()=>{
    console.log("값 변경")
    console.log(this.state)
   })
}}></button>
```

### useState

- 함수형 컴포넌트에서 state 를 사용하기 위한 Hook
- 이 함수를 호출하면 배열이 리턴되는데 배열의 첫번째 데이터는 현재 상태이고 , 두번째 데이터는 상태를 변경해주는 함수이다
  - 상태를 변경하는 함수를 setter라고 하고 대부분 이름은 set상태이름의 형태로 만들고 상태이름의 첫글자는 대문자
- useState에는 상태의 초기값을 매개변수로 대입한다
  - StateComponent 를 함수형 컴포넌트로 변경해서 사용할때 예시

```javascript
const StateComponent = () => {
  const [msg, setMsg] = useState(""); //함수형에서 state
  return <div></div>;
};

export default StateComponent;
```

### state 사용시 주의사항

> state에서 객체나 배열을 수정할 때는 복사본을 만들고 수정해야한다.

```javascript
const obj = { name: "adamd", age: 29 };

const copyObj = { ...obj, name: "군계" };
```

### EventHandling

- 주의사항

  - 이벤트이름은 camelCase
    - 시작은 소문자로하고 두번째 단어부터는 시작이 대문자
  - 자바스크립트 코드를 단순하게 작성하는 것이 아니고 함수 형태의 값을 전달
    - DOM요소에만 이벤트 설정

- onChange 이벤트
  - 입력 내용이 변경될 때 호출되는 이벤트
- 이벤트 처리를 위한 컴포넌트 생성

```javascript

...
...
    <h1>이벤트 연습</h1>
        <input
          value={this.state.name}
          onChange={(e) => {
            this.setState({ name: e.target.value });
            console.log(e.target.value);
          }}
          type="text"
          name="message"
          placeholder="이름을 입력하세요"
        />
```

### 관계

- is a
  - 상위 클래스의 인스턴스가 만들어 지고 하위 클래스의 인스턴스가 만들어진다
  - 하위 클래스에서는 상위 클래스에 대한 포인터가 존재함
  - 일반적으로 이 포인터의 이름을 super라고 함
- has a
  - 하나의 인스턴스 안에 다른 인스턴스를 생성하는 방식
  - 외부 인스턴스 안에서 내부 인스턴스가 생성되므로 외부에서 내부를 사용하는 것은 쉬움
  - 내부 인스턴스가 외부 인스턴스를 사용하는 것은 조금 어렵다
  - 생성될 대 외부 인스턴스의 참조를 넘겨주어야한다.

### babel 형식을 transform을 쓰게되면 굳이 바인딩을하지않아도 된다

```javascript
...
...

//기존 transform -class-properties를 사용하지않을때

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  handleChange(e) {
    this.setState({
      message : e.target.value
    });
  }

  handleClick = () => {
    alert(this.state.message);
    this.setState({
      message : ''
    });
  }

  //사용할때
this.state={
    name:'',
}
  handleClick = (e) => {
    alert(this.state.name);
    this.setState({
      name: e.target.value,
    });
  };

  render() {
    return (
      <div>
        <h1>이벤트 연습</h1>
        <input
          value={this.state.name}
          type="text"
          name="message"
          placeholder="이름을 입력하세요"
          onChange={(e) => {
            this.setState({
              name: e.target.value,
            });
          }}
        />
        <button onClick={this.handleClick}>
          스테이트 에 저장된 name을 다이얼로그에 출력
        </button>

```

- 불필요한 코드량을 줄일수있다 .

### EventRouting

- 하나의 함수로 여러 DOM의 이벤트를 처리하는것
  - 이벤트 처리 함수의 매개변수로 Event객체가 전달되는데 이벤트 처리 함수의 매개변수의 target속성을
    호출하면 이벤트각 발생한 객체의 참조를 리턴한다
  - 이벤트가 발생한 객체의 참조를 가지고 분기문을 만들어서 이벤트 처리에 코드를 작성하면 하나의 함수를 ㅇ가지고 여러 DOM의 이벤트 처리가 가능
  - name 이나 id속성 등을 적절히 이용

```javascript
...
...
  handleChange = (e) => {
    //이벤트가 발생한 객체는 target
    //e.target.name은 이벤트가 발생한 객체의 name
    this.setState({
      [e.target.name]: e.target.value,
    });
  };
```
