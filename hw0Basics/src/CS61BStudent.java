import java.util.ArrayList;

public class CS61BStudent {
    public int idNumber; // Instance Variables
    public int grade;
    public static String instructor = "Hug"; // Class (Static) Variables

    public CS61BStudent(int id) { // Constructor
        this.idNumber = id;
        this.grade = 100;
    }
    public boolean watchLecture() { // Instance Method
// Returns whether the student actually watched the lecture
...
    }
    public static String getInstructor() { // Static Method
...
}


public class CS61B {
        public static String university = "UCBerkeley";
        public String semester;
        public CS61BStudent[] Students;

        public CS61B(String semester, int capacity, CS61BStudent[] signups){
            this.semester = semester;
            this.Students = new CS61BStudent[capacity];
            for (int i = 0; i < capacity; i ++){
                this.Students[i] = signups[i];
            }
        }

        public int makeStudentWatchLecture(){

        }
}
}