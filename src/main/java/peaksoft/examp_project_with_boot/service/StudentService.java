package peaksoft.examp_project_with_boot.service;


import peaksoft.examp_project_with_boot.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    void saveStudent(Long id, Student student) throws IOException;

    void deleteStudent(Long id);

    void updateStudent(Long id, Student student) throws IOException;

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    List<Student> getAllStudents(Long id);

    void assignStudentToGroup(Long studentId, Long groupId) throws IOException;

}