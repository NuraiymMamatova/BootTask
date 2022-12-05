package peaksoft.examp_project_with_boot.service;

import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.repository.CourseRepository;

import java.util.List;

public interface CourseService {

    void saveCourse(Long id, Course course);

    void deleteCourse(Long id);

    void updateCourse(Long id, Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourses();

    List<Course> getAllCourses(Long id);
}
