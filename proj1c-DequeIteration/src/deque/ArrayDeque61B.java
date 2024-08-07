package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] data;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B(){
        this.data = (T[]) new Object[6];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    private class ArrayDeque61BIterator<T> implements Iterator<T>{
        private int count;
        private int curIndex;

        public ArrayDeque61BIterator(){
            count = 0;
            curIndex = Math.floorMod((nextFirst + 1), data.length);
        }

        @Override
        public boolean hasNext() {
            return (count < size);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = (T) data[curIndex];
            count++;
            curIndex = Math.floorMod((curIndex + 1), data.length);
            return item;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDeque61BIterator();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) { return true; }
        if (other instanceof ArrayDeque61B oad){
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

    @Override
    public void addFirst(T x) {
        if (size >= data.length){
            resize(size * 2);
        }
        data[nextFirst] = x;
        nextFirst = Math.floorMod((nextFirst - 1), data.length);
        size ++;
    }

    @Override
    public void addLast(T x) {
        if (size >= data.length){
            resize(size * 2);
        }
        data[nextLast] = x;
        nextLast = Math.floorMod((nextLast + 1), data.length);
        size ++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = Math.floorMod((nextFirst + 1), data.length);
        for (int i = 0; i < size; i ++){
            returnList.add(data[index]);
            index = Math.floorMod(index + 1, data.length);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (data.length >= size * 4){
            resize(data.length / 2);
        }
        int deleteIndex = Math.floorMod((nextFirst + 1), data.length);
        data[deleteIndex] = null;
        nextFirst = Math.floorMod((nextFirst + 1), data.length);
        size --;
        return null;
    }

    @Override
    public T removeLast() {
        if (data.length >= size * 4){
            resize(data.length / 2);
        }
        int deleteIndex = Math.floorMod((nextLast - 1), data.length);
        data[deleteIndex] = null;
        nextLast = Math.floorMod((nextLast - 1), data.length);
        size --;
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        int indexInData = Math.floorMod((nextFirst + index + 1), data.length);
        return data[indexInData];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void resize(int capacity){
        T[] newData = (T[]) new Object[capacity];
        int index = Math.floorMod(nextFirst + 1, data.length);
        for (int i = 0; i < size; i ++){
            newData[i] = get(index);
            index = Math.floorMod(index + 1, data.length);
        }
        data = newData;
        nextFirst = capacity - 1;
        nextLast = size;
    }
}


