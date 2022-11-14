div 
- 블록요소 
- 기본적으로 좌우에 다른 요소가 배치 될 수 없음

span 
- 인라인 요소
- 기본적으로 좌우에 다른 요소가 배치될 수 있음 

iframe
- 다른 웹 문서를 가지고와서 사용하는거

- 속성: width,height,name ,seamless(프레임 표시 없애주는 속성),src
<br/>
<h1>CSS</h1>

<h1>복합 선택자 </h1>
- 공백없이 여러 선택자를 나열하면 모든 선택자가 일치해야한다. 
EX) div.content{}-> div태그 중에서 class 속성이 content인 경우 

<h1>자식 선택자 </h1>
EX) 선택자 > 선택자 2

div > span 

```
<div><span></span> </div>
```
- 선택자 1에 포함된 선택자 2인데 선택자2가 바로 아래 레벨에 있어야함


<h1>하위 선택자 </h1>
- 선택자1 선택자2
- 선택자 2가 선택자1에 포함되어있으면 적용 
ex) div span 

<h1>인접 형제 선택자</h1>
- 인접형제 선택자 : 선택자1 + 선택자2 
- 선택자1과 동일한 레벨에서 다음에 나오는 선택자2에 적용 
<b>바로 뒤에오는 태그만 </b>




<h1> 형제 선택자</h1>
- h1 ~ h2 
- h1과 동등한 레벨에 있는 모든 h2 에 적용 
 태그 뒤에 있는 모든 !! 
<b>꼭 바로 뒤에오는 태그가 아니여도 됌  </b>

<h1> 그룹 선택자</h1>
- ,로 구분 
- , 와 나열하게 되면 나열된 선택자 중에 포함되면 적용 


<h1> 구조 선택자</h1>
- first -child ,last-child:첫번째 자식 요소와 마지막 자식요소
- nth-last-child(N):뒤에서 N번째 자식 요소
- nth-child(n): N번째 자식 요소 
- only-child : 자식이 하나인 요소 
- nth-of-type(n), first-of-type, last-of-type



<h1> 의사 요소 선택자</h1>
- ::before
- ::after
- ::first-letter
- ::first-line


<h1>박스 모델 </h1>
- 박스의 크기를 설정할때 기준을 정하는 것
- Content-box 를 설정하면 내용을 기준으로 box의 크기를 설정하고 border-box를 설정하면 경계선을 기준으로 함 
- 영역에 대한 설정 : width,height,border,padding,margin


<h1>floating</h1>
- 블록 요소 주위에는 다른 요소가 배치될 수 없다 .
- 블록 요소 주위에 다른 요소를 배치하고자 할 때는 블록 요소를 inline 요소로 변경하거나 float 속성을 이용해서 영역은 차지하지만 공중에 떠 있는 형태로 만들어서 할 수도 있다. 
- float 속성에는 left,right,none 등 이있다 .
- 인라인 요소에도 float속성을 설정할수가있다. 설정할때는 
width,height를 설정해야하고 설정안하면 컨텐츠를 표시하는 영역이 최대한 확장이 되버린다.

- clear 속성에 none ,left,right,both를 설정해서 해제 =>블록 요소에서만 가능 
- overflow 속성에 auto나 hidden을 설정해서 해제 - 부모요소에 설정 

## 크기조절 
- resize 속성에 horizontal이나 vertical both를 설정해서 크기 조절이 가능하도록 할 수 ㅣㅇㅆ다 

<h1>display</h1>
- 박스의 보기 모드를 변경.
- block: 블록 요소로 만들어짐.
- inline: 인라인요소로 만들어지는데 height가 무시됨.
- inline-block: 인라인 요소로 만들면서 주위에 콘텐츠가 배치되지않고 height가 적용됨.
<h1>position</h1>
- 요소의 배치 방법을 설정하는ㄴ 것으로 기본값은 static
- 요소엔 static relative,absolute,fixed,sticky
- absolute: 부모의 왼쪽 위를 기준으로 해서 배치되는 것으로 left,top 을 이용해서 부모에서의 위치를선정 
- fixed: 웹 브라우저의 스크린 기준으로 배치되는 방식 
- sticky: 스크롤 영역을 기준으로 함 . 스크롤 하는 위치로부터 배치 
absolute 나 fixed 를 설정하면 그 요소의 display는 block으로 자동 변경 

위치 = left,top,bottom,right
