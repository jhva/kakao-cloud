## JavaScripit 

- 1의 보수는 부호는 반대가 되고 절대값 1차잉 
- << 한번 밀때 마다 2배씩 증가
- >> 음수의 경우는 결과가 다름 

### 논리연산자 
- 데이터를 boolean 형태의 하나의 값으로 간주하고 논리 연산을 수행하는 연산자 
- && : 둘다 true일때 
- || : 둘다 false인경우에만 
- !(NOT) : true이면 false , false 이면 true

#### Falsy : false 로 간주하는데이터 
 > 0 , NULL, NaN(Not a Number), undefinded, " " 빈칸


### new : 생성자를 호출해서 인스턴스를 생성하고 인스턴스의 참조를 리턴하는 연산자 


### this: 생성자나 객체 내부의 함수에서 객체 자신을 가리키는 연산자 

### typeof : 데이터의 자료형을 문자열로 리턴해주는 연산자 

### instance of : 객체가 특정 클래스로부터 만들어졌는지 확인하는 연산자 

### in : 배열의 존재ㅑ여부 
```
var code = ["123","zkflsk"];
console.log("123" in code);
```

### void : 함수를 만들때 값을 리턴하지 않도록 지정하기 위한 연산자 


### switch 
- 값에 의한 분기 
```
switch(표현식)
    case:
    break; 
    ...
    ...
    ...
    default:
        break;
    
```
> 표현식의 결과는 문자열이나 정수 또는 boolean이어야한다 값의 잘이ㅔ는 반드시 값을 기재하는것이 아니라 <br/>
표현식도 가능 case문의 개수는 여러개 가능하지만 생략은 안됨.<br/>
default 는 생략하거나 1번만 작성 <br/>
break; 가 없으면 break 를 만날때까지 모든 내용을 수행한다.