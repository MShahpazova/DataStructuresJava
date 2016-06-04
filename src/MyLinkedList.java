import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by maria on 04.06.16.
 */
public class MyLinkedList<T> {
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
//    TODO implement method addAfter
}