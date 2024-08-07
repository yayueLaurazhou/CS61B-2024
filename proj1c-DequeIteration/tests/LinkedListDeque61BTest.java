import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {

    @Test
    public void TestIterator() {
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        td.addLast(1);
        td.addLast(2);
        td.addLast(3);

        Iterator<Integer> iterator = td.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void TestIteratorEmpty() {
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        Iterator<Integer> iterator = td.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void TestIteratorSingleElement(){
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        td.addLast(10);
        Iterator<Integer> iterator = td.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(10);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void TestToString(){
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        td.addLast(1);
        td.addLast(2);
        td.addLast(3);
        String tdToString = td.toString();
        assertThat(tdToString).isEqualTo("[1, 2, 3]");
    }

    @Test
    public void TestEqualsAddressEqual(){
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        td.addLast(1);
        td.addLast(2);
        td.addLast(3);
        Deque61B td2 = td;
        assertThat(td2.equals(td)).isTrue();
    }

    @Test
    public void TestEqualsContentEqual(){
        Deque61B<Integer> td = new ArrayDeque61B<Integer>();
        Deque61B<Integer> td2 = new ArrayDeque61B<Integer>();
        td.addLast(1);
        td.addLast(2);
        td.addLast(3);
        td2.addLast(1);
        td2.addLast(2);
        td2.addLast(3);
        assertThat(td2.equals(td)).isTrue();
    }


}
