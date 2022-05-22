package com.stevade;

import java.io.IOException;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        Todo todo = new Todo();

        if (args.length < 1) {
            System.out.println("Enter at least one argument");
            System.exit(1);
        }

        String operation = args[0];
        StringBuilder task = new StringBuilder();
        Arrays.stream(args).skip(1).forEach(s -> task.append(s).append(" "));

        switch (operation) {
            case "-list" -> todo.printAllTasks();
            case "-help" -> todo.instructions();
            case "-add" -> todo.addTodoTask(task.toString().trim());
            case "-complete" -> todo.completeTodoTask(task.toString().trim());
            case "-delete" -> todo.deleteTodoTask(task.toString().trim());
        }
    }
}
