import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> todoing = new ArrayList<>();
    private static final String INDEX_AND_TODO_REGEX = "/(\\d+)(\\s)(.+)/gm";
    private static final String INDEX_REGEX = "^\\d+";

    public static void main(String[] args) {
        //todo для выполнения дз создавать классы не нужно. Просто создавайте
        // необходимые вам методы и переменные. Основная логика программы будет в методе main

        String info = "Available commands:\n" +
                "Add task\n" +
                "Add number\n" +
                "Delete number\n" +
                "Edit number\n" +
                "Print\n" +
                "Exit\n" +
                "Help";
        System.out.println(info + "\n");

        while (true) {
            System.out.println("Enter your command: ");
            String input = new Scanner(System.in).nextLine();

            String command = input;
            String payLoad = "";

            if(input.contains(" ")) {
            String[] lexemes = input.split("\\s+", 2);
            command = lexemes[0];
            payLoad = lexemes[1].trim();
            } else {
                command = input.trim();
            }
            if (command.toLowerCase().equals("add")) {
                if (payLoad.matches(INDEX_AND_TODO_REGEX)) {
                    Integer number = Integer.parseInt(payLoad.replaceAll(INDEX_AND_TODO_REGEX, "$1"));
                    String todoing = payLoad.replaceAll(INDEX_REGEX, " ").trim();
                    add( todoing, number);
                } else {
                    add(payLoad);
                }
            } else if (command.toLowerCase().equals("delete")) {
                Integer number = Integer.parseInt(payLoad);
                delete(number);
            } else if (command.toLowerCase().equals("edit")) {
                Integer number = Integer.parseInt(payLoad.replaceAll(INDEX_AND_TODO_REGEX, "$1"));
                String newTodoing = payLoad.replaceAll(INDEX_REGEX, " ").trim();
                edit(number, newTodoing);
            } else if (command.toLowerCase().equals("print")) {
                print();
            } else if (command.toLowerCase().equals("exit")) {
                System.out.println("Bay-bay");
                return;
            } else {
                System.out.println("The input is incorrect " + info);
            }
        }
    }
    public static void add(String todo) {
        todoing.add(todo);
        System.out.println("Task " + todo + " added successfully");
    }

    public static void add(String todo, int number) {
        if (number >= todoing.size()) {
            todoing.add(todo);
            System.out.println("There is not task at number " + number + ", so the task \""
                    + todo + " has been successfully added to the end of the list");
            return;
        }
            todoing.add(number, todo);
            System.out.println("The task" + todo + "has been successfully added to position " + number);

    }

    public static void delete(Integer number) {
        if (number >= todoing.size()) {
            System.out.println("The task at number" + number + "has not in list");
            return;
        }
        String todo = todoing.get(number);
        todoing.remove(number);
        System.out.println("The task" + todo + "has been successfully deleted from position " + number);
    }

    public static void edit(Integer number, String newTodo) {
        if(number >= todoing.size()) {
            System.out.println("The task number " + number + " is not on the to-do list");
            return;
        }
            String oldTodo = todoing.get(number);
        todoing.set(number, newTodo);
        System.out.println("The task " + oldTodo + " has been changed to " + newTodo);
    }

    public static void print() {
        for (String todo : todoing) {
            System.out.println(todo);
        }
    }
}