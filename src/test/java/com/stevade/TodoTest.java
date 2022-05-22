package com.stevade;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

class TodoTest {
    Todo underTest = new Todo();
    List<String> allTodos;
    StringBuilder tasksInFile;

    @BeforeEach
    void setUp() throws IOException {
        tasksInFile = new StringBuilder();
        allTodos = underTest.getAllTodos();
        allTodos.forEach(task -> tasksInFile.append("\n").append(task));
    }

    @Test
    void canPrintAllTasksSuccessfullyWhenTasksArePresent() throws Exception {
        String result = "List of Todo tasks:\s"+ tasksInFile + "\n";
        String text = tapSystemOut(() -> underTest.printAllTasks());
        assertThat(text).isEqualTo(result);
    }


    @Test
    void addTodoTask() throws Exception {
        String todoTask = "Read Deployment to Heroku";
        String result = "Task: " + todoTask + " added successfully\n";
        String text = tapSystemOut(() -> underTest.addTodoTask(todoTask));

        assertThat(text).isEqualTo(result);
        assertThat(allTodos.size() + 1).isEqualTo(underTest.getAllTodos().size());
    }


    @Test
    void completeTodoTask() throws Exception {
        String result = "Task marked as completed successfully\n";
        String text = tapSystemOut(() -> underTest.completeTodoTask("2"));
        assertThat(text).isEqualTo(result);
    }

    @Test
    void deleteTodoTask() throws Exception {
        String result = "Task deleted successfully\n";
        String text = tapSystemOut(() -> underTest.deleteTodoTask("2"));
        assertThat(text).isEqualTo(result);
        assertThat(allTodos.size() - 1).isEqualTo(underTest.getAllTodos().size());
    }
}