package peaksoft.examp_project_with_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Group;
import peaksoft.examp_project_with_boot.entity.Instructor;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.repository.CourseRepository;
import peaksoft.examp_project_with_boot.repository.GroupRepository;
import peaksoft.examp_project_with_boot.service.GroupService;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    @Override
    public void saveGroup(Long id, Group group) {
        Course course = courseRepository.findById(id).get();
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = groupRepository.getById(id);
        List<Student> students = group.getStudents();
        Long count = students.stream().count();
        for (Course course : group.getCourses()) {
            Long count1 = course.getCompany().getCount();
            count1 -= count;
            course.getCompany().setCount(count1);
            for (Instructor instructor : course.getInstructors()) {
                Long count2 = instructor.getCount();
                count2 -= count;
                instructor.setCount(count2);
            }
        }
        groupRepository.delete(group);

    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        groupRepository.save(group1);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return courseRepository.findById(courseId).get().getGroups();
    }

    @Override
    public void assignGroupToCourse(Long groupId, Long courseId) throws IOException {
        Group group = groupRepository.findById( groupId).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getGroups() != null) {
            for (Group group1 : course.getGroups()) {
                if (group1.getId() == groupId) {
                    throw  new IOException("This group already exists!");
                }
            }
        }
        course.addGroup(group);
        group.addCourse(course);
        courseRepository.save(course);
    }
}
