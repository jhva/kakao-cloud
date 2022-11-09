### Constructor

```javascript

  const cMember = class{
            constructor(){
                return{name:"noname"};
            }
        }
  const cMember = class{
            constructor(name){
                this.name=name;
                //이렇게 만들면 직접 호출하는것이 안됨.
                //이경우는 데이터를 리턴하는 메서드를 별도로 만들어야 사용이 가능하다,
            }
        }


const memeber = new Member() // 인스턴스 생성 
// 생성자에서 name 이라는 속성ㅇ을 생성했음 므로 name 속성을 사용하는것이 가능함 


constructor 함수에 입력을 받아 객체의 초기값을 설정해봅시다. 

class Person{
    constructor(name,first,second){ // 약속된 이름으로 바꾸면 안됩니다.
        this.name = name;
        this.first = first;
        this.second = second;
    }
}
```
- 인스턴스를 생성하고 그 참조를 리턴하는 특별한 목적의 메서드 
- 메서드에서 this 속성이름을 이용해서 인스턴스의 속성을 생성할수있다.일반 메서드에서 속성을 생성하느 ㄴ경우 메서드 호출을 하지않으면 속성을 사용할수가없다.

- 생성자는 인스턴스를 생성하기위해 호출하는 메서드기 때문에 인스턴스를 만들려면 반드시 한 번은 호출해야한다. 생성자에서  필요한 속성을 만들면 ..
- 자바스크립트는 객체를 생성할때 자동으로 constructor 함수를 호출합니다.



### getter && setter - 접근자 메서드 
#### getter 
- 인스턴스 안에 존재하는 속성의 데이터를 변경하고 리턴하는 메서드
- getter 는 속성의 데이터를 리턴하는 메서드 

#### setter 

- 매개변수는 1개(데이터전체) 또는 2개(배열이나 객체의 일부 속성을 변경하는 경우 인덱스나 속성이름을 매개변수로 추가하는 경우가있다.) 일반적이면 리턴을 하지 않는다. 



### static

- 메서드 앞에 static 을 붙이면 인스턴스가 아니라 클래스가 호출할 수 있는 속성이 된다 .
- static 이 붙으면 인스턴스가 호출할 수 없음
``` javascript
    const Person = class{
            constructor(num=1,name='',tel=''){
                this.num=num;
                this.name=name;
                this.tel=tel;
            }
            static disp(){
                //return this.num;
                return "static";
            }
            setName(name){
                this.name=name;
            }
            getName(){
                return this.name;
            }

        }

        -클래스는 인스턴스가 아니기 때문에 this에 대입이안됨
        메서드를 호출할때 this에 인스턴스 자신의 참조를 넘겨야함.
```
- 만약에 disp를 호출할때 this가 있으면 인스턴스의 참조가 아니고 class객체의 참조가 된다.
- 인스턴스는 static 메서드를 호출할 수 없다 .


## 상속 
> 상위 클래스의 모든 것을 하위 클래스가 물려받는 것 
- 기본적으로는 클래스들을 만들다가 중복되는 내용이 나오면 중복되는 내용을 가지고 상위 클래스를 만들고 다른 클래스들에서 상속을 하는 형태를 취하게 됩니다.


### super 
- 하위 클래스에서 상위클래스의 속성을 호출할 때 상위 클래스 인스턴스를 super라고 함
- 하위클래스의 일반 메서드에서 상위 클래스에 만들어진 메서드를 호출하고자 할때는 super.
- constructor 안에서 상위 클래스의 constructor를 호출하고자 하는 경우 super()로 호출.

### method overriding(재정의)
- 상위 클래스의 메서드 와 동일한 모양의 메서드를 하위 클래스에서 다시 정의하는 것.
- 목적은 기능 확장 (상위 클래스의 메서드를 가지고 그대로 사용하는것이 부족해서 추가하기 위해서.)


### Iteration - 개념 
- 순차적 처리를 위한 것
- 모든 데이터가 순차적 처리를 할 수 있는 것은 아니고 Iterable 프로토콜 과 Iterator 프로토콜을 준수해야만 순차적 처리가 가능함.
- 순차적 처리가 가능한 대표적인 데이터가 Array
- 순차적 처리가 가능한 데이터 

### for of - 순차적 처리가 가능한 <b>데이터를</b> 순회하는 명령어
> for - in 은 객체 나 배열의 모든 <b>속성을</b> 순차적으로 접근하기위한 명령문 .


``` javascript
   // in은 속성을 추출 - 배열의 경우는 Index
        for (attr in ar){
            console.log(attr)
        }
        // 데이터를 순회 
        for (attr of ar){
            console.log(attr)
        }

```



# Object 

### Destructuring - 구조분해 할당 또는 비구조화  할당
- 데이터를 나누어서 저장 

### 배열 - 순서대로 할당되고 마지막 변수에는 이전에 할당한 것들을 제외한 모든 것들이 할당됩니다. 

### 인스턴스 
- 변수의 이름과 속성의 이름을 맞추어서 할당이 된다.

### spread 연산 & rest param
- 구조 분해 할당을 할 때 나머지를 전부 할당하는 연산을 spread 연산이라고 하고 파라미터에 적용하면 rest param이라고 함 
- 할당할 변수 앞에 ...을 붙혀주면된다 


### exception(예외)

- 문법에는 맞아서 번역은 되는데 특수한 상황이 발생해서 프로그램이 중단되는현상

### assertion
- 에러가 없고 예외가 발생하는 상황이 아니지만 개발자가 강제 예외를 발생시켜 프로글매을 중단시키는것


### 예외처리 기본 구조 

``` javascript
try{
    예외 발생 가능성이 있는코드
}catch{
    예외가 발생했을 때 수행할 코드
}finally{
    예외 발생 여부에 상관없이 수행 할 코드 
}
=> finally 는 생략이 가능하다 

```