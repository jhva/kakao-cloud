```javascript

 split

 var uri ="abc/def";
 var ar = url.split('/');


```


### 문자열변환
- 패턴을가지고 분할해서 배열로 생성: split

- 위치를 가지고 분할해서 문자열로 생성 : substring

> substring은 매개변수가 1이면 그 이후를 전부 잘라서 리턴 ..
매개변수가 2개이면 앞은 시작하는 위치 뒤는 끝나는 위치인경우도있고 개수인경우도있음


### template data 

```javascript 

var data =4332;
console.log(`${data} 입니다`);
// 결과 4332입니다 

```



### date

- 날짜 와 관련된 클래스로 자바에서 그대로 가져옴 
  - new Date() : 현재 날짜 및시간 
  - new Date(년,월,일,시,분,초,밀리초):입력한 데이터를 가지고 날짜 및 시간을 만들어내는데 월은 -1 을 해서 설정해야함
  - new Date(정수):정수는 1970년 1월1일 자정 에서 지나온 시간을 밀리초 단위로 설정 
- getTime 메서드를 이용하면 1970 년 1월 1일 자정 이후로 지나온 시간을 밀ㄹ리초 단위로 리턴


## 예외 (정훈) 
- 1000 밀리초 = 1초 
- 1일은 1000* 60*60* 24
- 1분은 1000*60 
- 1시간은 1000* 60*60 

계산할 날의 밀리초 / (1000 * 60 * 60 * 24) = 일

(계산할 날의 밀리초 / (1000 * 60 * 60)) % 24 = 시

(계산할 날의 밀리초 / (1000 * 60)) % 60 = 분

(계산할 날의 밀리초 / 1000) % 60 = 초

초 단위로 계산하는법은?

 

1일은? 1 * 60 * 60 * 24 = 86400 초

1시간은? 1 * 60 * 60 * 60 = 3600 초

1분은? 1 * 60 = 60초

1초는? 1



계산할 초 / 1 * 60 * 60 * 24 = 일

(계산할 초 /  (1 * 60)) * 60 = 시

(계산할 초 / (1 * 60 )) % 60 = 분

계산할 초 / 1 % 60 = 초


## Array 배열 

- 배열 객체 
- 다른 언어에서의 Linked List 와 유사 (늘리거나 줄이는게 자유자재인게 Linked List)
- 배열의 크기가 고정이 아니고 만들고 난 후 데이터를 추가하거나 삭제하는 것이 가능함 
- 생성 
### [데이터 나열]
- new Array(): 비어있는 배열 생성 
- new Array(데이터 개수) : 데이터 개수 만큼 저장할 수 있는 공간을 확보해서 생성
- new Array(데이터 나열) : 데이터를 가지고 생성 

### for in
- 모든 인덱스를 순차적으로 접근할수있음
### for of 
- 모든 데이터를 순차적으로 접근 한 수 있다 .

## 메서드

- concat(배열): 배열 결합
- reverse()

- slice(start,end)
- sort([정렬을 위한 함수]): 데이터 정렬

- pop():마지막 데이터를 삭제하고 리턴 (스택)
- push(): 마지막에 데이터를 추가  (스택) 
- shift(): 첫번째 데이터를 삭제하고 리턴 (큐)
- unshift(data): 데이터를 맨 앞에 추가 (큐)
- remove(인덱스): 인덱스 번째 데이터 삭제
- indexof,lastIndeOf: 인덱스 반환 
- join : 배열의 각 요소를 구분기호를 추가하면서 하나의 문자열로 만들어서 릴턴 


# ------------------------칠판---------------------------------
> 배열(Array): 입력한 순서대로 연속해서 저장  

### List: 연속적으로 데이터를 저장 

### ArrayList :크기가 변할수있는 배열

### Linked List : 논리적으로 연속 (바로 다음데이터의 위치를 기억한다)


### 방향에 따른 종류 
- ascending: 작은 것에서 큰 것 순으로 나열하는 것으로 기본 (오름차순)
- descending: 큰것에서 작은 것 순으로 나열 (내림차순)


### 알고리즘에 따른 종류 
- selection(선택)
- bubble
- insertion
- heap
- quick
- radix
- merge
- shell 
> 신입으로 면접을 볼 떄는 quick sort 를 구현하고 설명할 수 있는것 


### javascript 에서는 sort()는 숫자 데이터의 경우 문자열로 변환해서 정렬을 수행함
> 다른 방법으로는 정렬을 하고자 할 때는 sort함수에 매개변수가 2개이고 정수를 리턴하는 함수를 대입해야함.
2개의 매개변수는 배열에 있는 2개의 데이터를 번갈아가면서 대입하는데 이 때 양수를 리턴하면 앞의 데이터가 작다고 파단해서 위치를 변경하지않는다 .
```javascript 

 let ar = ["에스파","소녀시대","김하늘","에놀라파","블리치"];
        //javascript 는 숫자 배열을 문자 배열로 판단하고 정렬을함.

        let br = [20,40,10,370,160];

        console.log(br.sort((a,b)=>a-b));
        //sort 메서드에는 매개변수가 2개이고 리턴은 정수를 하는 함수를 대입
        // 하는것이가능한데 , 매개변수 2개는 배열의 데이터가순차적으로 전부 대입
        // 양수를 리턴하면 앞의 데이터가 크다고 판단하고 위치를 변경
       console.log(ar.sort((a,b)=>{if(a<b){
        return -1;
       }else {
        return 1;
       }}))


```



### 배열에 추가된 함수 
- forEach() : 매개변수가 1개이고 , 리턴이없는 함수를 매개변수로 받아서 배열의 모든 요소를 
매개변수에 대입해서 함수를 호출한다 .

- map():mapping 의 약자라함. 매개변수가 1개이고 리턴을 하는 함수를 매개변수로 받아서 배열의 모든 요소를 매개변수로 대입해서 함수를 호출 후 그 결과를 가지고 다시 배열을 만들어서 리턴함.
배열에 어떤 연산을 가해서 새로운 배열을 만들고자 할 때 사용 

- filter(): 매개변수가 1개이고 boolean 을 리턴하는 함수를 매개변수로 받아서 배열의 모든 요소를 매개변수로 대입해서 함수를 호출한 후 true를 리턴한 데이터만 모아서 배열로 리턴함 .
- every(): 매개변수가 1개이고 boolean을 리턴하는 함수를 매개변수로 받아서 배열의 모든요소를 매개변수로 대입해서 함수를 호출후 모든 데이터가 true를 리턴하는지 확인해서 boolean 을 리턴

- some():  매개변수가 1개이고 boolean을 리턴하는 함수를 매개변수로 받아서 배열의 모든요소를 매개변수로 대입해서 함수를 호출후 1개의 데이터라도 true를 리턴하는지 확인해서 boolean 을 리턴



```javascript
      * forEach

        const dataFunction =[10,20,30,13,55,32,3,6].forEach((item)=>console.log(item));
        //함수의 매개변수 자리에 배열의 데이터를 순서대로 대입해서 함수를 호출함 .
        //내부 반복자를 사용하기때문에 실행 속도가 빠름 . forEach
        //결과 10 \n 20 \n 30 \n 13 \n  55\n 으로 출력 
        console.log(dataFunction);
        
      * Map
        
        let data =[10,20,30].map((v)=>{return v*5})
        console.log(data)
        //배열의 모든 요소에 함수를 호출해서 그 결과를 모아서 배열을 리턴
        //forEach와다르게  result = [50,100,150]
      
      * filter
        //조건에 맞는 데이터만 추출하는 함수 
        //매개변수가 1개이고 boolean을 리턴하는 함수를 설정 
        var br = ["korea","china","korean"];

        let data123 =br.filter((e)=>{
        //0 , -1 ,0 있으면 0 없으면 -1 
           return e.indexOf("ko")>=0;
        })
        //결과 ['korea','korean']
        console.log(data123)

```


# JSON 객체 

> JSON(JavaScript Object Notation) : 데이터를 자바스크립트의 객체 표현법의 문자열로 만드는 것 


- JSON.parse(json 문자열) : json 문자열을 자바스크립트 데이터로 변경해서 리턴함 => 서버에서 데이터를 받아온 경우에 사용함.
- JSON.stringify(자바스크립트데이터) : 데이터를 json문자열로 변환 => 서버에서 데이터를 전송할 때 사용함


### RegExp 객체

> 정규표현식

- 문자열의 패턴을 정의하기 위한 객체로 Perl 에서 처음 사용되었는데 지금은 거의 모든 프로그래밍 언어에서 제공하고 언어 차원에서 제공하지 않으면 외부 라이ㅏ브러리를 통해서 제공 
- 장점은 복잡한 코드를 줄여서 표현할수있다.
- 단점은 가독성이 떨어진다 .

> 객체 생성

- new RegExp(패턴,한정자): /패턴/한정자

> 함수
- test(문자열) : 문자열에 정규 표현식 패턴이 존재하는지 확인
- exec(문자열) : 정규표현식 문자열을 리턴

```javascript
     var str="Javascript Reg Exp";
        // 위의 문자열에 script 가 포함되어 있는지 확인 
        var regExp = /script/;
        console.log(regExp.test(str));
        //true or false
        /script/i < i를 넣어주면 대소문자 구분씹가능
        /script/g < 전체 영역에서 비교
        /script/m < 여러줄에서 비교

```
> 앵커 문자 
- ^패턴 : 패턴으로 시작하는 
- 패턴$ : 패턴으로 끝내는

> 메타 문자
- . : 아무글자
- [문자나열] : 문자 중 1개
- [^문자]: 문자를 제외
- [시작-끝]: 시작에서 끝에 포함되는 <br/> 영문소문자 ex) [a-z]<br/> 영문대소문자[A-Za-z]<br/> 한글[가-힣] 
- ￦d: 숫자, ￦w: 단어, ￦D: 숫자가아닌 ￦W: 단어가아닌, ￦s: 공백문자, ￦S: 공백문자가아닌,  

> 수량 문자 
+: 1개 이상
*: 0개 이상
?: 0개 또는 1개 
{횟수}: 횟수
{최소횟수,최대횟수} : 최소에서 최대 사이 
{최소횟수, } : 최소 횟수 이상


> String 의 정규 표현식 관련 메서드
- match : 정규 표현식 과 일치하는 부분을 리턴 
- replace(정규표현식,변경할 문자열): 정규 표현식에 해당하는 부분을 문자열로 치환
- search(정규 표현식): 정규 표현식 과 일치하는 부분의 인덱스 리턴
- split(정규 표현식): 정규 표현식 과 일치하는 부분을 찾아서 잘라낸 다음 배열로 리턴     



```javascript

    //주민등록번호 검사 
        // 숫자 6개 -숫자 7개 
        var jumin1 = "123456-1234567"
        let jumin2 = "123456-123456"
        let jumin3 = "12r3456-1234567"

        var jumin =/\d{6}-\d{7}/;
        
        console.log(jumin.test(jumin1))
        console.log(jumin.test(jumin2))
        console.log(jumin.test(jumin3))
        // true
        // false
        // false


```



## Set 
- 여러 개의 데이터를 중복없이 저장하는 자료구조
- 데이터를 가지고 key를 만들어서 저장한다 
  - 이때 해싱을 수행해서 key를 만들어낸다 
    * 해싱은 데이터를 가지고 새로운 데이터를 만들어 내는 것인데, 
  이렇게 만들어진 코드를  HashCode라고 함. 데이터가 같으면 HashCode 는 같음 <br/>
  <b>HashCode가 같다고 해서 데이터가 같지는 않습니다</b>
  <b>HashCode 를 가지고 원본 데이터를 찾는 것도 아주 어렵다</b>


## 생성 
- new Set()
- new Set(배열)
  > 함수<br/> 
  - add ,has, keys,values,delete,clear

```javascript
let set =new Set()
set.add("JavaScript")
set.add("Java")
set.add("JavaScript")
console.log(set)
// 결과 {Javascript,Java}
```

## Map 
> key와 value를 쌍으로 저장 
 - 이때 key는 Set으로 만들어진다 
   - 동일한 key에 value를 여러 번 설정하면 마지막 value만 저장된다.
    ```javascript
    new Map([key,value],[key,value]...)
    ```

- 함수 
  - keys(): key를 순회할 수 있는 이터레이터 리턴
  - values(): value를 순회할 수 있는 이터레이터 리턴
  - entries(): 모든 데이터를 순회할 수 있는 이터레이터 리턴
  - delete(key) : key에 해당하는 데이터 삭제
  - clear() : 모든 데이터 삭제
  - has(key) : 키의 존재여부를 리턴


## BOM(Browser Object Model)

>  웹 브라우저와 관련된 객체

 - window 가 최상위 객체 ,나머지는 window안에 포함된 객체
 
- windoiw 객체
  - 브라우저 자체 
  - 속성과 메서드 
  - location 속성: 현재 URL을 리턴하고 이 속성에 URL을 대입하면 그 URL 로 이동함. 
  - 화면의 너비와 높이 관련 속성 : outerWidth, outerHeight
  - open 이라는 메서드를 이용해서 새로운 창을 화면에 출력하는 것이 가능 : 팝업창을 만들때 사용 





## Screen 객체
  - 화면 정보 와 관련된 객체
  - 너비 와 높이 속성을 제공 

## location 객체
- 현재 페이지의 URL 관련된 객체
  - 프로토콜://IP 주소나 도메인: 포트번호/요청경로
  
  - IP 주소 나 도메인 - hostname (컴퓨터를 구분하기 위한 주소 - 하나의 컴퓨터가 여러 개의 IP 나 Domain을 가질 수 있음) -> ex( IP 공유기 
  - 포트번호 - 컴퓨터 내에서 프로세스 (프로그램) 을 구분하기 위한 번호 (0 ~ 65535)
    - 포트번호는 프로토콜의 기본 번호를 사용할 때는 생략 (http -> 80 , https -> 443)
  요청 경로는 프로세스 내에서 구분하기 위한 문자열 

  - ? 다음에 이름 = 값 query string 이라고 하는데 클라이언트가 서버에게 전송하는 데이터 
    - #이름이 나오는 경우가 있는 fragment(책갈피 - 문서 내의 특정 위치) 라고 합니다. 


## navigator 객체 
- 웹 브라우저의 고유 정보를 제공하는 객체
- userAgent: 클라이언트의 브라우저와 운영체제 정보 리턴

  - apple은 iphone 과 ipad 그리고 ipod 의 운영체제 이름이 다르고 ,android 는 구분이 없음 

```javascript
var os =['iphone'];
for(o of os){
  //os 배열에 있는 문자열이 userAgent 에 포함되어있는지 여부를 확인
  if(navigator.userAgent.toLowerCase().indexOf(o)>=0){
    break;
  }
}
```

## history 객체
- 이전에 접속한  url 에 대한 정보를 가진 객체
- back이나 forward 같은 메서드를 이용해서 이전 이나 이후로 이동하는 것이 가능
<br/>

# DOM(Document Object Model)

- HTML Body에 표시되는 태그들을 자바스크립트에서 가져와서 사용하도록 만든 것 

- document 객체
   - document 는 HTML 내의 Body에 관련된 정보를 제공하고 작업을 수행할 수 있도록 해주는 객체

- referrer: 이곳에 다른곳에서 링크를 선택해서 온 경우 다른 곳의 URL 





### DOM 객체의 공통속성 과 메서드

- appendChild()
- removeChild()
- createElement(),
- createTextNode(),
- getAttribute(),
- setAttribute()