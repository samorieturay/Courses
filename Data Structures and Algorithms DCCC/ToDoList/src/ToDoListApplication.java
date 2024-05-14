import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Integer;


enum Priority {
    URGENT,
    HIGH,
    NORMAL,
    LOW
}

enum Status {
    NOT_STARTED,
    IN_PROGRESS,
    WAITING,
    DEFERRED
}

class Task implements Comparable<Task> {

    int taskId;
    String subject;
    Priority priority;
    Status status;
    LocalDate startDate;
    LocalDate dueDate;

    public Task(int taskId, String subject, Priority priority, Status status, LocalDate startDate, LocalDate dueDate) {
        this.taskId = taskId;
        this.subject = subject;
        this.priority = priority;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }


    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Id:" + taskId + "; Subject: " + subject + "; Status: " + status + "; Priority: " + priority + "; StartDate: " + startDate.toString() + "; Due date: " + dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task task) {
        return this.getPriority().compareTo(task.getPriority());        }
     }
public class ToDoListApplication {


    PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ToDoListApplication app = new ToDoListApplication();
        app.testPriorityQueue();
    }

         public void displayNextTask() {
             // add your code here
             // This should display the next task and ask the user if they want to show it complete.
             // If yes, the task should be removed using the poll() method.  
         }

        public void showTaskDetail(int taskId)
        {
            Task task = getTaskById(taskId);
            // add your code here to print the task.
        }

        public Task getTaskById(int taskId)  {

            Iterator it = taskPriorityQueue.iterator();
            while (((Iterator) it).hasNext()) {
                Task task = (Task) it.next();
                if (task.getTaskId() == taskId)
                    return task;
                System.out.println("The TaskId was not found");
            }
            return null;
        }

        public void removeTask(int taskId)  {
                // Add your code here
        }

        public void testPriorityQueue() {
        System.out.println("Welcome to My Task List\n");
        do {
            System.out.println("Choose action ( Add (a), Next (n), List (l), Detail (d), Edit (e), Remove (r), Quit (q):");

            String menuItem = scanner.nextLine();

            switch (menuItem) {
                case "a":
                    addTask();
                    break;
                case "n":
                    displayNextTask();
                    break;
                case "l":
                    showTaskSummaryList();
                    break;
                case "d":
                    System.out.println("Enter taskId: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    showTaskDetail(taskId);
                    break;
                case "r":
                    System.out.println("Enter taskId: ");
                    taskId = Integer.parseInt(scanner.nextLine());
                    removeTask(taskId);
                    break;
                case "q":
                    break;
            }
        } while (true);
    }

     void showTaskSummaryList() {
       for (Task task: taskPriorityQueue)
            System.out.println(task);
    }



    private Priority scanForPriority() {
        Priority priority = Priority.NORMAL;
        System.out.println("Enter priority abbreviation Normal = n, Low = l, High = h, Urgent = u): ");
        String abbrev = scanner.nextLine();
        switch (abbrev) {
            case "n":
                priority = Priority.NORMAL;
                break;
            case "l":
                priority = Priority.LOW;
                break;
            case "h":
                priority = Priority.HIGH;
                break;
            case "u":
                priority = Priority.URGENT;
                break;
        }
        return priority;
    }

     void addTask() {
        System.out.println("Adding new Task...");
        System.out.println("Enter subject:");
        Scanner scanner = new Scanner(System.in);
        String subject = scanner.nextLine();

        System.out.println("Enter due date (yyyy-MM-dd):");
        String input = scanner.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(input);
        Priority priority = scanForPriority();

        Status status = Status.NOT_STARTED;
        LocalDate date = LocalDate.now();
        LocalDate startDate = LocalDate.now();
        Task task = new Task(taskPriorityQueue.size() + 1, subject, priority, status, startDate, dueDate);
        taskPriorityQueue.add(task);
    }
}