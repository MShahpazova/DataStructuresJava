import java.util.HashSet;
import java.util.Iterator;


public class MyLinkedList<T> implements Iterable<T> {
    private MyLinkedListNode<T> first;
    private MyLinkedListNode<T> last;
    private HashSet<MyLinkedListNode<T>> nodes = new HashSet<>();

    private int count;

    public T getFirst() {
        if (count == 0) {
            throw new IllegalStateException("List is empty.");
        }
        return first.getValue();
    }

    public T getLast() {
        if (count == 0) {
            throw new IllegalStateException("List is empty.");
        }
        return last.getValue();
    }

    public MyLinkedListNode<T> addFirst(T value) {

        first = new MyLinkedListNode<T>(value, null, first);
        if (count == 0) {
            last = first;

        } else {
            first.getNext().setPrev(first);
        }
        count++;
        nodes.add(first);
        return first;
    }

    public MyLinkedListNode<T> addLast(T value) {
        last = new MyLinkedListNode<T>(value, last, null);
        if (count == 0) {
            first = last;
        } else {
            last.getPrev().setNext(last);
        }
        count++;
        nodes.add(last);
        return last;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new IllegalStateException("List is empty.");
        }

        T value = first.getValue();
        nodes.remove(first);
        first = first.getNext();
        if (count == 1) {
            last = first;

        } else {
            first.setPrev(null);
        }

        count--;

        return value;
    }

    public T removeLast() {
        if (count == 0) {
            throw new IllegalStateException("List is empty.");
        }
        nodes.remove(last);
        T value = last.getValue();

        last = last.getPrev();

        if (count == 1) {
            first = last;
        } else {
            last.setNext(null);
        }

        count--;

        return value;
    }

    public int size() {
        return count;
    }

    public MyLinkedListNode<T> find(T value) {

        MyLinkedListNode current = first;
        while (current != null) {
            if (current.getValue().equals(value)) {
                break;
            }
            current = current.getNext();
        }

        return current;
    }

    public boolean remove(T value) {
        MyLinkedListNode node = find(value);
        return remove(node);
    }

    public boolean remove(MyLinkedListNode<T> node) {
        if (node == null) {
            return false;
        }
        if (!nodes.contains(node)){
            throw new IllegalArgumentException("Node doesn't exist");
        }
        nodes.remove(node);
        if (node.getNext() == null) {
            removeLast();
        } else if (node.getPrev() == null) {
            removeFirst();
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            count--;
        }
        return true;
    }

    public MyLinkedListNode<T> addBefore(MyLinkedListNode<T> node, T value){
        if (!nodes.contains(node)){
            throw new IllegalArgumentException("No such node.");
        }

        MyLinkedListNode<T> newNode = new MyLinkedListNode<>(value, node.getPrev(), node);
        node.setPrev(newNode);
        if (newNode.getPrev() != null) {
            newNode.getPrev().setNext(newNode);
        }
        else {
            first = newNode;
        }
        count++;
        nodes.add(newNode);
       return newNode;
    }

    public MyLinkedListNode<T> addAfter(MyLinkedListNode<T> node, T value){
        if (!nodes.contains(node)){
            throw new IllegalArgumentException("No such node.");
        }
        MyLinkedListNode<T> newNode = new MyLinkedListNode<T>(value, node, node.getNext());

        node.setNext(newNode);
        if (newNode.getNext() == null){
            last = newNode;
        }
        else{
            newNode.getNext().setPrev(newNode);
        }
        count++;
        nodes.add(newNode);
        return newNode;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private MyLinkedListNode<T> current = first;

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public T next() {
                T value = current.getNext().getValue();
                current = current.getNext();

                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }
}