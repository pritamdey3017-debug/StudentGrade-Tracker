import java.util.*;
class Student{
    private String name;
    private int roll;
    private double[] marks;
    //constructor
    public Student(String name, int roll,int subjectCount){
        this.name=name;
        this.roll=roll;
        this.marks= new double[subjectCount];
    }
    //getter method
    public int getRoll(){
        return roll;
    }

    public int getSubjectCount(){
        return marks.length;
    }
    //setter method
    public void setMarks(double[] marks){
        this.marks=marks;
    }
    public double calculateTotal(){
        double total=0;
        int n=marks.length;
        for(int i=0; i<n; i++){
            total+=marks[i];
        }
        return total;
    }
    public double calculateAvg(){
        if(marks.length==0) return 0;
        return calculateTotal()/marks.length;
    }
    public double getHighest(){
        if(marks.length==0) return 0;

        double max=marks[0];
        for(int i=0; i<marks.length; i++){
            if(marks[i]>max){
                max=marks[i];
            }
        }
        return max;
    }
    public double getLowest(){
        if(marks.length==0) return 0;

        double min=marks[0];
        for(int i=0; i<marks.length; i++){
            if(marks[i]<min){
                min=marks[i];
            }
        }
        return min;
    }
    public String calculateGrade(){
        double avg=calculateAvg();
        if( 90<= avg && avg<=100) return "A+";
        else if(80<= avg && avg<90) return "A";
        else if(70<= avg && avg<80) return "B+";
        else if(60<= avg && avg<70) return "B";
        else if(40<= avg && avg<60) return "C";
        else return "SNC";
    }
   public void display(String[] subjects){
        System.out.println("\n========================================");
        System.out.println("           STUDENT REPORT CARD          ");
        System.out.println("========================================");

        System.out.printf("Name : %-20s\n", name);
        System.out.printf("Roll : %-20d\n", roll);

        System.out.println("----------------------------------------");
        System.out.println("Subjects            Marks");
        System.out.println("----------------------------------------");

        for(int i = 0; i < marks.length; i++){
            System.out.printf("%-20s %6.2f\n", subjects[i], marks[i]);
        }

        System.out.println("----------------------------------------");

        System.out.printf("Total Marks   : %6.2f\n", calculateTotal());
        System.out.printf("Average       : %6.2f\n", calculateAvg());
        System.out.printf("Highest Marks : %6.2f\n", getHighest());
        System.out.printf("Lowest Marks  : %6.2f\n", getLowest());
        System.out.printf("Grade         : %-10s\n", calculateGrade());

        System.out.println("========================================\n");
    }
}
//StudentsSet class
class StudentsSet{
    private ArrayList<Student> students;
    private String[] subjects;
    private Scanner sc;     //scanner inside class creating an object
    public StudentsSet(String[] subjects, Scanner sc){
        this.subjects=subjects;
        this.students=new ArrayList<>();
        this.sc=sc;
    }
    //Input marks with validation
    private double[] inputMarks(){
        double [] marks= new double[subjects.length];
        for(int i=0; i<subjects.length; i++){
            while(true){
                System.out.print("Enter marks of subject " +(i+1)+ " (out of 100):");
                double input=sc.nextDouble();
                if(input>=0 && input<=100){
                    marks[i]=input;
                    break;
                }
                else{
                    System.out.println("Invalid input! Please enter marks between 0 and 100.");
                }
            }
        }
        return marks;
    }
    public void initializeStudents(){
        System.out.println("Enter the number of student that you want to add: ");
        int num=sc.nextInt();
        for(int i=0; i<num; i++){
            addStudent();
        }
    }
    
    //Add student
    public void addStudent(){
        sc.nextLine();
        System.out.print("\nEnter Name: ");
        String name= sc.nextLine();
        System.out.print("Enter Roll: ");
        int roll=sc.nextInt();
        
        Student s =new Student(name, roll, subjects.length);
        double[] marks= inputMarks();
        s.setMarks(marks);
        students.add(s);
        System.out.println("Student Added Successfully\n");
    }
    //view all record
    public void viewALL(){
        if (students.size()==0){
            System.out.print("No students found! \n");
            return;
        }
        for(int i=0; i<students.size(); i++){
            students.get(i).display(subjects);
        }
    }
    //find there is any student or not
    public  Student findStudent(int roll){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getRoll()==roll){
                return students.get(i);
            }
        }
        return null;
    }
    //Search student
    public void display_SpecificStudent(){
        System.out.print("Enter roll: ");
        int roll= sc.nextInt();
        Student s = findStudent(roll);
        if(s!=null){
            System.out.println("\n---- Specific Student record----");
            s.display(subjects);
        }
        else{
            System.out.print("Student not found.\n");           
        }
    }
    //update student marks
    public void updateMarks(){
        System.out.print("Enter roll: ");
        int roll=sc.nextInt();
        Student s =findStudent(roll);
        if(s!=null){
            s.setMarks(inputMarks());
            System.out.print("Marks updated.");
        }
        else{
            System.out.print("Student not found\n");
        }
    } 

    //delete student
    public void deleteStudent(){
        if(students.size()==0){
            System.out.print("No students Available to delete.");
            return;
        }
        System.out.print("Enter roll: ");
        int roll=sc.nextInt();
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getRoll()==roll){
                students.remove(i);
                System.out.print("Student Deleted Successfully");
                return;
            }
        }
        System.out.print("Student not found!\n");
    }
}

public class studentGrade {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of subject: ");    
        int n= sc.nextInt();
        String[] subjects = new String[n];
        sc.nextLine();
        for(int i=0; i<n; i++){
            System.out.print("Enter the name of Subject " +(i+1) + ": ");
            subjects[i]=sc.nextLine();
        }
        StudentsSet stdset=new StudentsSet(subjects, sc);

        int choice;
        do{
            System.out.println("\n---- MENU ----");
            System.out.println("1. Add Student");
            System.out.println("2. view All");
            System.out.println("3. Display specific record by roll");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. EXIT");
            System.out.println("Enter your choice: ");
            choice= sc.nextInt();
            switch (choice){
                case 1: stdset.initializeStudents();
                        break;
                case 2: stdset.viewALL();
                        break;
                case 3: stdset.display_SpecificStudent();
                        break;
                case 4: stdset.updateMarks();
                        break;
                case 5: stdset.deleteStudent();
                        break;
                case 6: System.out.println("Exiting program...");
                        break;
                default:System.out.println("Your choice is incorrect");

            }
        }while (choice!=6);
        sc.close();
    }
}
