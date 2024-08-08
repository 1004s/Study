package Baeldung.java_collection_stream_foreach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class TestIterable implements Iterable<String> {

    public ArrayList<String> elementData;

    public TestIterable() {
        elementData = new ArrayList<>();
    }


    @Override
    public Iterator<String> iterator() {
        return this.elementData.iterator();
    }

//    @Override
//    public void forEach(Consumer<? super String> action) {
//        for (int i = 0; i < elementData.size(); i++) {
//            System.out.println("여기여기여기");
//        }
//    }
}
