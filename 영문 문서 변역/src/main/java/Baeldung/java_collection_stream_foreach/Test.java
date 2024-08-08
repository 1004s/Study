package Baeldung.java_collection_stream_foreach;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {

        TestIterable list = new TestIterable();
        list.elementData.add("A");
        list.elementData.add("B");
        list.elementData.add("C");
        list.elementData.add("D");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();

        list.forEach(x -> System.out.print(x));

    }
}
