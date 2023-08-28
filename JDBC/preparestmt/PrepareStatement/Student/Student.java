package PrepareStatement.Student;

public class Student {
   private String name;
   private int rollNo;
   private char div;

    public Student(int rollNo, String name, char div ) {
        this.name = name;
        this.rollNo = rollNo;
        this.div = div;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public char getDiv() {
        return div;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }



    public void setStd(char div) {
        this.div = div;
    }


}
