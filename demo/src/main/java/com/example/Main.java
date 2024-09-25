import java.util.Scanner;

class Student {
    String name;
    String phoneNumber;
    String emailAddress;
    String feedback;

    Student() {
        this.name = "";
        this.phoneNumber = "";
        this.emailAddress = "";
        this.feedback = "";
    }
}

class Teacher {
    String name;
    Student[] students;

    Teacher(int studentCount) {
        this.name = "";
        this.students = new Student[studentCount];
        for (int i = 0; i < studentCount; i++) {
            students[i] = new Student();
        }
    }
}

public class Main {
    static final int NUM_INSTITUTIONS = 3;
    static final int STUDENTS_PER_INSTITUTION = 2;
    static Scanner scanner = new Scanner(System.in);

    // Function to display student information and receive feedback
    public static void giveFeedback(Student student) {
        System.out.println("\nStudent Information:");
        System.out.println("Name: " + student.name);
        System.out.println("Phone Number: " + student.phoneNumber);
        System.out.println("Email Address: " + student.emailAddress);
        System.out.println("Feedback: " + student.feedback);

        System.out.print("\nEnter updated feedback for the student: ");
        student.feedback = scanner.nextLine();
    }

    // Function to add a new student
    public static void addStudent(Teacher teacher) {
        Student newStudent = new Student();
        System.out.println("\nEnter information for the new student:");
        System.out.print("Enter student name: ");
        newStudent.name = scanner.nextLine();

        System.out.print("Enter student phone number: ");
        newStudent.phoneNumber = scanner.nextLine();

        System.out.print("Enter student email address: ");
        newStudent.emailAddress = scanner.nextLine();

        for (int i = 0; i < STUDENTS_PER_INSTITUTION; i++) {
            if (teacher.students[i].name.isEmpty()) {
                teacher.students[i] = newStudent;
                break;
            }
        }

        System.out.println("New student added successfully.");
    }

    // Function to delete a student
    public static void deleteStudent(Teacher teacher) {
        System.out.print("Enter the index of the student to delete (1-" + STUDENTS_PER_INSTITUTION + "): ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer

        if (index >= 1 && index <= STUDENTS_PER_INSTITUTION) {
            teacher.students[index - 1] = new Student();  // Reset the student's data
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Function to display all feedback for a teacher
    public static void viewAllFeedback(Teacher teacher) {
        System.out.println("\nFeedback for students supervised by " + teacher.name + ":");
        for (Student student : teacher.students) {
            if (!student.name.isEmpty()) {
                System.out.println("Student: " + student.name);
                System.out.println("Feedback: " + student.feedback);
            }
        }
    }

    // Function to add a new teacher
    public static void addTeacher(Teacher[] teachers, int numTeachers) {
        if (numTeachers >= NUM_INSTITUTIONS) {
            System.out.println("Cannot add more teachers. Maximum number reached.");
            return;
        }

        Teacher newTeacher = new Teacher(STUDENTS_PER_INSTITUTION);
        System.out.println("\nEnter information for the new teacher:");
        System.out.print("Enter teacher name: ");
        newTeacher.name = scanner.nextLine();

        teachers[numTeachers] = newTeacher;
        System.out.println("New teacher added successfully.");
    }

    // Function to delete a teacher
    public static void deleteTeacher(Teacher[] teachers, int numTeachers) {
        System.out.print("Enter the index of the teacher to delete (1-" + numTeachers + "): ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer

        if (index >= 1 && index <= numTeachers) {
            for (int i = index - 1; i < numTeachers - 1; i++) {
                teachers[i] = teachers[i + 1];
            }
            System.out.println("Teacher deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public static void main(String[] args) {
        Teacher[] teachers = {
            new Teacher(STUDENTS_PER_INSTITUTION),
            new Teacher(STUDENTS_PER_INSTITUTION),
            new Teacher(STUDENTS_PER_INSTITUTION)
        };

        teachers[0].name = "Teacher1";
        teachers[0].students[0] = new Student();
        teachers[0].students[0].name = "Student1";
        teachers[0].students[0].phoneNumber = "111-111-1111";
        teachers[0].students[0].emailAddress = "student1@agmail.com";

        teachers[0].students[1] = new Student();
        teachers[0].students[1].name = "Student2";
        teachers[0].students[1].phoneNumber = "222-222-2222";
        teachers[0].students[1].emailAddress = "student2@gmail.com";

        // Continue initializing the other teachers and students as in the C++ code

        System.out.print("Enter user type (1 for Manager, 2 for Teacher): ");
        int userType = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer

        if (userType == 1) {
            int choice;
            do {
                System.out.println("\nManager Menu:");
                System.out.println("1. Add Teacher");
                System.out.println("2. Delete Teacher");
                System.out.println("3. View All Feedback");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer

                switch (choice) {
                    case 1:
                        addTeacher(teachers, NUM_INSTITUTIONS);
                        break;
                    case 2:
                        deleteTeacher(teachers, NUM_INSTITUTIONS);
                        break;
                    case 3:
                        for (Teacher teacher : teachers) {
                            viewAllFeedback(teacher);
                        }
                        break;
                    case 0:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 0);
        } else if (userType == 2) {
            System.out.print("Enter teacher's name: ");
            String teacherName = scanner.nextLine();

            int teacherIndex = -1;
            for (int i = 0; i < NUM_INSTITUTIONS; i++) {
                if (teachers[i].name.equals(teacherName)) {
                    teacherIndex = i;
                    break;
                }
            }

            if (teacherIndex != -1) {
                int choice;
                do {
                    System.out.println("\nTeacher Menu:");
                    System.out.println("1. Give Feedback");
                    System.out.println("2. Add Student");
                    System.out.println("3. Delete Student");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Clear the buffer

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the index of the student to give feedback (1-" + STUDENTS_PER_INSTITUTION + "): ");
                            int feedbackIndex = scanner.nextInt();
                            scanner.nextLine();  // Clear the buffer

                            if (feedbackIndex >= 1 && feedbackIndex <= STUDENTS_PER_INSTITUTION && 
                                !teachers[teacherIndex].students[feedbackIndex - 1].name.isEmpty()) {
                                giveFeedback(teachers[teacherIndex].students[feedbackIndex - 1]);
                            } else {
                                System.out.println("Invalid index or empty student.");
                            }
                            break;
                        case 2:
                            addStudent(teachers[teacherIndex]);
                            break;
                        case 3:
                            deleteStudent(teachers[teacherIndex]);
                            break;
                        case 0:
                            System.out.println("Exiting program.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } while (choice != 0);
            } else {
                System.out.println("Teacher not found.");
            }
        } else {
            System.out.println("Invalid user type.");
        }
    }
}
