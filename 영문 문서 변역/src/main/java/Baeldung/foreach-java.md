# **Guide to the Java 8 forEach**

# **1. Overview**

Java 8에 forEach문이 도입되어서 컬렉션을 간결하게 반복(iterate)할 수 있게 되었다.

이 글의 목표: 컬렉션과 forEach를 어떻게 사용하는지, 어떤 종류의 인수(argument)가 필요한지, enhanced for문(foreach문)과 forEach문의 차이가 무엇인지 

# **2. forEach 기본**

Java에서 Collection 인터페이스는 super interface로 Iterable을 갖는다.<br>

그리고 Iterable 인터페이스는 Java 8 부터 새로운 API를 갖는다.<br>

```java

void forEach(Consumer<? super T> action)

```
<br>

forEach의 Javadoc에는<br> 

```
    Iterable의 각 요소에 대해 지정된 작업을 수행합니다.
    작업은 모든 요소가 처리되거나, 작업에서 예외가 발생할 때까지 수행합니다.
```

라고 명시되어 있다.

<br>

따라서 forEach를 사용하면, 다른 Iterator와 마찬가지로 컬렉션을 반복(iterate)하면서 각 요소에 지정된 작업을 수행할 수 있다.

<br><br>

예시로 String collection을 반복(iterate)하고 print하는 for-loop문은 다음과 같다.

```java
for (String name : names) {
    System.out.println(name);
}
```

<br>
forEach문을 사용하면 다음과 같이 쓸 수 있다.

```java
names.forEach(name -> {
    System.out.println(name);
});
```

<br>

# **3. forEach 메서드 사용**

forEach를 사용하면 collection을 반복(iterate)하며 각 요소에 특정 작업을 수행할 수 있다.<br>

**수행할 작업은 Consumer 인터페이스를 구현하는 클래스에 포함되어 있고, 주로 forEach에 인수(argument)로 전달된다.**

<br>

Consumer 인터페이스는 함수형 인터페이스(단일 추상 메서드가 있는 인터페이스)이다.

```java
@FunctionalInterface
public interface Consumer {
    void accept(T t);
}
```

<br>
예를 들어 단순히 String을 print하는 Consumer 구현을 다음과 같이 할 수 있고,

```java
Consumer<String> printConsumer = new Consumer<String>() {
public void accept(String name) {
        System.out.println(name);
    };
};
```

<br>
이것은 forEach에 argument로 다음과 같이 넘겨줄 수 있다.

```java
names.forEach(printConsumer);
```

<br>
Consumer로 작업을 지정하고 forEach API를 사용하는 방법 외에도 다른 방법들이 더 있다.

<br><br>

forEach 메서드를 사용하는 가장 유명한 3가지 방법을 알아보자.

# **3.1. Anonymous *Consumer* Implementation**

익명 클래스를 사용하여 Consumer 인터페이스 구현을 인스턴스화한 다음 forEach 메서드에 인수로 적용할 수 있다.

```java
Consumer<String> printConsumer = new Consumer<String>() {
public void accept(String name) {
        System.out.println(name);
    }
};
names.forEach(printConsumer);
```

이 방법은 잘 동작한다.

<br>

예제를 분석하면, 실제로 중요한 부분은 accept() 메서드 내부의 코드이다.<br>

지금은 람다 표현식이 표준이고, 함수형 인터페이스를 구현하는 더 쉬운 방법이지만, 익명 클래스를 구현하는 방식을 아는 것은 여전히 가치가 있다.

<br>

# **3.2. Lambda Expression**

Java 8 함수형 인터페이스의 가장 큰 이점은 람다 표현식을 사용하여 인스턴스화가 가능하고, bulky한 익명 클래스 구현을 사용하지 않아도 된다는 것이다.<br>

Consumer Interface는 함수형 인터페이스이므로 람다 표현식으로 작성할 수 있다.

```java
(argument) -> { //body }
```

<br>
따라서 printConsumer는 다음과 같이 간소화된다.

```java
name -> System.out.println(name)
```

<br>
그리고 이것을 다음과 같이 forEach에 전달해줄 수 있다.

```java
names.forEach(name -> System.out.println(name));
```

<br>
Java 8에서 람다 표현식이 도입된 이후로, 람다 표현식이 forEach 메서드를 사용하는 가장 일반적인 방법일 것이다.<br>



# **3.3. Method Reference**

클래스에 작업을 수행하기 위한 메서드가 이미 존재하는 경우, 일반적인 람다 문법 대신 메서드 참조 구문을 사용할 수 있다.

```java
names.forEach(System.out::println);
```

<br>

# **4. forEach로 작업하기**

# **4.1. Collection 반복(iterate)하기**

컬렉션 중 iterable 타입(list, set, queue 등)은 forEach를 사용하는 것과 동일한 syntax를 갖는다.<br>

따라서 앞서 살펴본 대로 List에서는 다음과 같이 요소를 반복(iterate)할 수 있다.

```java
List<String> names = Arrays.asList("Larry", "Steve", "James");

names.forEach(System.out::println);
```

<br>
Set에서는 다음과 같이 요소를 반복할 수 있다.

```java
Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));

uniqueNames.forEach(System.out::println);
```

<br>
Queue에서는 다음과 같이 요소를 반복할 수 있다.

```java
Queue<String> namesQueue =new ArrayDeque<>(Arrays.asList("Larry", "Steve", "James"));

namesQueue.forEach(System.out::println);
```

<br><br>

# **4.2. Map의 forEach를 사용하여 Map 반복(iterate)하기**

Map은 반복 가능하지 않지만(iterable하지 않지만) **BiConsumer를 허용하는 자체적인 forEach 변형을 제공**한다.<br>

Java 8은 Map의 forEach에 Consumer 대신 BiConsumer를 도입하였고, <br>
따라서 Map의 key와 value에 대해 동시에 작업을 수행할 수 있다.<br>

<br>

다음 항목(entry)들을 가지고 Map을 만들어보자.

```java
Map<Integer, String> namesMap =new HashMap<>();
namesMap.put(1, "Larry");
namesMap.put(2, "Steve");
namesMap.put(3, "James");
```

<br>
다음으로는, Map의 forEach를 사용해서 namesMap을 반복(iterate)해보자.

```java
namesMap.forEach((key, value) -> System.out.println(key + " " + value));
```

<br>
아래에서 볼 수 있듯이, 우리는 Map의 entry를 반복하기 위해 BiConsumer를 사용했다.

```java
(key, value) -> System.out.println(key + " " + value)
```

<br>

### Consumer 인터페이스와 BiConsumer 인터페이스

( 개인적으로 찾아본 내용입니다 )

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    ...
}
```

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
    ...
}
```

<br><br>

# **4.3. Map의 entrySet을 반복하여 Map을 반복하기**

Iterable의 forEach를 사용하여 Map의 EntrySet을 반복하는 방법도 있다.<br>

Map의 항목(entry)들은 EntrySet이라는 Set에 저장되므로, forEach를 사용하여 entrySet을 반복할 수 있다.

```java
namesMap.entrySet().forEach(entry -> System.out.println(
  entry.getKey() + " " + entry.getValue()));
```

<br><br>

# **5. Foreach vs For-loop**

간단히 말하면, foreach와 일반 for문 모두 “컬렉션의 요소를 반복하는” 동일한 기능을 제공한다.<br>

foreach와 for문의 큰 차이점은 다른 반복자(iterator)라는 것이다.<br>

향상for문은 외부 반복자(iterator)이고, forEach 메서드는 내부 반복자(iterator)이다.<br>

<br>

# **5.1. 내부 반복자 — *forEach***

이 반복자 타입은 백그라운드에서 반복을 관리한다. <br>

프로그래머는 컬렉션 요소에 대해 수행해야 할 작업을 코딩하기만 하면 된다.<br>

반복자(iterator)는 반복을 관리하고 요소를 하나씩 처리한다.<br>

<br>
내부 반복자 예시를 보자.

```java
names.forEach(name -> System.out.println(name));
```

위의 forEach 메서드에서 제공된 인수가 람다 표현식임을 알 수 있다.<br>

이것은 메서드는 수행해야할 작업이 무엇인지만 알면 되고, 모든 반복 작업은 내부적으로 처리된다는 의미이다.<br>

<br>

# **5.2. 외부 반복자 — *for-loop***

외부 반복자는 루프를 어떻게 실행할지, 루프에서 무엇을 실행할지를 모두 고려한다.<br>

Enumeration, Iterator, enhanced for-loop는 모두 외부 반복자이다.(iteartor(), next(), hasNext() 메서드를 떠올려보자.)<br>

우리가 해야할 일은 외부 반복자에서 반복을 어떻게 수행할지 정의하는 것이다.<br>

<br>

다음 루프문을 보자.

```java
for (String name : names) {
    System.out.println(name);
}
```

리스트를 반복(iterate)하는 동안 hasNext()나 next() 메서드를 명시적으로 호출하지는 않지만,<br>

위의 코드는 반복을 수행할 때 내부적으로 hasNext()나 next() 메서드를 사용한다.<br>

<br>

(진짜인지 궁금해서 개인적으로 컴파일 시켜본 코드입니다)

```java
// 컴파일 전 .java 파일
public static void main(String[] args) {
        List<String> names = Arrays.asList("Larry", "Steve", "James");
        for (String name : names) {
            System.out.println(name);
        }
    }
```

```java
// 컴파일된 .class 파일
public static void main(String[] args) {
        List<String> names = Arrays.asList("Larry", "Steve", "James");
        Iterator var2 = names.iterator();

        while(var2.hasNext()) {
            String name = (String)var2.next();
            System.out.println(name);
        }

    }
```

<br>

이러한 복잡한 작업은 숨겨져있어서 프로그래머에게는 보이지 않지만 실제로 존재한다.<br>

Collection이 반복을 직접 수행하는 내부 반복자와 달리, 여기서는 컬렉션에서 모든 요소를 꺼내는 외부 코드가 필요하다.<br>

# **6. 결론**

이 글에서 forEach 루프가 일반적인 for-loop보다 편리하다는 것을 알게 되었다.<br>

또한 forEach 메서드가 어떻게 작동하는지, 그리고 컬렉션의 각 요소에 대한 작업을 수행하기 위해 어떤 종류의 구현체를 인수(argument)로 받을 수 있는지 살펴보았다.<br>

마지막으로, 이 글에서 사용된 모든 코드 snippet은 [GitHub](https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-8) 리포지토리에 있다.
