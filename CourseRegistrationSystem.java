import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private int capacity;
    private int enrolledStudents;

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        if (!isFull()) {
            enrolledStudents++;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nCapacity: " + capacity +
                "\nEnrolled Students: " + enrolledStudents;
    }
}

class Student {
    private int studentId;
    private String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("------------------------------");
        }
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public void displayStudentCourses(int studentId) {
        Student studentToDisplay = findStudentById(studentId);
        if (studentToDisplay != null) {
            System.out.println("Courses registered by " + studentToDisplay.getName() + ":");
            for (Course course : courses) {
                if (course.getEnrolledStudents() > 0) {
                    System.out.println(course.getTitle());
                }
            }
            System.out.println("------------------------------");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Add sample courses to the course registration system
        registrationSystem.addCourse(new Course("CSCI101", "Introduction to Computer Science", 30));
        registrationSystem.addCourse(new Course("MATH201", "Calculus I", 25));
        registrationSystem.addCourse(new Course("ENG101", "English Composition", 20));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.println("5. Display Registered Courses for a Student");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    registrationSystem.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter your student ID: ");
    int studentIdToRegister = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    Student studentToRegister = registrationSystem.findStudentById(studentIdToRegister);
    if (studentToRegister == null) {
        studentToRegister = new Student(studentIdToRegister, "Student" + studentIdToRegister);
        registrationSystem.registerStudent(studentToRegister);
    }

    System.out.print("Enter the course code you want to register for: ");
    String courseCodeToRegister = scanner.nextLine();

    Course courseToRegister = registrationSystem.findCourseByCode(courseCodeToRegister);
    if (courseToRegister != null) {
        if (!courseToRegister.isFull()) {
            courseToRegister.enrollStudent();
            System.out.println("Course registration successful.");
        } else {
            System.out.println("Course is full. Registration failed.");
        }
    } else {
        System.out.println("Course not found.");
    }
                    break;
                case 3:
                   System.out.print("Enter your student ID: ");
                    int studentIdToDrop = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Student studentToDrop = registrationSystem.findStudentById(studentIdToDrop);
                    if (studentToDrop != null) {
                        System.out.print("Enter the course code you want to drop: ");
                        String courseCodeToDrop = scanner.nextLine();

                        Course courseToDrop = registrationSystem.findCourseByCode(courseCodeToDrop);
                        if (courseToDrop != null) {
                            courseToDrop.dropStudent();
                            System.out.println("Course dropped successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                case 5:
                    System.out.print("Enter your student ID: ");
                    int studentIdToDisplay = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    registrationSystem.displayStudentCourses(studentIdToDisplay);
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}

