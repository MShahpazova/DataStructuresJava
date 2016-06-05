import java.util.Iterator;

public class MyArrayDequeue<T> implements Iterable<T>{
    private static final int DEFAULT_CAPACITY = 8;
    private int count;
    private int head;
    private int tail;
    private Object[] elements;

    public MyArrayDequeue() {
        elements = new Object [DEFAULT_CAPACITY];
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public T peekFirst(){
        return (T)elements [head];
    }

    public T  peekLast(){
        return (T)elements[tail];
    }

    public void add(T element) {
        if (count == elements.length) {
            resize();
        }

        int index = tail + 1;
        if (index == elements.length){
            index = 0;
        }
        elements[index] = element;
        count++;
        tail = index;
    }

    public boolean contains(T element) {

        for (T item : this) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
//        int current = head;
//        for (int i = 0; i < count; i++) {
//            if (elements[current].equals(element)){
//                return true;
//            }
//            current++;
//            if (current == elements.length){
//                current = 0;
//            }
//        }
//        return false;
    }

    public T pop(){
        if (isEmpty()){
            throw new IllegalStateException("Queue is empty.");
        }
        int temp = tail;
        count--;
        if (--tail < 0){
            tail = elements.length - 1;
        }
        return (T)elements[temp];
    }

    public T dequeue(){
        if (isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        int temp = head;
        count--;
        if (++head == elements.length){
            head = 0;
        }
        return (T)elements[temp];
    }

    private void resize(){
        Object [] temp = new Object[elements.length * 2];
        int current = head;
        for (int i = 0; i < count; i++) {
            temp[i] = elements[current++];
            if (current == elements.length){
                current = 0;
            }
        }
        elements = temp;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            int currentIndex = head;
            @Override
            public boolean hasNext() {
                return currentIndex != tail;
            }

            @Override
            public T next() {
                T value =  (T)elements[currentIndex++];
                if (currentIndex == elements.length){
                    currentIndex = 0;
                }
                return value;
            }
        };

        return it;
    }
}
