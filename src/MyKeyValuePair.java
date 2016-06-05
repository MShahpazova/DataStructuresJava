/**
 * Created by maria on 05.06.16.
 */
public class MyKeyValuePair<K, V> {
    private K key;
    private V value;

     MyKeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    private void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    private void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        MyKeyValuePair<K,V> other = (MyKeyValuePair<K,V>)(obj);
        return this.getKey().equals(other.getKey());
    }

    @Override
    public int hashCode() {
        return this.getKey().hashCode();
    }
}
