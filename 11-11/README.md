# DOM 객체 찾아오기 

- getElementById(아이디) : 아이디에 해당하는 객체를 찾아와서 리턴 
- getElementssByName(이름): 배열로 리턴
- getElementsByTagName:배열로 리턴
- getElementsClassName: 배열로 리턴



### DOM 객체의 공통 속성 과 메서드
- style 속성 : 스타일 지정하기위한 속성
- innerHTML  속성: 태그와 태그 사이의 텍스트와 관련된 속성
- value 속성 : 입력된 데이터 확인 이벤트 와 함께 많이 사용
- DOM 객체마다 속성은 다르다 

# Event Handling

> 사용자가 발생시는것도 있고 시스템이 발생시키는 것 도 있음

```javascript
  document.getElementById('btn').onclick =function (){
            alert("고전적 이벤트 처리 모델")
        }
  document.getElementById("btn2").addEventListener("click",()=>{
            alert("표준 이벤트 모델")
        })
```

 - Event Handling : 이벤트가 발생했을때 수행할 동작을 설정 
   - 이러한 Event Handling 을 수행하는 객체를 Event Handler 또는 Listener 라고 한다 
   
<br/>


## Javascript 이벤트 처리방법
- inline : 태그 안에서 이벤트 속성에 스크립트 코드를 직접 작성하는것 - 권장하지않음 

- 고전적 이벤트 처리 모델 : 객체.이벤트이름 

- 표준 이벤트 모델: 객체.addEventListener("이벤트이름",함수 이름이나 함수를 만드는 코드)
  - 표준 이벤트 모델에서는 이벤트 이름 앞에 on 을 붙이지않음
    하나의 이벤트에 여러 함수를 연결하는 것이 가능하고 이벤트 처리 코드를 제거할 때는 removeEventListener를 호출하면 된다



## 이벤트 객체 

- 이벤트를 처리하는 함수에는 event객체 1개가 전달된다 .

- 이벤트 처리 함수 내에서는 this는 이벤트가 발생한 객체이다.<br/> 전에는 많이 사용했는데 최근에는 화살표 함수를 사용하기 때문에 잘 사용하지않는다.<br/> this 대신에 Document 객체를 이용해서 DOM 을 찾아와서 사용합니다 .


- 속성
  - data: Drag & Drop 을 할때 Drop된 객체들의 url을 문자열 배열로 반환 
  - height
  - layerX,layerY
  - modifier: 같이 누른 조합키 (ALT,CTRL,SHIFT 나 마우스 왼쪽, 오른쪽 판별)
  - pageX,pageY
  - target:이벤트가 전달된 객체
  - type :이벤트의 타입
  - which: 마우스 버튼의 ASCII 코드 값이나 키보드의 키값(window.event.keyCode)
  - width
  - x
  - y

## Event Trigger
<br/>

> 이벤트를 강제로 발생시키는 것

```javascript
   document.getElementById("btn").addEventListener("click", (e) =>{
   //문단 태그의 강제로 클릭이 발생하도록 강제로 이벤트 발생시키기 
    document.getElementById("paragraph").click();
        })
```
- 객체.이벤트이름 ()


## Default Event Handler  

> 기본기능을 수행하지 않도록 할 때는 event객체의<br/> <b>preventDefalut()</b>를 호출

- 특정한 태그 들에는 기본적인 이벤트 처리 코드가 포함되어 있습니다 .
  - a 태그는 다른 URL이나 책갈피로 이동


- input type
   - type submit이면 form 의 action으로 요청을 전송
   - type reset 이면 form 을 clear
   - button 이 form 안에 존재하면 submit 기능을 수행
   - keydown 이 발생하면 누른 키를 input 에 출력


## Event Bubbling


> 부모 와 자식 태그 관계에서 양쪽에 동일한 이벤트에 대한 핸들러가 존재하면 자식 태그에서 이벤트가 발생하면 자식 태그의 핸들러를 수행하고 부모태그의 핸들러도 수행한다 .

- 이때 이벤트 버블링을 막고자 하면 이벤트 객체의 stopPropagation 메서드를 호출하면 된다.,



```javascript
<form action="login" method="post">
        <p>
            아이디
             <input type="text" name="id">
        </p>
</form>
  * action = 처리할 서버  url 
  *  id = 클라이언트안에서
  *  name = 서버가 처리할 name 

```



# 여러종류의 이벤트
> keydown, keypress, keyup
- which속성을 이용해서 누른 키의 ASCII 코드 값을 찾아올수있고 code<br/> 로 상수 형태로 가져올 수 있고 key로 문자를 가져올수있다.

  - <b>이벤트 종류 : mousemove, mouseout, mouseover, mouseup</b>
  <br/>

- click ,dbclick
  - 누른 좌표는 screenX 와 screenY

- focusin(focus), focusout(blur)
  - focusout 이벤트에서 유효성 검사를 하기도 한다.
  - load: 메모리 적재 
- window에서는 body에 있는 태그를 전부 읽어서 메모리에 적재하면 호출되는 이벤트 
  - img 나 file 의 경우는 내용을 전부 읽었을 대 호출되는 이벤트
  - ajax에서는 서버에서 응답을 받았을 때

> beforeunload: 브라우저의 내용이 사라지기 직전
- 세션을 이용해서 로그인 처리를 하는 경우 로그아웃을 할 때 세션을 초기화하는데 브라우저 창을 닫아 버리면 세션 초기화를 하지 못하는 경우가 있는데 이 이벤트를 이용해서 브라우저가 닫히는 시점을 찾아서 세션을 초기화하면 된다 .
> chage: 값이 변경될 때 
- 비밀번호 같은 것을 변경할 때 메시지를 출력하는 형태로 많이 이용한다. 

```javascript
  <script>
         window.addEventListener("load",(e)=>{
            document.getElementById("display").innerHTML="display"
            for(let i = 0; i<30; i++){
            document.getElementById("body").innerHTML+="<h1>무한스크롤</h1>"
            } 
            ///30개가 나옴 무한스크롤이 
            window.addEventListener("scroll",(e)=>{
                alert("스크롤")
                //스크롤시 alert창에 스크롤이라고 나오는현상
            })
        })
  </script>
   //파일을 전부 읽어서 그들을 전부 메모리에 적재한 후에 발생
```




# 비동기 처리 

## 동기(Synchronous) 와 비동기(Asynchronous)
> 동기 : 순차적으로 처리 
- 하나의 처리가 끝나야 다음을 처리하는 형태

> 비동기 : 하나의 처리가 끝나기 전에 다른 처리를 할 수 있는 형태
- 이 와 유사한 형태를 스레드를 만들어서 처리하는 것
  - 시간이 오래 걸리는 작업의 경우 작업을 순차적으로 처리하게되면 뒤의 작업이 너무 오래 기달려야하고 <br/>
  동일한 자원을 사용하지않는 작업을 순서대로 처리하게 되면 자원의 낭비가 발생하게 된다

> <b>알림을 어떻게 줄 것인가를 고민해야함 .</b>

  - 비동기로 동작한 작업이 끝나고 난 후 작업을 어떻게 할 것인가 하는 문제 
    - 방법: callback함수 && Promise && async/await 


# callback function
> 상태 변화가 생기면 호출되는 함수를 callback function 이라고 합니다.

- 비동기 처리는 언제 작업이 종료될 지 알수 없기 때문에 작업이 종료되면 다른 작업을 수행할 수 있도록 콜백 함수를 등록 할 수 있게해준다.
- 브라우저가 html 파일을 전부 읽어서 메모리에 적재를 하고 나면 함수를 호출함 .
  - 함수는 로드가 끝나면 호출되기때문에 콜백 함수라고히고, 모든 이벤트 처리는 비동기 방식이다.

## 비동기 처리를 수행해야 하는 경우 

- 입출력 작업
  - 파일에 읽고 쓰기
  - 서버에 요청하고 응답을 받는 것 

- Processor(작업 수행 - CPU,GPU) <-> Main Memory(RAM)<->I/o (키보드/모니터/NIC-네트워크)<-> 보조기억장치(디스크..)\

### 암호화/복화화 작업
- 오랜 시간이 걸리기 때문에 비동기적으로 처리하는 것을 기본으로 한다.


# Promise
> 비동기 처리를 수행한 후 다음 작업을 진행하고자 할 때 callback 함수를 이용할 수 있다.

- callback을 이용하는 경우 2가지 문제점이 발생할 수 있음
    - 문제점1. 가독성이떨어질수있음 -> callback안에서 다시 비동기 작업을 하는 경우 callback 안에 callback이 계속 만들어지는 상황이발생.

- 예외처리의 한계 - 콜백 안에서 예외가 발생하면 콜백 외부에서는 처리할 수 없음.


```javascript
  const promise = new Promise((res,rej)=>{
    비동기 작업을 수행 
    if(비동기 작업 수행에 성공했다면){
      res();
    }else{ㄴ
      reject();
    }
  })

```

### chainning(연쇄적으로 동작)가능 
```javascript

  Promise
          .then(수행할작업)
          .then(수행할 작업)
          ...
          ...

          .catch(에러가 발생했을 떄 수행할 작업)
then 의 개수는 무제한이고 
catch 는 1번만나와야함
```
- 앞에서 수행한 작업의 리턴되는 데이터가 다음 수행할 작업의 함수의 매개변수로 전달된다.