package com.stevade;

import java.util.List;

public interface FileOperations {
    List<String> readAllTodoTasksFromFileToList() throws Exception;
    void writeSingleTaskToFile(String task) throws Exception;
    void writeListOfTasksToFile(List<String> tasks) throws Exception;
}
