제네릭 클래스, 제네릭 인터페이스를 모두 합쳐서 “제네릭 타입” 이라고 부른다.

# Generic Class

- 클래스명 오른쪽 다이아몬드 연산자(`<>`) 안에 “ **타입 매개변수** ”를 정의하는 클래스를 제네릭 클래스라고 한다.
- 클래스 정의 시점에 클래스 내부 변수 타입을 미리 결정되지 않는다.
- 클래스 생성 시점에 타입 인자로 타입을 넘겨주면, 타입 매개변수가 모두 지정해준 타입으로 변한다.

- 제네릭 클래스

```java
// 클래스 정의 시점에는 타입이 결정되지 않는다.
// 여기에서 T가 타입 매개변수이다.
class ClassA<T> {

    private T param1;
    
}

...

class Main {
    public static void main(String[] args) {
        // 클래스 생성 시점에 타입 인자로 String을 넘겨준다.
        ClassA<String> classA = new ClassA<>(); 
        // 그러면, ClassA의 타입 매개변수 T가 모두 String으로 변한다.
    }
}
```

### 타입 매개변수 관례

✅ 타입 매개변수로는 아무 문자를 써도 상관 없지만,

관례적으로 용도에 맞게 자주 쓰는 영어 대문자를 사용한다.

```java
E - element

K - key

N - number

T - type

V - value

S, U, V - 2nd, 3rd, 4th types
```

✅ 타입 매개변수를 한 번에 여러 개 선언해도 된다.

```java
class ClassA<K, V> {

    private K param1;
    private V param2;

}
```

✅ 타입 인자로는 기본형 타입은 들어갈 수 없고, 참조형 타입만 들어갈 수 있다.

✅ 타입 인자를 지정해주지 않아도 컴파일이 되는데,
    
- 이것을 “row type으로 지정되었다”고 하며 실제로는 타입 매개변수로 Object 타입이 전달되는 것과 같은 효과이다.
- Row 타입의 존재 이유는 제네릭이 등장하기 이전 코드들과의 호환성을 위해서이다.

```java
class GenericBox<T> {
...
}

class Main {
    public static void main(String[] args) {
        GenericBox box = new GenericBox(); // 가능. Row type
    }
}
```

✅ 제네릭을 선언할 때에는 컴파일러가 타입 추론을 할 수 있기 때문에, 등호 오른쪽에서는 타입을 선언하지 않아도 된다.

```java
GenericBox<String> box = new GenericBox<String>(); // 이렇게 써도 되지만,

GenericBox<String> box = new GenericBox<>(); // 타입 추론이 가능하기 때문에 타입을 적어주지 않아도 된다.

GenericBox<> box = new GenericBox<String>(); // 이건 불가능
```

# 제네릭을 사용하는 이유

1 ) 코드 중복 없애면서,

2 ) 타입 안정성을 위해 

예시) 

```java
public class IntegerBox {

    private Integer value;

    public void set(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return value;
    }
}

public class StringBox {

    private String value;

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
```

DoubleBox, CharacterBox, StudentBox ….

계속 생성해야 한다면 코드 중복 문제가 발생한다.

### 코드 중복 문제는 Object 타입을 사용하여도 해결할 수 있다.

```java
public class ObjectBox {
    
    private Object value;
    
    public void set(Object value) {
        this.value = value;
    }
    
    public Object get() {
        return  value;
    }
}

```

### → 문제점

(1) 값을 꺼낼 때 다운캐스팅을 해야 하는 번거로움 

(2) 잘못된 타입의 인수가 전달된 상황에서 다운캐스팅 시도할 때, 컴파일 타임에는 오류를 잡을 수 없다. (즉 타입 안정성이 보장되지 않는다.)

```java
public class Main {

    public static void main(String[] args) {
        ObjectBox box = new ObjectBox();
        box.set("Hello!");
        
        // 값을 꺼낼 때 타입캐스팅 필요
        String value = (String) box.get();
        
        // 컴파일 시 오류 x
        Integer intValue = (Integer) box.get();
        
    }
}

```

## ✅  타입 안정성까지 보장받으려면 제네릭을 사용해야 한다.

```java
public class GenericBox<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

}
```

제네릭을 사용하면

- 객체를 꺼내서 사용할 때 다운캐스팅을 할 필요가 없음
- 잘못된 타입의 인수가 전달되거나, 잘못된 타입으로 꺼내려고 하면 컴파일 오류가 발생한다.
