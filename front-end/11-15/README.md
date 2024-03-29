# Geo Location
- 디바이스의 물리적 위치 정보를 파악하기 위한 JavaScript API

- 위치 정보 사용 가능 여부 확인
    - navigator.geolocation 의 값을 확인
- 위치 정보를 가져와서 한 번만 사용하기 
    - navigator.geolocation.getCurrentPosition(위치 정보를 가져오는데 성공했을때 호출되는 함수, 위치정보를 가져오는데 실패했을때 호출되는 함수 ,옵션)

    - 위치 정보를 가져오는데 성공했을 때 호출되는 함수에는 매개변수로 위치 정보와 관련된 객체가 전달된다.
        - coords: 
            - latitude(위도)
            - longitude(경도)
            - altitude(고도)
            - accuaracy(정확성) 
            - altitudeAccuracy(고도의 정확도)
            - heading(방향)
            - speed(속도)

    - 위치 정보를 가져오는데 실패했을때 호출되는 함수에도 매개변수가 넘어가는데 이 경우에는 에러 객체가 전달되고 code속성을 확인하면 실패한 이유를 알수있다.        

### 위치 정보를 계속 가져와서 사용하기
- navigator.geolocation.watchPosition(위치 정보를 가져오는데 성공했을 때 호출되는 함수, 위치 정보를 가져오는데 실패했을때 
호출되는 함수,옵션),로 위치 정보를 계속해서 파악하고<br/> clearWatch(변수)를 호출하면 더 이상 위치 정보를 가져오지 않습니다.

### 옵션
- enableHighAccuaracy: 정확도가 높은 위치 정보를 사용하도록 하는 옵션, 기본값은 false
- timeout : 일정 시간이 지난 데이터는 폐기하는 옵션으로 밀리초 단위로 설정
- maximumAge: 0을 설정하면 항상 최신의 데이터를 가져온다.

> Geolocation https://inpa.tistory.com/entry/JS-%F0%9F%93%9A-Geolocation-API%EB%A1%9C-%F0%9F%97%BA%EF%B8%8F-%EC%9C%84%EB%8F%84-%EA%B2%BD%EB%8F%84-%EC%96%BB%EA%B3%A0-%E2%9B%85-%EB%82%A0%EC%94%A8-%EC%98%A8%EB%8F%84-%EC%A0%95%EB%B3%B4%EB%A5%BC-%EC%96%BB%EC%96%B4%EC%98%A4%EA%B8%B0 참조링크 임의로 찾아본것들 

## 화면에 현재 위치에 카카오맵 출력해보기
```javascript
  navigator.geolocation.getCurrentPosition((pos) => {
            //위치정보
            var container = document.getElementById('map');

            var options = {
                center: new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
                level: 2
            };
            var map = new kakao.maps.Map(container, options);
            var markerPosition = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);
        }, (err) => {
            //error
        }, {
            //options
        });
```
## File API
> File 을 읽고 쓰기 위한 API

- input 타입의 file에 multiple 속성이 추가되서 이 속성의 값을 설정하면 여러 개의 파일을 선택하는것이 가능
- 텍스트 파일을 읽을 때는 인코딩 설정에 주의해야한다.
- 일반 파일을 읽을 때는 <strong>FileReader 객체를 생성 후 reader.readAsDataURL</strong> 을 호출후<br/> load 이벤트 와 err이벤트 처리
    - load 는 전부 읽었을 때 FileReader 객체의 result에 읽은 내용을 저장
    - error 이벤트는 읽기에 실패했을때 

```javascript
img.addEventListener("change",(e)=>{
            //선택된 파일의 내용읽기. 비동기로 수행이됨
            let render = new FileReader();
            //파일의 선택여부를확인하고싶으면 
            if(!img.files[0]){
                //...

            }
            
            render.readAsDataURL(img.files[0]);

            //비동기여서 콜백을해야함 
            render.addEventListener("load",()=>{
                display.src=render.result;
            })
})
```
- javascript 는 0이 아닌 숫자이거나 null 또는 undefined가 아니면 true로 간주 


### Drag And Drop API
> 브라우저 내에서 사용할 수 도 있고 외부 프로그램 과 브리우저 사이에서도 사용할 수 있음

- 외부 프로그램 과 사용할 때는 외부 프로그램에서 드래그를 하고 브라우저에 드랍을 해야한다.
    - 파일을 첨부할 때 많이 사용



### 브라우저에 데이터를 저장
- 브라우저에 데이터를 저장하는 이유
    - <b>불필요한 트래픽을 줄이기 위해서</b> 
        - 매일 앱의 경우 매번 서버에 접속해서 서버의 데이터를 받아오는 것은 자원의 낭비가 될 수 있다.
        - 맨 처음 접속 할 때는 데이터를 다운로드 받고 (파일의 존재 유무) 다음 부터는 마지막 업데이트 된
        시간을 비교해서 <b> 양쪽의 시간이 다르면</b> 데이터가 수정된 것이므로 다운로드를 받고 양쪽의 시간이 같다면
        업데이트 된 내용이 없으므로 다운로드를 받지 않도록 구현을 해야한다.

- <b>offline(오프라인)상태에서도 데이터를 사용할 수 있도록 하기 위해서</b>


### 브라우저에 데이터를 저장하는 방법
- Web Storage: Map 형태로 저장 
- Web SQL: 관계형 데이터베이스(SQlite3 - <b>외부에서는 접속이 불가능한 저용량 디비</b>) 이용 
- Indexed DB: 자바스크립트 객체 형태로 저장 - NoSQL 과 유사하다.



### Web Storage
> Local Storage :브라우저에서 저장해서 지우지 않는한 절대 삭제가되지않는 저장소

> SessionStorage: 현재 접속중인 브라우저에 해당하는 저장소로 접속이 종료되면 소멸된다.

> Indexed DB: 자바스크립트 객체 형태로 저장 - NoSQL과 유사
 - 기존에는 Cookie를 사용했는데 Cookie를 사용하게 되면 문자열만 저장할수있고 서버에게 매번 전송된다.
     - 전송 여부를 클라이언트가 결정할 수 없다.!!
            

### 데이터 저장 과 가져오기 그리고 삭제
- 스토리지.키이름 =데이터
- 저장소에 데이터가 변경되면 window 객체에 storage 이벤트가 발생하고 이벤트 객체에는 key,oldValue,newValue,url,sotrageArea 같은 속성이 만들어진다.
- Local Storage 는 전역변수 localStorage 로 사용할수있고 Session Storage는 session Storage 로 사용할수있다.
- 저장된 내용을 확인하는 방법은 브라우저의 검사 창에서 application을 확인하면된다
- 세션 스토리지 - 브라우저를 종료했을때 내용이 소멸되는지 와 현재 창에서 새창을 출력했을 때 내용이 복제가 되는지 확인
- 로컬 스토리지 - id 저장을 구현하는데 브라우저를 종료하고 다시 연결했을 때 내용이 존재하는지 여부를 확인

```javascript
   let loginform = document.getElementById("loginform");
        let loginId = document.getElementById("id");
        let pw = document.getElementById("pw");
        let loginIdSave = document.getElementById("idsave");

        //form 의 데이터를 전송할 때 (submit)
        loginform.addEventListener("submit", () => {
            //체크 여부확인
            if (loginIdSave.checked === true) {
                // 로컬스토리지에 저장
                localStorage.loginId = loginId.value;
            } else {
                delete localStorage.ids
                    
            }
        })
          //메모리 로드가 끝나면 ids ㅇ저장되어있으면 가져오기 
        window.addEventListener("load", () => {
            if (typeof localStorage.loginId != 'undefined') {
                loginId.value = localStorage.loginId;
                loginId.checked = true;
            }
        })
```

- 로컬스토리지는 보안이 중요하지않은 많은 양의 데이터를 저장하는데 용이하다
    - 예를 들어서 장바구니 구현이나 아이디 저장에 유용하게 사용할 수 있습니다.

- 동일한 패턴의 데이터가 많은 경우는 로컬 스토리지 보다는 Web SQL 이나 Indexed DB를 권장함.


# Web Worker - 처음 알게된 내용 (정훈)
> JavaScript 를 이용해서 백그라운드 처리(스레드)
- Javascript 에서는 Thread 표현 대신에 Worker 라는 표현을 사용
- HTML 과 함께 있는 Javascript 코드에서 긴 작업을 수행하게 되면 작업이 끝날때까지 다른 작업을 수행할 수 없음(UI는 아무것도 할 수 없는 상태)
- Web Worker 는 UI변경을 하지 못하고 DOM 객체 제어를 할 수 없지만 localStorage 와 XMLHttpRequest(ajax) 사용은가능 
- 사용방법 
```javascript
//web worker 생성
let 변수 = new Worker("자바스크립트 파일 경로") //워커는 별도의 스크립트 파일에 만들어야함.

//worker 와 브라우저 사이의 메시지 전송
워커변수.postMessage("메시지")=> 워커에서는 MESSAGE이벤트가 발생함
워커 파일에서는 postMessage("메시지")=> 워커 변수에 message가 발생함
```
- sendMessage는 바로 처리해달라는 요청이고 postMessage는 다른 작업이 없으면 처리해달라고 하는 요청입니다.
- message이벤트가 발생하게되면 매개변수에 data와 err를 가진 객체가 전달됨.<br/>
data는 data이고 error는 에러가 발생했을 때 에러에 대한 정보를 가진 객체이다.

- 워커는 terminate()를 호출해서 중지가 가능

### 계산작업을 대신수행해주는 워커를 생성

- worker.html에서 1억번이상시 동작이 아무것도 실행이 안되어서 이때 <br/>
worker.js파일을 하나 파일을 만들어 onmessage 함수로 data를 받은후 계산해서 postMessage<br/>에 인자로 결과를 전송해준다
- 이후 worker를 호출할 파일을 생성 (맨처음에 만들었던 worker.html)


```javascript 
* worker.html

 let num = document.getElementById("num");
        let start = document.getElementById("start");

        let worker;
        start.addEventListener("click", () => {
          if(worker){
            //워커가 만들어져있으면 중지 
            worker.terminate();
          }
          //워커 생성 
          worker = new Worker("worker.js");
          worker.postMessage(Number(num.value));
          //워커가 결과전송시 

          worker.onmessage=(e)=>{
            //워커가 전송한 데이터 출력
            alert("합계:"+e.data);
          }
        })

// ==================================================================
* worker.js

//html에서 요청이 오면 
onmessage = (event) => {
    //html에서 전송한 데이터 받기
    let num = event.data;
    let result = 0;

    for(let i =1; i<=num; i++){
        result+=i;
    }

    postMessage(result);
}
```

# Application Cache
> 리소스의 일부분을 로컬에 저장하기 위한 기능

- 사용하게되면 오프라인 브라우징이 가능해지고 리소스를 빠르게 로드할수있고 <br/>
서버에 부하를 감소시킬수있다.

    - css나 js 그리고 이미지파일등을 캐싱(눈으로보여지는 거에는 아무 변화가 없음.)


# Communication

### Web Push
> server sent Events: 클라이언트의 요청 없이 서버가 클라이언트에게 전송하는 것
- 주로 사용하는 기능 
    - 알림 
- Apple Server가 보내는 Push를 APNS(Apple Push Notification Service) 라고 하고 Google Server 가 보내는 Push를 FCM(Firebase Cloud Message)라고 한다.


### Web Socket

- 웹에서의 TCP통신 
- 일반적인 Web 요청의 처리 방식은 Client 가 Server 에게 접속을 한 후<br/> 하나의 reqeust를 전송하고 그 request를 Server가 받으면 처리를 하고 response 를 Client에게 전송하고 접속이 끊어진다 .
    - 연속되는 작업을 처리하기 위해서 Cookie(클라이언트의 브라우저에 저장) 와 Session(서버에 저장) 이라는 개념을 도입

    - 일반적인 Web요청 (Http 나 Https)은 본문 이외에 헤더 정보를 같이 전송해야한다.
        - 작은사이즈의 데이터를 보내는 경우 오버헤드가 너무크다.(트래픽이 낭비가 너무심해짐)
    
    - Web Socket을 이용하면 헤더가 거의 없기때문에 이러한 오버헤드를 줄일 수 있음
        - 작은 양의 메시지를 자주 주고 받는 경우는 ajax나 Fetch API 보다는 Web Socket을 사용하는것을 권장
        



