package peaksoft.examp_project_with_boot.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Group;
import peaksoft.examp_project_with_boot.entity.Instructor;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.repository.GroupRepository;
import peaksoft.examp_project_with_boot.repository.StudentRepository;
import peaksoft.examp_project_with_boot.service.StudentService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    @Override
    public void saveStudent(Long id, Student student) throws IOException {
        Group group = groupRepository.findById(id).get();
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        group.addStudents(student);
        for (Course course : group.getCourses()) {
            course.getCompany().plus();

        }
        for (Course course : group.getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.plus();
            }
        }

        student.setGroup(group);
        groupRepository.save(group);

    }

    @Override
    public void deleteStudent(Long id) {
        Student studentById = getStudentById(id);
        for (Course course : studentById.getGroup().getCourses()) {
            course.getCompany().minus();

        }
        for (Course course : studentById.getGroup().getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.minus();
            }
        }
        studentRepository.delete(studentById);
    }

    @Override
    public void updateStudent(Long id, Student student) throws IOException {
        Student student1 = studentRepository.findById(id).get();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        studentRepository.save(student1);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentRepository.getAllStudentsById(id);
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) throws IOException {
        Student student = studentRepository.findById(studentId).get();
        Group group = groupRepository.findById(groupId).get();
        if (group.getStudents() != null) {
            for (Student student1 : group.getStudents()) {
                if (student1.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        group.addStudents(student);
        student.setGroup(group);
        groupRepository.save(group);

    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени инструктора нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию инструктора нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии инструктора должно быть как минимум 3 буквы");
        }

        if (phoneNumber.length() == 13
                && phoneNumber.charAt(0) == '+'
                && phoneNumber.charAt(1) == '9'
                && phoneNumber.charAt(2) == '9'
                && phoneNumber.charAt(3) == '6') {
            int counter = 0;

            for (Character i : phoneNumber.toCharArray()) {
                if (counter != 0) {
                    if (!Character.isDigit(i)) {
                        throw new IOException("Формат номера не правильный");
                    }
                }
                counter++;
            }
        } else {
            throw new IOException("Формат номера не правильный");
        }
    }
}