### React children에대하여 정훈 ..

> 참고 https://velog.io/@hyunjoogo/React-children-Component%EC%97%90-props-%EC%A0%84%EB%8B%AC%ED%95%98%EA%B8%B0

### useCallback

- 특정 함수를 새로 만들지 않고 재사용하고자 할 때 사용
- 컴포넌트에 구현한 함수들은 컴포넌트가 랜더링될 때 마다 다시 생성
- 컴포넌트가 많아지고 랜더링이 자주 발생하면 함수들을 다시 만드는 것은 비효율적이 될 수 있습니다.
- useCallback을 이용하면 데이터가 변경된 경우에만 함수를 다시 만들도록 할 수 있습니다.
- 첫번째 매개변수는 함수이고 두번째 매개변수는 데이터의 배열입니다.

### reduce

```javascript
// reduce는 배열을 순회하면서 연산을 수행한 후 하나의 값을 리턴
// 매개변수는 2개인데 첫 번쨰 매개변수는 수행할 함수이고
// 두 번쨰 매개변수는 연산을 시작할 때의 초기값이다.
// 두 번쨰 매개변수를 생략하면 배열의 첫 번째 요소로 설정
// 첫 번째 매개변수인 함수는 매개변수를 4개까지 갖는데
// 첫 번째는 누적값이고 두 번쨰는 배열의 요소
// 세 번쨰는 배열의 인덱스이고 네 번쨰는 배열
const sum = numbers.reduce((acc, cur) => acc + cur);
```

# SCSS 라이브러리

- SCSS가 이미 적용된 라이브러리
- 반응형 웹 디자인 (디바이스의 크기에 상관없이 동일한 콘텐츠를 사용할 수 있도록하는 것)
  - include-media

### CSS Module 사용개요

- CSS를 불러와서 사용할 때 클래스 이름을 고유한 값으로 만들어서 적용
- [파일이름]_[클래스이름]_[해시값] 을 추가해서 클래스 이름을 부여
- 사용하는 방법은 css 파일의 확장자를 .module.css 로 만들면 됩니다.
- 이 기능을 사용할 때는 css 파일의 클래스 이름을 일반적인 이름으로 사용하면 됩니다.
- 별도의 클래스 이름을 부여하지 못하도록 하고자 하는 경우에는 클래스 이름 앞에 :global을 추가하면 됩니다

### classnames 라이브러리

> CSS 클래스를 조건부로 설정할 때 유용한 라이브러리로 여러 클래스를 설정할 때 편리

- 설치

```
yarn add classnames(npm install classnames)
```

- classNames('one', 'two'): 2개 설정
- classNames('one', ['two', 'three']): 3개 설정
- classNames('one', {'two':true}): two 적용
- classNames('one', {'two':false}): two 적용 안됨

### CDN

> 서버 와 사용자 사이의 물리적인 거리를 줄여서 콘텐츠 로딩에 소요되는 시간을 최소화하기 위해서 동일한 콘텐츠를 여러 네트워크에 분산 저장해서 요청을 하면 가장 가까운 Network에서 다운로드 하도록 해주는 서비스

### Fetch API

> Promise 를 이용하는 API

- 여러가지 api

```javascript
//fetch
fetch("https://jsonplaceholder.typicode.com/users")
  .then((res) => {
    console.log(res);
    res.json();
  })
  .catch((err) => console.log(err, "err"));
//
let req = new XMLHttpRequest();
req.open("GET", "https://jsonplaceholder.typicode.com/users");
//post방식일때는 send에 파라미터 대입해야함
req.send("");
req.addEventListener("load", () => {
  let data = JSON.parse(req.responseText);
  console.log(data);
});
req.addEventListener("error", (err) => {
  console.log(err, "err");
});
//axios
axios
  .get("https://jsonplaceholder.typicode.com/users")
  .then((res) => {
    console.log(res.data);
  })
  .catch((err) => {
    console.log(err, "err");
  })
  .finally(() => {
    console.log("무조건실행");
  });
```

### 서버를 수정할 수 없을때 proxy설정

> package.json 파일에 설정을 추가

```javascript
"proxy":"서버의 도메인"
```

### 라이브러리를 이용

```
yarn add http-proxy-middleware
```

```javascript
//node
const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = (app) => {
  app.use(
    createProxyMiddleware("클라이언트 공통 URL", {
      target: "서버의 url",
      changeOrigin: true,
    })
  );
};
```
