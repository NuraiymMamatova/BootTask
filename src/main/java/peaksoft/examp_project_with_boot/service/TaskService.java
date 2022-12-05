package peaksoft.examp_project_with_boot.service;

import peaksoft.examp_project_with_boot.entity.Task;

import java.util.List;

public interface TaskService {
void saveTask(Long id, Task task);

    void deleteTask(Long id);

    void updateTask(Long id, Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    List<Task> getAllTasks(Long id);
}