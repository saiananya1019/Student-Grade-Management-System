import java.util.*;
import java.io.*;

class Student implements Serializable {
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

    public String getGrade() {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadFromFile();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Student\n2. Display All\n3. Search by Roll");
            System.out.println("4. Update Student\n5. Delete Student\n6. Display Grades");
            System.out.println("7. Sort by Marks\n8. Save & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> displayAll();
                case 3 -> searchByRoll(sc);
                case 4 -> updateStudent(sc);
                case 5 -> deleteStudent(sc);
                case 6 -> displayGrades();
                case 7 -> sortByMarks();
                case 8 -> {
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 8);

        sc.close();
    }

    public static void addStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();
        for (Student s : students) {
            if (s.roll == roll) {
                System.out.println("Roll number already exists.");
                return;
            }
        }
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

    public static void updateStudent(Scanner sc) {
        System.out.print("Enter Roll No to update: ");
        int roll = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.roll == roll) {
                System.out.print("Enter new name: ");
                s.name = sc.nextLine();
                System.out.print("Enter new marks: ");
                s.marks = sc.nextDouble();
                System.out.println("Student record updated.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void deleteStudent(Scanner sc) {
        System.out.print("Enter Roll No to delete: ");
        int roll = sc.nextInt();
        Iterator<Student> it = students.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.roll == roll) {
                it.remove();
                System.out.println("Student record deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void displayGrades() {
        if (students.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
        for (Student s : students) {
            s.display();
            System.out.println("Grade: " + s.getGrade());
        }
    }

    public static void sortByMarks() {
        students.sort((a, b) -> Double.compare(b.marks, a.marks));
        System.out.println("Students sorted by marks (high to low):");
        displayAll();
    }

    public static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            students = new ArrayList<>(); // start fresh if file not found
        }
    }
}
