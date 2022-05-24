package com.stevade;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws Exception {
        TodoService todoService = new Todo();

        if (args.length < 1) {
            System.out.println("Enter at least one argument");
            System.exit(1);
        }

        String operation = args[0];
        StringBuilder task = new StringBuilder();
        Arrays.stream(args).skip(1).forEach(s -> task.append(s).append(" "));

        switch (operation.toLowerCase()) {
            case "-list" -> todoService.printAllTasks();
            case "-add" -> todoService.addTodoTask(task.toString().trim());
            case "-complete" -> todoService.completeTodoTask(task.toString().trim());
            case "-delete" -> todoService.deleteTodoTask(task.toString().trim());
            default -> todoService.instructions();
        }
    }
}
