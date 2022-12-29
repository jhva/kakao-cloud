### 인터페이스나 추상 클래스 또는 일반 클래스를 상속받아서 사용하는 방법
> 인터페이스나 추상 클래스를 상속하는 클래스를 생성해서 인스턴스를 생성한 후 사용하는 방법 
- anonymous 를 이용하는 방법 
    - new Comparator<String>{메서드 재정의 }
```java
package java_12_21;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String [] names = {"눈오는게제일싫어","하늘에서 뭐가 내리면 너무싫어","칼로리"};
        //원본은 항상 냅두고 복사본가지고 하는게좋다.
        String[] copy = Arrays.copyOf(names,names.length);
        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
        Arrays.sort(copy, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //숫자일땐 뺄셈
                return o1.compareTo(o2);
            }
        });
    }
}

```

### quicksort

```java
public class main(){
  public static void quickSort(int left,int right,int [] data){
    //데이터 출력
    System.out.println(Arrays.toString(data));
    //기준점 선정 - 맨왼쪽을 기준으로 설정
    int pivot = left;
    //큰 데이터를 찾기 위한 인덱스
    int i = left +1;
    //작은 데이터를 찾기 위한 index -> right
    //나중에 데이터를 교체해야하기 때문에 pivot의 위치를 저장
    int j = pivot;

    //배열의 데이터가 2개이 상인 경우만 수행
    // 배열의 데이터가 1개이면 left 와 right 가 같아지눼
    if ( left< right ){
      for(; i<= right; i=i+1){
        if(data[i] < data[pivot]){
          j =j +1;
          int  temp = data[j];
          data[j]=data[i];
          data[i]=temp;
        }
      }
      //pivot 위치의 데이터를 자신의 위치로 이둉 =
      int temp = data[left];
      data[left] =data[j];
      data[j]=temp;
      //pivot 의 위치를 비교가 끝ㅌ난 자리로 수정
      pivot = j;
      //pivot 의 왼쪽 부분을 재귀적으로 다시 quick sort
      quickSort(left , pivot-1, data);

      //pivot의 오른족 부분을 재귀적으로 다시 quick sort
      quickSort(pivot+1,right, data);
    }
  }
}
```


### 검색 
> 검색알고리즘
- 순차 검색 
  - 앞에서부터 끝까지 순회하면서 조회, 데이터가 정렬되지 않아서 수행
  - 검색속도는 가장 느림, 데이터가 존재하지 않는 경우 모든 데이터를 조회해야 알 수 있다.
- 제어 검색
  - 데이터를 정렬해 놓고 검색하는 방식
  - 이분검색(Binary Search) 
    - 중앙의 데이터 와 비교해서 같으면 찾은 것이고 작으면 왼쪽 부분에서 동일한 작업을 수행하고
    크면 오른쪽에서 동일한 작업을 수행
  - 피보나치 검색 
    - 피보나치 수열 이용 (1,1,2,3,5,..)
  - 보간 검색
    - 검색위치를 계산 
      - (찾고자하는 데이터 - 최소 데이터)/(가장 큰 데이터 - 최솟 데이터) * 데이터 개수 
      - 10 20 30 40 50 60 70 (70을 조회)
      - (70-10) / ( 70-10) * 7
  - 트리 검색 
    -  데이터를 저장할때 트리 구조를 저장 (LinkedList 구조)
  - 블록 검색 
    - 블록끼리는 정렬이 되어 있고 블럭 내부는 정렬을 하지 않은 상태에서 검색
- 해싱 
  - 데이터의 저장 위치를 계산하는 방식으로 모든 데이터의 접근 속도가 같음
    - 한 번의 계산만으로 데이터를 조회할 수 있으므로 가장 빠른 검색 방법
    - 계산은 우리가 하지 않고 운영체제가 한다. 
- Array 클래스는 binarySearch 라는 메서드를 제공해서 이분 검색을 할 수 있도록 해준다.

### 배열이나 공부할 때 반드시 해야할 일 
- 전체순회 
- 정렬 
- 데이터의 존재 여부 나 존재 위치

# Collection
> 여러 개의 데이터를 모아 놓은것 - vector Data 라고도 한다 
- java.util.Vector 클래스가 존재 
- Collection 인터페이스가 존재하는데 이 인터페이스는 List 와 Set의 상위 인터페이스
- List 나 Set에서 공통으로 사용할 메서드의 원형을 선언해 둠
  - add.addAll 
  - clear 
  - contains
  - equals
  - isEmpty
  - remove
  - removeAll
  - retainAll
  - size
  - Generics 가 적용되어 있어서 인스턴스를 만들 때 자료형을 기재하면 그 자료형으로 결과를 리턴하지만 
  기재하지 않으면 Object 타입으로 리턴하게되고 이렇게 되면 사용을 할 때는 강제 형 변환을 해서 사용해야한다.


### 반복자 
> Collection 의 데이터를 순차적으로 접근할 수 있도록 해주는 포인터 
- 부르는 이름은 여러가지가 있음 
  - enumerator
  - iterator
  - cursor
  - generator
- Enumeration 인터페이스 와 Iterator  인터페이스가 제공되는데 Enumeration 인터페이스가 legacy API
- 다음 데이터의 존재 여부 와 실제 다음데이터를 가져오는 메서드가 제공 
- Stream API 를 제공해서 잘 사용하지는 않는다.

## Java에서의 배열들
- 배열은 크기가 고정 
- ArrayList,Vector는 크기가 가변이다 . 
  - ArrayList는 Thread unsafe 
    - 추가나 삭제를 해야하면 시간소요가 많이걸림 데이터가 연속적으로 연결되어있어야하는데 
    끊어지는이유이기때문에 삭제된데이터를 다 앞으로 끌어서 연결해야한다 
    - 누군가 사용하는걸 확인하지 않고 사용하겠다 .
  - Vector는 Thread safe
    - 확인하지 않고 사용하겠다 
- 서버에서 많이 사용함.

### LinkedList 
- 논리적으로 연속 
- 다음데이터의 참조를 기억
```
10 -> 20  -> 30 
```
- 삽입 삭제 에 굉장히 강한성질을 가졌다 
- ArrayList와 달리 추가삭제시 앞으로 끌어모으거나해야하지만 
- LinkedList는 데이터의 참조성질이 작용하기때문에 그렇다
- 실제로지우는게아니라 포인터를 삭제하는거다  
- 메모리 낭비가 심하고 조회속도가느리다
- 응용프로그램에서 만힝사용함 
- 게임에서 거의 사용한다 


### doubleLinkedList
- 출발점이라는 부분을 잘모른다 
- 그래서 앞의 부분을 HEAD , 끝 부분을 Tail을 구분을 지어준다
  - 데이터를 가지는게아니라 시작점 과 끝점의 이름을 붙혀주는것이다.


### stack (LIFO Last In First Out)
- 마지막에 들어간게 첫번째로 나간다 
### Queue 
> FIFO (First In First OUt)
- 첫번째로 들어간게 첫번째로 나간거 

### Deque
> Stack + Queue
- 양쪽에서 삽입과 삭제가 가능한게 Deque 


### Hashing 
> 주소 계산 
- 중복X 

### List 구조 
> List 인터페이스 
- List 들이 공통으로 가져야하는 메서드를 소유하고 인터페이스
- Generics 가 적용되어 있다 
  - 인스턴스를 만들 때 실제 자료형을 설정하는 것이 좋다 
- toString 메서드를 재정의 놓았기 때문에 toString 메서드로 데이터의 내용을 확인하는것이가능

### Vector , ArrayList 클래스 
> 데이터를 물리적으로 연속해서 저장한 클래스 

### LinkedList 클래스 
> 데이터를 논리적으로 연속해서 저장한 클래스 


### 실습 

```java
package java_12_21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Collection
{
    public static void main(String[] args) {
        //정수를 저장하기위한 ArrayList
        List<Integer> al = new ArrayList<>();

        //정수를 링크드리스트에 저장하기위한
        List<Integer> li =new LinkedList<>();


        for(int i =0; i<100000; i++){
            al.add(i);
            li.add(i);
        }
        long start;
        long end;
        start= System.currentTimeMillis();
        for(int i=0; i<100000; i++){
            Integer integer = al.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 조회 시간"+ (end-start));

        for(int i=0; i<100000; i++){
            Integer integer = li.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("linkedList 조회 시간"+ (end-start));
        // 결과 확실히 차이가 난다 
      //ArrayList 조회 시간2
     // linkedList 조회 시간3681


    }
}
```

### 확장클래스 
> java.util.concurrent.CopyOnWriteArrayList
- 멀티 스레드 환경에서 하나의 리스트를 사용하고 있는 중에 다른곳에서 List에 변경을 가하면 
ConcureentModificationException이 발생하는데 이러한 문제를 해결하기 위해서 데이터 읽기 작업을 할 때 복사본을 만들어서 수행하는 클래스 


### Stack
> LIFO 구조로 동작하는  List
- 삽입은 push
- 꺼내는 동작은 pop (마지막 데이터를 삭제)
- peek(마지막 데이터를 삭제하지않고 리턴)

### Queue
> FIFO 구조의 자료구조 
- 자바에서는 인터페이스로 제공 
- 삽입은 offer
- 마지막 데이터리턴은 peek
- 지우면서가져오는 poll
- Queue 를 implements 한 대표적인 클래스는 데이터의 크기 순으로 조회할 수 있는 PriorityQueue 클래스가 있다.
- 데이터를 삽입할 때 정렬을 수행하기 때문에 크기 비교가 가능한 (Comparable 인터페이스를 implements) 인스턴스만 삽입이 가능하다



### Deque
> 양쪽에서 삽입삭제가 가능한 자료구조 
- 자바에서는 인터페이스 형태로 제공하고 ArrayDeque 클래스가 Deque를 구현한 대표적인 클래스

# Set
> 개요 
- 데이터를 중복없이 해싱을 이용해서 저장하는 자료구조
  - key 없이 저장 
  - 저장순서도 알 수 없음
  - 전체 데이터 순회는 이터레이터 나 빠른 열거를 이용해서 수행
  - 자바에서는 인터페이스로 제공

### 구현한 클래스 
- HashSet
  - 저장 순서르 알 수 없음 
- LinkedHashSet 
  - 저장 순서를 기억하는 Set
- TreeSet 
  - 크기 순서대로 저장 
    - 비교 가능한 메서드를 가진 인스턴스만 저장 가능 

### 실습 로또 
```java


import java.util.*;

public class LottoMain {
    public static void main(String[] args) {
        //로또 번호생성기
        //1-45 까지의 숫자 6개를 입력받앗 ㅓ젖아한 후 출력
        //입력받을 때 1~45 사이의 숫자가 아니면 다시입력하도록
//      6개의 정수 배열 생성
//        Scanner sc = new Scanner(System.in);
//        int[] lotto = new int[6];
//        int len = lotto.length;
//        for (int i = 0; i < len; i++) {
//            try {
//                System.out.println("로또 번호 입력");
//                lotto[i] = sc.nextInt();
//                if (lotto[i] < 1 || lotto[i] > 45) {
//                    System.out.println("1 -45 사이만 입력하셈");
//                    i--;
//                    //중복 검사를 하지않고 패스하기 위해서=
//                    continue;
//                }
//                //데이터 중복검사
//                // 첫번째 부터 현재 데이터 앞까지 비교
//                boolean falg = false;
//                int j = 0;
//                for (; j < i; j++) {
//                    //2개의 데이터가 같을때
//                    //중복
//                    if (lotto[i] == lotto[j]) {
//                        falg = true;
//                        break;
//                    }
//                }
//                if(falg == true){
//                    System.out.println("중복된 숫자 입니다 ");
//                    i--;
//                }
//                Arrays.sort(lotto);
//            } catch (Exception e) {
//                i--;
//                sc.nextLine();
//                System.out.println("숫자 넣으세요 ");
//
//            }
//        }
        //중복된 데이터를 저장하지 않고 데이터를 정렬해서 저저장하는
        //자료구조 클래스
        //Tres set

        Scanner sc = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>();
        //set에 6개의 데이터가 저장되지 않은 경우
        while(set.size()<6){
            System.out.println("로또 번호를 입력해주세요 ");
            int temp = sc.nextInt();
            if(temp< 1 || temp >45){
                System.out.println("로또 번호를 1~45 까지 의 숫자를 입력해주세요");
                continue;
            }

            boolean result = set.add(temp);
            if(result==false){
                System.out.println("중복된 로또번호입니다");
            }
        }
        System.out.println(set);
        sc.close();


    }
}
```

### Map 
> Map 은 Key 와 Value 를 쌍으로 저장하는 자료구조 
- Dictionary(dict) 나 HashTable이라고도 한다 .
- java 에서는 Map 은 인터페이스 이고 2개의 Generic을 사용 
  - 하나의 key 의 자료형이고 다른하나는 value의 자료형입니다 
- 특별한 경우가 아니면 key 의 자료형은 String 으로 하는 것이 좋다 
  - key 를 set으로 구성 
  - key 는 중복 될 수없다 
- 데이터 추가 put (key,value)
  - 존재하는 key를 이용하면 수정 
- 데이터 1개 가져오기 
  - get(key)
    - 존재하지 않는 key 를 사용하면 null 을 리턴 
- 데이터 삭제 
  - remove(key)
- 데이터 개수 
  - size()
- 모든 key를 리턴 
  - Set<K>keySet()

### 구현 클래스 
- HashMap
  - key의 순서를 알 수 없다. 
- LinkedHashMap
  - key가 저장한 순서 
- TreeMap 
  - key를 정렬 


### MVC Pattern 
- M: model
- V: View 
- C: Controller
- 역할 별로 구별해서 구현 
  - 이렇게 구현하는 이유는  Model이 변경되더라도 View 에는 영향을 주지 않기 위해서 
- 2차원 배열의 경우는 복잡하더라도 Map 의 배열로 만드는 것이 MVC 패턴을 적용하는 편리하다.
- 모든 언어의 Web Programming 에서는 데이터 전달을 Map 으로한다


### Map 
```java

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapMain {
    public static void main(String[] args) {
        Map<String,Object> map =new HashMap<>();
        map.put("name","adma");
        System.out.println(map.get("name"));
        map.put("name","군ㄱ");
        System.out.println(map.get("name"));
            //value를 object로 설정했을 때 사용
        //value 를 삽입할 때 삽이  ㅂ할 때 String 이지만
        //Map을 만들 때 Value ㄹ의 type을 Object 로 설정했기 때문에
        //get을 해서 데이터를 원상 보구하고자 하면 강제 형 변환을 해야한다.
        String value =(String)map.get("name");
        //모든 키를 가져오는것
        Set <String>keys = map.keySet();
        System.out.println(keys);
        System.out.println(value);
    }
}

```

### VO 와 Map

```java

package java_12_21;

//Data 클래스 VO

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class VO {
    private int num;
    private String name;

    public VO(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public VO() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}

public class VoMapMain {
    public static void main(String[] args) {
        VO vo = new VO(1, "12");
        System.out.println(vo.getName());
        System.out.println(vo.getNum());

        Map<String,Object> map =new HashMap<String,Object>();
        map.put("name","daf");
        map.put("num",3);
        System.out.println(map);

        //map의 모든 키를 가져와서 출력
        Set <String> keys = map.keySet();
        for(String key :keys){
            System.out.println(map.get(key));
        }
    }
}

```

### 2차원 배열 