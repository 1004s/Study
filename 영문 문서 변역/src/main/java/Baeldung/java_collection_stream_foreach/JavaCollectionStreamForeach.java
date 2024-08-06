package Baeldung.java_collection_stream_foreach;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

public class JavaCollectionStreamForeach {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D");

//        System.out.println("Enhanced for-loop");
//        for(String s : list) {
//            System.out.println(s);
//        }
//        System.out.println();
//
//        System.out.println("Lamda expression");
//        list.forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("Stream foreach");
//        list.stream().forEach(System.out::print);
//
//        System.out.println();
//        System.out.println("Parallel Streams");
//        list.parallelStream().forEach(System.out::print);
//        System.out.println();
//        System.out.println();

        List<String> myList = new ReverseList();
        myList.addAll(list);

        Iterator iterator = myList.iterator();
        System.out.println("custom iterator 직접 사용");
        while(iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();

        // 구현한 iterator를 호출해서 사용하고 있음
        System.out.println("enhanced for-loop");
        for(String s : myList) {
            System.out.print(s);
        }
        System.out.println();

        // 찾았다 범인, 컴파일러가 forEach를 컴파일 할때 iterable로 사용하는게 아니라
        // 그냥 list의 foreach문을 호출하고 있음
        // foreach함수를 보면 안에서 향상for문을 사용하고 있는데 파라미터로 넘긴 Consumer 클래스(Functional 클래스) 대로 동작
        System.out.println("그냥 forEach() 사용");
        myList.forEach(x -> System.out.print(x));
        System.out.println();

        //stream의 foreach 호출
        System.out.println("스트림 forEach() 사용");
        myList.stream().forEach(System.out::print);

        Consumer<String> removeElement = s -> {
            System.out.println(s + " " + list.size());
            if (s != null && s.equals("A")) {
                list.remove("D");
            }
        };

        list.forEach(removeElement);


    }
}
