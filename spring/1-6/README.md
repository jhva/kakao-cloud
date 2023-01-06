# json 출력방법 
- controller 클래스 요청 처리 메서드의 리턴 타입을 ResponseEntity 로 설정하고 Content-type 을 json으로 설정해서 리턴 
- jackson-databind라는 라이브러리를 사용
  - Controller 클래스의 리턴타입을 일반 자바 객체 나 List 로 설정하고 앞에 @ResponseBody를 추가하는방법
    - JSONObject 나 JSONArray 를 데이터로 넘겨준 후 MappingJacksonJsonView 를 이용해서 출력 
    - Spring4 버전 이후에는 RestController 를 생성해서 리턴 
      - 최근에 많이 사용 