# 메모 

11~15 node 시작 

### npm init (package.json)
- package name : 패키지를 배포할때 사용할 이름인데 디렉토리 이름과 같으면 안됨
- version : 버전 
- description : 설명 
- entry point : 시작하는 파일 이름 (앱의 출발점을 index.js, App.js)
- git repo : 레포 
- keywords: 패키지가 배포된경우 사용할 검색어
- license: ISC 나 MIT를 사용하는데 오픈 소스라는 의미 

## 참고 정훈 

### package.json 에대한내용
- https://asource.tistory.com/5 


### package.json 
- 이패키지가있으면 npm init install 등으로 하자 쓰잘데기없는 node_modules다삭제

### 1118이슈 
```javascript
app.use(express.urlencoded({ extended: false }))
//express.urlencoded 확인하자.....
```