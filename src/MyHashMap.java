import java.util.Iterator;

public class MyHashMap <K, V> implements Iterable<MyKeyValuePair<K,V>>{

    private final static int DEFAULT_BUCKETS_CAPACITY = 16;
    private final static double MAX_LOAD_FACTOR = 0.75;
    private Object[] buckets;
    private int count;

    public MyHashMap() {
        this.buckets = new Object[DEFAULT_BUCKETS_CAPACITY];
    }

    public boolean containsKey(K key){
        int index = key.hashCode()%buckets.length;
        MyLinkedList<MyKeyValuePair<K,V>> list =  (MyLinkedList<MyKeyValuePair<K,V>>) buckets[index];

        return list.find(new MyKeyValuePair(key, null)) != null;
    }

    public V get(K key){
        int index = key.hashCode() % buckets.length;
        MyLinkedList<MyKeyValuePair<K,V>> list =  (MyLinkedList<MyKeyValuePair<K,V>>) buckets[index];
        MyKeyValuePair<K,V> pair = list.find(new MyKeyValuePair<K,V>(key, null)).getValue();
        if (pair == null){
            throw new IllegalArgumentException("No such key");
        }
         return pair.getValue();
    }

    public int size(){
        return count;
    }

    public boolean containsValue(V value){
        for (int i = 0; i < buckets.length ; i++) {
            MyLinkedList<MyKeyValuePair<K,V>> currentList = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[i];
            if (currentList == null){
                continue;
            }

            for (MyKeyValuePair<K,V> pair:
                 currentList) {
                if (pair.getValue().equals(value)){
                    return true;
                }
            }
        }

        return false;
    }

    public void put(K key, V value){
        int index = key.hashCode() % buckets.length;
        MyLinkedList<MyKeyValuePair<K,V>> list = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[index];
        MyKeyValuePair<K,V> pair = new MyKeyValuePair<>(key, value);
        if (list == null){
            buckets[index] = new MyLinkedList<>();
            list = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[index];
        }

        if (list.find(pair) != null){
            throw new IllegalArgumentException("Key already exists");
        }
        list.addFirst(pair);
        count++;
    }

    public void remove(K key){
        int index = key.hashCode() % buckets.length;
        MyLinkedList<MyKeyValuePair<K,V>> list = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[index];
        if (list == null){
            return;
        }
        MyLinkedListNode<MyKeyValuePair<K,V>> node = list.find(new MyKeyValuePair<K, V>(key, null));
        if (node != null){
            list.remove(node);
            count--;
        }
    }

    private double getLoadFactor(){
        return (double)count/buckets.length;
    }

    @Override
    public Iterator<MyKeyValuePair<K, V>> iterator() {

        Iterator<MyKeyValuePair<K,V>> it = new Iterator<MyKeyValuePair<K, V>>() {
            int index = 0;
            int iteratedNodes = 0;

            @Override
            public boolean hasNext() {
                if (iteratedNodes == count) {
                    return false;
                }

                return true;
            }

            @Override
            public MyKeyValuePair<K, V> next() {
                MyLinkedList<MyKeyValuePair<K,V>> list = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[index];
                while (list == null) {
                    list = (MyLinkedList<MyKeyValuePair<K,V>>)buckets[++index];
                }

                MyKeyValuePair<K,V> pair = list.iterator().next();

                if (!list.iterator().hasNext()) {
                    index++;
                }

                iteratedNodes++;
                return pair;
            }
        };

        return it;
    }
}
