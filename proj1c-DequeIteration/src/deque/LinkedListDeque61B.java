package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node first;
    private Node last;
    private int size;


    private class LinkedListDeque61BIterator<T> implements Iterator<T> {
        private Node curNode;

        public LinkedListDeque61BIterator(){
            curNode = first;
        }

        @Override
        public boolean hasNext() {
            return curNode != last;
        }

        @Override
        public T next() {
            T temp = (T) curNode.item;
            curNode = curNode.next;
            return temp;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other instanceof LinkedListDeque61B oad){
            if (this.size != oad.size) { return false; }
            Iterator<T> otherIterator = oad.iterator();
            for (T x : this){
                T y = otherIterator.next();
                if (!x.equals(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return this.toList().toString();
    }

    public class Node{
        public Node prev;
        public Node next;
        public T item;

        public Node (T x){
            item = x;
        }
    }

    public LinkedListDeque61B(){
        first = new Node(null);
        last = new Node(null);
        first.next = last;
        last.prev = first;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode= new Node(x);
        Node firstNext = first.next;
        first.next = newNode;
        newNode.prev = first;
        newNode.next = firstNext;
        firstNext.prev = newNode;
        size ++;
    }

    @Override
    public void addLast(T x) {
        Node newNode= new Node(x);
        Node lastPrev = last.prev;
        last.prev = newNode;
        newNode.next = last;
        newNode.prev = lastPrev;
        lastPrev.next = newNode;
        size ++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node curNode = first.next;
        while(curNode != last){
            returnList.add(curNode.item);
            curNode = curNode.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }else{
            Node newNext = first.next.next;
            first.next = newNext;
            newNext.prev = first;
            size--;
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }else{
            Node newLast = last.prev.prev;
            newLast.next = last;
            last.prev = newLast;
            size--;
            return null;
        }
    }

    @Override
    public T get(int index) {
        Node result = first.next;
        int cur = index;
        if (index >= size || index < 0){
            return null;
        }else{
            while(cur > 0){
                result = result.next;
                cur--;
            }
            return result.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        int cur = index;
        Node curNode = first.next;
        if (index >= size || index < 0){
            return null;
        }else if (cur == 0){
            return curNode.next.item;
        }else{
            curNode = curNode.next;
            return getRecursive(cur-1);
        }
    }

}
