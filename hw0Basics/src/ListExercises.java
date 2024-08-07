import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int result = 0;
        for (int x: L) {
            result += x;
        }
        return result;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List <Integer> evens = new ArrayList<>(); 
        for (int x: L){
            if ( x % 2 ==0 ){
                evens.add(x);
            }
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        Set<Integer> set1 = new HashSet<>(L1);
        List<Integer> common = new ArrayList<>();
        for (int num : L2){
            if (set1.contains(num)){
                common.add(num);
            }
        }
        return common;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for (String word : words){
            for (char ch : word.toCharArray()){
                if (ch == c){
                    count ++;
                }
            }
        }
        return count;
    }
}
