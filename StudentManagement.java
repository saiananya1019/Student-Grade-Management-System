import java.util.*;

class Student {
    int roll;
    String name;
    double marks;

    public Student(int roll, String name, double marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public void display() {
        System.out.println("Roll: " + roll + ", Name: " + name + ", Marks: " + marks);
    }
}

public class StudentManagement {
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student\n2. Display All\n3. Search by Roll\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    searchByRoll(sc);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }

    public static void addStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();  // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        students.add(new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }

    public static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Student s : students) {
                s.display();
            }
        }
    }

    public static void searchByRoll(Scanner sc) {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.roll == roll) {
                s.display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
}

