### 문자열의 + 연산자

- 문자열은 모든데이터와 + 연산이 가능
- 기본형 데이터의 경우는 문자열로 형 변환을 해서 결합을 하고 나머지 데이터는 toString 메서드를 호출해서
  결과를 결합하는 방식을 취하게된다
- 문자열 리터럴은 static영역에 저장되기 때문에 내용 변경이 안된다
- 문자열을 + 로 결합을 하게되면 메모리 낭비가 발생할 수 있다
- 문자열 + 연산은 자주 사용하지 않는 것을 권장하고 String.format 이나 StringBuilder를 사용하는 것을 권장함.
    - Java1.7 버전 부터는 문자열의 + 연산을 자바가 내부적으로 StringBuilder 를 이용해서 수행하기 때문에 크게 관계없다.

- String.format
    - 스트링을 만들어주는거 ?

# 제어문

> java 는 애플리케이션을 실행하면 jdk가 제공하는 클래스들은 jre가 제공을 해주고 우리가 만든 모든클래스를 jvm에 로딩을해서 실행함

### 콘솔에서 입력받기

- BufferedReader 클래스

```java
import java.io.BufferedReader;

public class main() {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new BufferedReader(System.in));
        String str = br.readLine();
//        한줄의 문자열을 입력받아서 str에 대입 
    }
}
```

- Scanner 클래스

```java

import java.util.Scanner;

public class main() {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        자료형 변수이름 = sc.next자료형(); //문자열을 받아서  자료형으로 변환한 후 리턴 
    }
}
```

### 키보드에서 입력받는 원리

- 키보드 -> buffer -> App
- 버퍼를 내보내는작업
    - flush

### if

```java

public class passif {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("메뉴입력(1~3");
            int menu = sc.nextInt();

//            System.out.print("아이디입력");
//            String id = sc.nextLine();
//            System.out.print("비밀번호입력");
//
//            String password = sc.nextLine();
//            if (id.equals("root") && password.equals("1234")) {
//                //문자열은 생성 방법에 따라 다른인스턴스가 될 수 있어서 ㅓ
//                //값이 동일한 지 비교할때 ==를 사용하면안되고 
//                //equals를 사용해야함
//                System.out.println("로그인성공");
//            } else {
//                System.out.println("로그인실패 404");
//            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
```

### while 문

> 기본 구조

```
while(boolean 표현식){
    boolean 표현식이 false가 아니면 수행할 내용 
        }
```

- boolean 표현식이 false가 될 때 까지 내용을 반복함

### do~while

> 기본형식

```
do{
   boolean 표현식이 false가 아니면 수행할 내용 
        }while(boolean 표현식);
        
 do {
            //반복할 내용
            tot += cnt;
            cnt += 1;
        }
        while (cnt <= 10);
        System.out.println(cnt);
        System.out.println(tot);
```

### for 문

> 기본형식

```
for(초기식; boolean 표현식; 두번재부터 수행할 내용 ){
boolean 표현식이 false 가 아닌 경우 수행할 내용
}
```

- 각 식은 생략 가능
- 각 식에는 2개 이상의 문장을 사용해도 된다
- 배열을 순회하면서 수행

```
for(임시변수 : 배열){
배열의 데이터를 임시변수에 하나씩 대입하면서 수행할 내용
}
```

### 제어문 사용시 주의할점

- 실행할 내용이 한 줄이면 {} 생략가능
- 제어문은 별도의 블럭을 생성하는데 이전 블럭에 만든것을 다시만들수는 없고 자신의 블럭안에 만든것은 자신의 블럭에서만가능하고 제어문이 종료되면 블럭은 소멸된다

### break와 continue

- break
    - switch 와 반복문 안에서 switch와 반복문을 종료하는 기능
    - break 다음에 수행할 내용이 없다면 break 대신에 자신을 호출한 메서드에게 제어권을 넘기는 reuturn으로 작성해도된다
- continue
    - 반목문 안에서 더 이상 아래로 내려가지 않고 다음 반복으로 넘어가는 기능
- 주의할점
    - break나 continue 가 반목문 안에서 조건없이 ㅏㅅ용되면 경고가발생하는데 이때 dead code가 만드러질수있다
    - 조건만 이씅면 경고는 없어진다

### 배열

> 동일한 자료형의 연속적인 모임

- 자바에서의 Array는 정적이다
    - 한 번 만들어지면 내부 요소의 수정은 가능하지만 크기 변경은 안된다.
- 생성을 하면 데이터는 Heap에 생성이 된다.
- 동일한 용도로 사용되는 데이터는 이름이 하나인게 관리하기 편리하기 때문이다.
    - 여러 개의 데이터를 반목문이나 iterator 를 이용해서 쉽게 사용할 수 있다.
- 배열은 하나의 public 속성을 갖는데 속성은 length
    - 배열이름.length 를 호출하면 한 단계 아래 하위 데이터의 개수를 리턴한다

### 배열의 생성

> 초기데이터를 가지고 생성 - 선언할 때 만 가능

- 배열요소 1개의 자료형 [] 배열이름 = {데이터 }

### 크기만 설정

- 배열요소 1개의 자료형 [] 배열 이름 = new 배열요소 1개의 자료형[개수]
    - 개수만큼의 저장 공간이 확보되고 자동 초기화를 수행
    - 숫자의 경우는 0
    - boolean 의 경우는 false
    - 나머지는 null
- [] 는 배열 이름 뒤에 기재해도 된다.
    - 자바에서는 대부분 앞에 기재함

### 배열의 요소 접근

- 배열이름[인덱스] : 인덱스는 0부터 length -1 까지

### 배열의 순회

- for (임시변수 : 배열이름){배열의 데이터를 순차적으로 하나씩 임시 변수에 대입하고 수행}

```java
public class Array {
    public static void main(String[] args) {
        String[] socker = {"반바스텐", "굴리트", "박지성"};

        //배열의 데이터 순회
        int len = socker.length;
        for (int i = 0; i < len; i += 1) {
            String str = socker[i];
            System.out.println(str);
        }

        int[] ar = new int[3]; //숫자는 0으로 초기화
        ar[0] = 20;
        ar[1] = 30;

        //배열의 순회
        for (int temp : ar) {
            System.out.println(temp);
        }
    }
}
```

### 배열 사용시 많이 발생하는 예외

- <b>NullPointException </b>
    - 배열의 메모리 할당이 이루어지지 않은 상태에서 사용
- <b>ArrayIndexOutOfBoundsException </b>
    - 인덱스 오류

### 다차원 배열

- 배열의 배열
- 배열 안의 요소가 배열인 형태
- [] 의 개수를 차원의 개수라고 한다
- 2차원 배열을 matrix 라고 함
    - 3차원 배열이나 4차원 배열을 사용하는 경우가 있음

### 생성

- 초기 데이터를 가지고 생성
- 각 행의 열 숫자가 동일한 경우
- 자료형[][] 배열 이름 = new 자료형 [크기][크기]

### 2차원 배열 사용

```
배열이름[행인덱스][열인덱스] 하면 데이터에 접근
배열이름[행인덱스]는 배열
```

### 2차원 배열에서의 length

```
배열이름.length : 행의 개수
배열이름[행인덱스].length:: 열의 개수 
```

- 배열은 만들 때 마지막을 가리키기 위해서 메모리를 사용하므로 메모리를 아끼고자 할 때는
  2차원 배열을 1차원 배열로 만드는 것도 생각을 해봐야한다

### 배열에 데이터 추가

- 배열은 크기 변경이 불가
- 배열의 크기를 변경하고자 할 때는 배열의 데이터를 복사해서 수행해야한다
- 배열을 복사하는 방법은 직접 알고리즘을 구현해도 되고 Arrays클래스의 static 메서드인 Arrays.copy를 이용할 수 있다.
- 복사할 배열과 개수를 매개변수로 대입하면된다.

### 배열의 데이터를 하나의 문자열로 만들기

- 배열은 toString 메서드가 존재하지 않아서 문자열로 만들고자 하는 경우에는 Arrays.toString 메서드에
  매개변수로 대입해서 결과를 리턴받아야한다.
- Arrays.toString 은 배열 안의 모든 요소의 toString 을 호출해서 결합한다 .

```java

public class ArrayClone {
    public static void main(String[] args) {
        String[] arr = {"라이언", "복숭아", "솜사탕"};
        //위의 배열의 내용을 가지고 데이터를 1개 추가한 배열을 생성
        String[] arr1 = new String[arr.length + 1]; //배열을 추가하고싶은 개수만큼 만들기

        //베열 요소 복제

        //직접 구현 예시
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }


//방법 2
        String[] copyArr = Arrays.copyOf(arr, arr.length + 1);
        copyArr[3] = "아 집가고싶어";

        System.out.println(Arrays.toString(copyArr));
    }
}

```

### sorting

> 배열은 데이터가 여러개 이므로 접근을 할 때 원하는 순서대로 접근할 수 있도록 정렬

- 정렬하는 방법은 알고리즘 구현
- sort라는 메서드를 이용하는 방법이 있다.
- Arrays.sort 메서드는 기본적으로 크기 비교가 가능한 메서드를 소유하고 있어야 하고 이를 직접구현해서도가능
    - default 오름차순

### Arrays.sort 메서드를 이용한 데이터의 정렬

> 숫자 데이터 와 문자열 그리고 Date클래스의 배열은 별도로 메서드를 구현하지 않아도 오름차순 정렬이 가능

- Wrapper 클래스
- String , Date는 Comparable이라는 인터페이스가 구현되어있음
- Comparable ?
    - https://webstudynote.tistory.com/136
- 정렬은 <b>힙소트와 퀵소트 를 많이사용함 </b>

## 선택정렬 (Selection sort)

> 첫번째 데이터부터 마지막 바로앞 데이터까지 자신의 뒤에있는 모든 데이터와 비교해서 정렬해나가는 방식

- 참고 정렬 애니메이션
    - https://www.toptal.com/developers/sorting-algorithms
- 시간복잡도 참고 정훈
    - 즉 시간 복잡도는 O(N^2)이다. (+ 버블 정렬과 시간 복잡도는 동일하지만 자료의 Swap을 반복하는 버블 정렬보단 조금 빠르다.)
- 앞의 데이터가 클 때 데이터를 교환하면 오름차순 정렬이 되고 뒤의 데이터가 클때 데이터를 교환하면 내림차순정렬이된다.

```
기준 : 첫번째 자리의 데이터
비교 : 두번째 자리부터 끝까지의 데이터 
1 5 3 2 4 

1일때 => 1 5 3 2 4 

2번째 => 1 2 5 3 4  

3번째 => 1 2 3 4 5 
```

### search(검색)

> 검색 알고리즘

- 순차 검색
    - 데이터를 정렬하지 않은 상태에서 처음부터 끝까지 데이터를 찾을때까지 비교해나가는 방식
- 제어검색
    - 데이터를 정렬한 상태에서 검새 ㄱ
- binary search
    - 중앙에 있는 데이터 와 비교해서 같으면 찾은 것이고 작으면 왼쪽 부분의 배열에 다시 중앙값과 비교하고 크면 오른쪽의 배열에서 중앙값과 비교하면서 찾는방식

### 피보나치 검색

> 피보나치 수열의 순서대로 검색해나가는 방식

- 첫번째와 두번째는 1이고 세번째부터 앞의 2개의 항의 합인 수열

### interpolation searc(

- 찾는 위치를 곗ㄴ해서 찾는 방식 수행* 데이터 )

```
((검색값-최소값))/(최대값-최소값)) * (최대인덱스 - 최소인덱스)+1 
```

- 위와같은방식을 해서 위치를결정

- 데이터의 간격이이 균등할때 효과적
- tree search
    - 데이터를 저장할때 true구조로 저장해서 검색에 이용
- block search
    - 블록끼리는 정렬이 되어 있고 블럭 내부는 정렬이되지 않는 경우사용
- hasing
    - 데이터 key를 생성해서 찾아가는 방식응ㄴ 가장빠르지만 메모리의효율은 떨어진다