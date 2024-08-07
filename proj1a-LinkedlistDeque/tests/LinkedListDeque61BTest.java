import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void removeFirstTest(){
         Deque61B<Integer> rrf = new LinkedListDeque61B<>();
         rrf.addFirst(3);
         rrf.addLast(-3);
         rrf.addFirst(1);
         rrf.removeFirst();
         assertThat(rrf.toList()).containsExactly(3,-3).inOrder();
    }

    @Test
    public void removeFirstToEmpty(){
        Deque61B<Integer> rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.removeFirst();
        rrf.removeFirst();
        assertThat(rrf.toList().isEmpty()).isTrue();
    }

    @Test
    public void removeLastToEmpty(){
        Deque61B<Integer> rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.removeLast();
        rrf.removeLast();
        assertThat(rrf.toList().isEmpty()).isTrue();
    }

    @Test
    public void AddFirstAfterRemoveToEmpty(){
        Deque61B<Integer> rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.removeFirst();
        rrf.removeFirst();
        rrf.addFirst(1);
        assertThat(rrf.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void AddLastAfterRemoveToEmpty(){
        Deque61B<Integer> rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.removeFirst();
        rrf.removeFirst();
        rrf.addLast(1);
        assertThat(rrf.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void removeLastTest(){
         Deque61B<Integer>rrf = new LinkedListDeque61B<>();
         rrf.addFirst(3);
         rrf.addLast(-3);
         rrf.addFirst(1);
         rrf.removeLast();
         assertThat(rrf.toList()).containsExactly(1,3).inOrder();
    }

    @Test
    public void removeFirstAndRemoveLastTest(){
         Deque61B<Integer>rrf = new LinkedListDeque61B<>();
         rrf.addFirst(3);
         rrf.addLast(-3);
         rrf.addFirst(1);
         rrf.addLast(-1);
         rrf.addFirst(5);
         rrf.removeLast();
         rrf.removeFirst();
         rrf.removeLast();
         assertThat(rrf.toList()).containsExactly(1,3).inOrder();
    }

    @Test
    public void size(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.addFirst(1);
        rrf.addLast(-1);
        rrf.addFirst(5);
        assertThat(rrf.size()).isEqualTo(5);
    }

    @Test
    public void sizeAfterRemoveToEmpty(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addLast(-3);
        rrf.addFirst(1);
        rrf.removeLast();
        rrf.removeLast();
        rrf.removeFirst();
        assertThat(rrf.size()).isEqualTo(0);
    }

    @Test
    public void getValid(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        assertThat(rrf.get(1)).isEqualTo(-3);
    }

    @Test
    public void getOobLarge(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        assertThat(rrf.get(1000)).isNull();
    }

    @Test
    public void getOobNegative(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        assertThat(rrf.get(-100)).isNull();
    }

    @Test
    public void getRecursiveValid(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        rrf.addFirst(9);
        assertThat(rrf.getRecursive(2)).isEqualTo(1);
    }

    @Test
    public void getRecursiveOobLarge(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        assertThat(rrf.getRecursive(1000)).isNull();
    }

    @Test
    public void getRecursiveOobNegative(){
        Deque61B<Integer>rrf = new LinkedListDeque61B<>();
        rrf.addFirst(3);
        rrf.addFirst(-3);
        rrf.addFirst(1);
        assertThat(rrf.getRecursive(-100)).isNull();
    }
}