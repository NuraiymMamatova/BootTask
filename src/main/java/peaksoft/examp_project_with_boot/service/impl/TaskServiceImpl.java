package peaksoft.examp_project_with_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Lesson;
import peaksoft.examp_project_with_boot.entity.Task;
import peaksoft.examp_project_with_boot.repository.LessonRepository;
import peaksoft.examp_project_with_boot.repository.TaskRepository;
import peaksoft.examp_project_with_boot.service.TaskService;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void saveTask(Long id, Task task) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.addTasks(task);
        task.setLesson(lesson);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = taskRepository.findById(id).get();
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        taskRepository.save(task1);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasks(Long id) {
        return taskRepository.getAllTasksById(id);
    }
}
