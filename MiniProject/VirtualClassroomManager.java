package MiniProject;
import java.util.*;

class VirtualClassroomManager {
    private Map<String, Classroom> classrooms = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        manager.start();
    }

    private void start() {
        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            String[] commands = input.split(" ", 3);

            try {
                switch (commands[0].toLowerCase()) {
                    case "add_classroom":
                        if (commands.length < 2) {
                            throw new IllegalArgumentException("Class name is required.");
                        }
                        addClassroom(commands[1]);
                        break;
                    case "add_student":
                        if (commands.length < 3) {
                            throw new IllegalArgumentException("Student ID and class name are required.");
                        }
                        String[] studentArgs = commands[1].split(" ", 2);
                        addStudent(studentArgs[0], studentArgs[1]);
                        break;
                    case "schedule_assignment":
                        if (commands.length < 3) {
                            throw new IllegalArgumentException("Class name and assignment details are required.");
                        }
                        String[] assignmentArgs = commands[1].split(" ", 2);
                        scheduleAssignment(assignmentArgs[0], assignmentArgs[1]);
                        break;
                    case "submit_assignment":
                        if (commands.length < 3) {
                            throw new IllegalArgumentException("Student ID, class name, and assignment details are required.");
                        }
                        String[] submissionArgs = commands[1].split(" ", 2);
                        submitAssignment(submissionArgs[0], submissionArgs[1], commands[2]);
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void addClassroom(String className) {
        if (classrooms.containsKey(className)) {
            System.out.println("Classroom " + className + " already exists.");
            return;
        }
        classrooms.put(className, new Classroom(className));
        System.out.println("Classroom " + className + " has been created.");
    }

    private void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
            return;
        }
        classroom.addStudent(studentId);
        System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
    }

    private void scheduleAssignment(String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
            return;
        }
        classroom.scheduleAssignment(assignmentDetails);
        System.out.println("Assignment for " + className + " has been scheduled.");
    }

    private void submitAssignment(String studentId, String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            System.out.println("Classroom " + className + " does not exist.");
            return;
        }
        classroom.submitAssignment(studentId, assignmentDetails);
        System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
    }
}

class Classroom {
    private Set<String> students = new HashSet<>();
    private List<String> assignments = new ArrayList<>();
    private Map<String, List<String>> studentAssignments = new HashMap<>();

    public Classroom(String name) {
    }

    public void addStudent(String studentId) {
        students.add(studentId);
        studentAssignments.putIfAbsent(studentId, new ArrayList<>());
    }

    public void scheduleAssignment(String assignmentDetails) {
        assignments.add(assignmentDetails);
    }

    public void submitAssignment(String studentId, String assignmentDetails) {
        if (!students.contains(studentId)) {
            throw new IllegalArgumentException("Student not enrolled in the class.");
        }
        studentAssignments.get(studentId).add(assignmentDetails);
    }
}
