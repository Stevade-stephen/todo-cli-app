package com.stevade;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Todo implements TodoService, FileOperations {
    List<String> allTodoTasksList;

    {
        try {
            allTodoTasksList = readAllTodoTasksFromFileToList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllTasks() {
        System.out.println("List of Todo tasks: ");
        if (allTodoTasksList.isEmpty()) {
            System.out.println("Please add new tasks, your todo list is empty");
            return;
        }
        allTodoTasksList.forEach(System.out::println);
    }

    @Override
    public void addTodoTask(String todoTask) throws Exception {
        int serialNumber = allTodoTasksList.size() == 0 ? 1 : allTodoTasksList.size() + 1;
        writeSingleTaskToFile(serialNumber + ". " + todoTask +"\n");
        System.out.println("Task: " + todoTask + " added successfully");
    }

    @Override
    public void deleteTodoTask(String todoNumber) throws Exception {

        if(Integer.parseInt(todoNumber) > allTodoTasksList.size()) {
            System.out.printf("Enter a valid task number to delete, there are %d tasks available%n", allTodoTasksList.size());
            return;
        }

        allTodoTasksList.removeIf(item -> item.startsWith(todoNumber));

        writeListOfTasksToFile(allTodoTasksList);

        System.out.println("Task deleted successfully");
    }

    @Override
    public void completeTodoTask(String todoNumber) throws Exception {

        if(Integer.parseInt(todoNumber) > allTodoTasksList.size()) {
            System.out.printf("Enter a valid task number to delete, there are %d tasks available%n", allTodoTasksList.size());
            return;
        }

        List<String> completedTask = allTodoTasksList.stream()
                .filter(task -> task.startsWith(todoNumber)).map(task -> task + "*").toList();
        allTodoTasksList.set(Integer.parseInt(todoNumber) - 1, completedTask.get(0));

        writeListOfTasksToFile(allTodoTasksList);
        System.out.println("Task marked as completed successfully");
    }


    public List<String> getAllTodos() throws Exception {
        return readAllTodoTasksFromFileToList();
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

    @Override
    public List<String> readAllTodoTasksFromFileToList() throws Exception {
        return Files.readAllLines(Path.of("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt"));
    }

    @Override
    public void writeSingleTaskToFile(String todoTask) throws Exception {
        File file = new File("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(todoTask);
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public void writeListOfTasksToFile(List<String> tasks) throws Exception {
        Files.write(Path.of("/Users/mac/IdeaProjects/todo-cli-app/src/main/resources/todo.txt"), tasks);
    }
}
