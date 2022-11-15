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