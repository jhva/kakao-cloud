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