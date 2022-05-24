package com.stevade;

import java.io.IOException;

public interface TodoService {
    void printAllTasks() throws IOException;
    void addTodoTask(String todoTask) throws Exception;
    void deleteTodoTask(String todoNumber) throws Exception;
    void completeTodoTask(String todoNumber) throws Exception;
    void instructions();
}
