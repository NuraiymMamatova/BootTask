package peaksoft.examp_project_with_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l where l.course.id = :id")
    List<Lesson> getLessonsById(Long id);
}