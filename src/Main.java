/**
 * Created by maria on 04.06.16.
 */
public class Main {
    public static void main(String[] args) {
//        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
//        MyLinkedList<String> list2 = new MyLinkedList<>();
//
//       MyLinkedListNode<String> node = list2.addFirst("added");
//        list2.removeFirst();
//
//        list2.addFirst("first");
//        list2.addLast("last");
//        myLinkedList.addFirst("node");
//       //myLinkedList.addFirst("node2");
//
//        boolean removed = myLinkedList.remove(node);

        MyHashMap<Character, Integer> map = new MyHashMap<>();
        for (int i = 'A'; i < 'Z'; i++) {
            map.put((char)i, i - 'A' + 1);
        }

        for (MyKeyValuePair<Character, Integer> pair:
             map) {
            System.out.println(pair.getKey() + " --> " + pair.getValue());
        }
    }
}
