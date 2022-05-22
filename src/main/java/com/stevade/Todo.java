package com.stevade;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Todo {

    public void printAllTasks() throws IOException {
        System.out.println("List of Todo tasks: ");
        List<String> allTodos = allTodos();

        if(allTodos.isEmpty()){
            System.out.println("Please add new tasks, your todo list is empty");
        }

        else allTodos.forEach(System.out::println);
    }

    public void addTodoTask(String todoTask) throws IOException {
        List<String> allTodos = allTodos();

        int serialNumber = allTodos.size() == 0 ? 1 : allTodos.size() + 1;

        File file = new File("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(serialNumber + ". " + todoTask +"\n");
        fileWriter.flush();
        fileWriter.close();

        System.out.println("Task: " + todoTask + " added successfully");
    }

    public void deleteTodoTask(String todoNumber) throws IOException {
        List<String> allTodos = allTodos();

        if(Integer.parseInt(todoNumber) > allTodos.size())
            System.out.printf("Enter a valid task number to delete, there are %d tasks available%n", allTodos.size());

        for (int i = 0; i < allTodos.size(); i++) {
            if(allTodos.get(i).startsWith(todoNumber)) {
                allTodos.remove(i);
                break;
            }
        }


        Files.write(Path.of("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt"), allTodos);
        System.out.println("Task deleted successfully");
    }

    public void completeTodoTask(String todoNumber) throws IOException {
        List<String> allTodos = allTodos();

        if(Integer.parseInt(todoNumber) > allTodos.size())
            System.out.printf("Enter a valid task number to delete, there are %d tasks available%n", allTodos.size());

        for (int i = 0; i < allTodos.size(); i++) {
            if(allTodos.get(i).startsWith(todoNumber)) {
                String completedTask = allTodos.get(i);
                allTodos.set(i, completedTask + "*");
                break;
            }
        }

        Files.write(Path.of("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt"), allTodos);
        System.out.println("Task marked as completed successfully");
    }

    private List<String> allTodos() throws IOException {
        return Files.readAllLines(Path.of("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt"));
    }

    public List<String> getAllTodos() throws IOException {
        return allTodos();
    }

    public void instructions() {
        String instructions = """
            Welcome to your Todo Application
            1. To add a todo task, enter '-add' followed by a space and the task
            2. To view all tasks, enter '-list'
            3. To delete a task, enter "-delete" and the number of the task you want deleted
            4. To complete a task, enter "-complete" and the number of the task you want deleted
            ------------------------------------------------------------------------------------
            """;
        System.out.println(instructions);
    }
}
