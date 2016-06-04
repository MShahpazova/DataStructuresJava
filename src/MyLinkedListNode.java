public class MyLinkedListNode<T> {
    private T value;
    private MyLinkedListNode<T> prev;
    private MyLinkedListNode<T> next;

    MyLinkedListNode(T value, MyLinkedListNode<T> prev, MyLinkedListNode<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    MyLinkedListNode<T> getPrev() {
        return prev;
    }

    void setPrev(MyLinkedListNode<T> prev) {
        this.prev = prev;
    }

    MyLinkedListNode<T> getNext() {
        return next;
    }

    void setNext(MyLinkedListNode<T> next) {
        this.next = next;
    }
}
