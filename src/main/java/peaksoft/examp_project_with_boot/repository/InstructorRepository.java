package peaksoft.examp_project_with_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.examp_project_with_boot.entity.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query("select i from Instructor i where i.course.id = :id")
    List<Instructor> getAllInstructorById(Long id);
}