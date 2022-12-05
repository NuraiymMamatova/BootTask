package peaksoft.examp_project_with_boot.service;

import peaksoft.examp_project_with_boot.entity.Instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    void saveInstructor(Long id, Instructor instructor) throws IOException;

    void deleteInstructor(Long id);

    void updateInstructor(Long id, Instructor instructor) throws IOException;

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors();

    List<Instructor> getAllInstructors(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException;

}
