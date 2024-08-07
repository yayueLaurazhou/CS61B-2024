import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

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
