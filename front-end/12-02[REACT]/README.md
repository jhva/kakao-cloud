# REACT 

### react
- 유저 인터페이스를 만드는데 사용할 수 있는 자바스크립트 라이브러리 
- SPA(Single Page Application)구현을 위해서 사용하는 경우가 많다 
    - 이 목적으로 만들어진 라이브러리로는 angular.js,vue.js도 있다

### component
- 특정 부분의 모양을 결정하는 선언체
- 템플릿 엔진은 데이터 셋과 HTML을 가지고 HTML을 재생성해서 보여주는 용도로만 사용.
    - 컴포넌트 많은 기능을 내장한 재사용 가능한 개체

### DOM(Doucument Object Model)
> 자바스크립트 내장 객체보다 처리 속도가 느림
- react 에서는 Virtual DOM 을 이용해서 출력할 내용을 메모리에 만든 후 데이터가 변경이 되면 VIrtual DOM 에 적용을 하고 실제 DOM 과 비교를 한 후 변경되는 부분만 수정해서 출력하는 형태로 동작해서 빠르게 출력을 한다.

- 템플릿 엔진은 데이터가 변경이 되면 변경된 데이터 와 HTML 을 가지고 다시 화면을 만들어서 출력을 한다 .
- 이 방식이 게임 엔진이 화면 출력을 만드는 방식이다.

- MVC(Model View Controller),MVVM(Model VIEW ViewModel),MVW(Model View Whatever), MVP(Model View Presentation) 등의 구조를 위한 프레임워크 와 view 만을 위한 라이브러리
- ajax러리를 위한 axios 나 fetch API 와 같은 데이터를 가져오기 위한 라이브러리 와 redux 와 같은 라이브러리들을 같이 학습하게 된다.

### 개발 환경 설정
- node 설치 
- npm 설치 
    - npm 은 node의 패키지 관리자로  node를 설치하면 자동으로 설치가 된당.
- yarn 
    - npm 을 개선한 패키지 관리자 
    - npm 보다 속도가 빠르며 더 나은 캐싱 시스템을 사용 
- 설치 및 설치확인
```
 npm install --location=global yarn 

 * 확인
 yarn --version
```

### webpack
> 프로젝트에 사용된 파일을 분석해서 웹 문서 파일로 변환해주는 도구 
- 웹 브라우저는 js 와 css 그리고 html을 해석할 수있는데 ,
프레임워크 나 라이브러리를 사용하다보면, 이러한 확장자 이외의 파일을 만들어서 사용하는 경우가 있는데 이경우 webpack 이  css 나 html로 변환해줌 

### babel 
> 대다수의 브라우저들은 ES6 (ECMA 2015) 버전의 자바스크립트 문법 까지는 적용할 수 있는데 그 이상 버전의 문법은 이해하지 못하는 경우가 있을 수 있어서 babel이 이러한 코드들을 ES6 이하의 문법으로 변환해주는 
<b>Trans Compiler</b>라고 한다.

- 컴파일러
    - 소스코드를 운영체제나 virtual machine등을 이해시킬려고 


### IDE
> Visual Studio Code 와 같은 IDE 필요 
- 여러가지 확장 프로그램 설치 


### 형상 관리 도구 설치 
> git 이 대표적인 형상 관리 도구 

### 디버깅을 위한 도구 
> 크롬 확장 프로그램 - React Developer Tools

### React 프로젝트를 만들기 위한 애플리케이션 설치
```
yarn global add create-react-app

*생성

create react-app 애플리케이션이름 

이렇게 사용함 난 
npx create-react-app 애플리케이션이름 
```

### JSX 
> javascript XML 의 약자로 Javascript 에 XML 을 추가한 확장형 문법으로 react 프로젝트에서 사용하는 문법 `
- 브라우저에서 실행할 때 Babel이 자바스크립트 코드로 변환을 해서 실행

- 장점 
    - 보기 쉽고 익숙
    - 코드 작성 중 오류가 있으면 Babel이 코드를 변환하는 과정에서 이를 감지
    - HTMl 태그 와 Component를 혼용해서 개발하는 것이 가능

### 규칙 
- 반드시 하나의 부모 요소로 시작해야한다.
    - 루트는 반드시 하나여야 한다.

- 태그는 반드시 닫아야 한다 .
    - 닫는 태그를 사용하던가 아니면 빈태그처럼 <태그/> 으로 처리를 해주어야함.
- 자바스크립트 내용을 출력하고자 하는 경우에는 
{} 를 사용해야함다 
- 자바스크립트는 boolean 이외의 데이터도 논리 연산 (||,&&) 이 가능 
    - 0이 아닌 숫자 나 null 이나 undefind가 아니면 true로 간주
    
- 스타일을 적용할 때는 객체 형식으로 설정 
    - 문자열로 설정하지 않음 
    - 모든 스타일 속성은 camel case를 이용 
    - 스타일에서는 - 가 들어가는 경우 이름이 변경됨 .
- class 속성 대신에 className 이라는 속성이용
```javascript 
//변수선언해서 적용해보기
const style = {
    color:'aqua',
    backgroundColor:'black'
}

..
..
return (
    <>
    <h1 style={style}> helow reat</h1>
    </h1>
)


//style  class속성
//app.css
.styleClass{
    background:aqua;
    color:black;
}

// app.js 

..
..
return (
    <h1 className="styleClass" ></h1>
)

```

# React Component 
>  화면을 구성하는 재사용 가능한 모듈 
- View 는 전체화면을 의미하는 경우가 많고 Component 는 전체 화면의 일부분을 의미한다
    - View 는 전체 화면을 만드는 개념이라서 재사용성이 떨어짐 
- 확장자는 이전에는 js 를 많이 사용했지만 최근에는 명확하게 구분하기 위해서 jsx 나 tsx를 사용하는경우가 많다
- 만드는 방식
    - 클래스 형 컴포넌트 
        - 초창기
    - 함수 형 컴포넌트 
        - 최근
- 화살표 함수에서 매개변수가 1개이면 ()생략이 가능

```javascript
(e)=>{..내용} //(o)
e=>{..내용}//(o)
```

- 화살표 함수에서 return 은 return 을 생략하면 마지막 문장의 결과가 리턴된다. 


### 클래스 형 컴포넌트 
- 생성
```javascript
class 컴포넌트이름 extends Component {
    render(){
        //출력할 내용 리턴 
    }
}
```
- 장점 
    - Life Cycle 더 명시가 좋음 
        - 함수는 호출하면 안의 내용을 수행하고 호출한 곳으로 리턴
        - 인스턴스는 <b>한 번 만들어지면 소멸시키기 전까지 존재하고</b> 생성될 때 생성자와 같은 메서드가 호출되기 때문에 수명주기에 따른 작업이 수월하다
        - 소멸시키기 전까진 메모리에 남아있는다 .
    - 내부에 메서드 구현 가능