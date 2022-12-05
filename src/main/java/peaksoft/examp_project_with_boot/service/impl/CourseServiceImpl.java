package peaksoft.examp_project_with_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.examp_project_with_boot.entity.Company;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Group;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.repository.CompanyRepository;
import peaksoft.examp_project_with_boot.repository.CourseRepository;
import peaksoft.examp_project_with_boot.service.CourseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void saveCourse(Long id, Course course) {
        Company company = companyRepository.findById(id).get();
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        companyRepository.save(company);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        Long count = 0L;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
            }
        }
        Long count1 = course.getCompany().getCount();
        count1 -= count;
        course.getCompany().setCount(count1);
        courseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).get();
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        courseRepository.save(course1);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        return courseRepository.getAllCoursesById(id);
    }
}
