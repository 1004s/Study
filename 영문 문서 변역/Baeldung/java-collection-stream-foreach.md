# The Difference Between Collection.stream().forEach() and Collection.forEach()

## 1. Overview
자바에서 컬렉션을 반복하는 옵션에는 여러가지가 있다.
이번 글에서는 2가지 비슷한 접근 방식을 살펴본다.

대부분 둘다 동일한 경과를 보이지만, 몇 가지 미묘한 차이점을 볼 예정이다.

## 2. Simple List
 a. 가장 간단한 방법은 향상 for문을 이용하는 것이다.
 ```java
   for(String s : list) {
     // do something with s
   }
 ```
 b. 람다식으로도 가능하다(Funtional-style)
 ```java
    Consumer<String> consumer = s -> { System.out::println };
    list.forEach(consumer);
 ```
 c. stream의 forEach()를 사용할 수 있다.
 ```java
    list.stream().forEach(consumer);
 ```
