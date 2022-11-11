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
<br/>

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