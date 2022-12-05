package peaksoft.examp_project_with_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.lesson.id = :id")
    List<Task> getAllTasksById(Long id);
}