import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{

    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    BSTNode root = null;
    ArrayList<K> keylist = new ArrayList<>();
    int size = 0;


    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            BSTNode node = getHelper(key,root);
            node.value = value;
        } else {
            keylist.add(key);
            size ++;
            root = putHelper(key, value, root);
        }
    }

    public BSTNode putHelper(K key, V value, BSTNode currnode){
        if (currnode == null){
            return new BSTNode(key, value);
        } else if (currnode.key.compareTo(key) > 0){
            currnode.left = putHelper(key, value, currnode.left);
        } else {
            currnode.right = putHelper(key, value, currnode.right);
        }
        return currnode;
    }

    @Override
    public V get(K key) {
        if (root == null){
            return null;
        }
        if (getHelper(key,root) == null){
            return null;
        } else {
            return getHelper(key, root).value;
        }
    }


    private BSTNode getHelper(K key, BSTNode startnode){
        if (startnode.key.equals(key)){
            return startnode;
        } else if (startnode.key.compareTo(key) > 0){
            if (startnode.left == null){
                return null;
            }
            startnode = startnode.left;
        } else {
            if (startnode.right == null){
                return null;
            }
            startnode = startnode.right;
        }
        return getHelper(key, startnode);
    }

    @Override
    public boolean containsKey(K key) {
        return keylist.contains(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
        keylist = new ArrayList<>();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keylist.iterator();
    }

//    private class BSTMapIter implements Iterator<K>{
//
//        BSTNode curr;
//
//        public BSTMapIter(){
//            curr = root;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return (curr.left!= null || curr.right!= null);
//        }
//
//        // traverse from left to right
//        @Override
//        public K next() {
//
//        }
//    }

}
