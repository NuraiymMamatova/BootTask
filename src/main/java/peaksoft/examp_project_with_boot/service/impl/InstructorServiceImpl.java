package peaksoft.examp_project_with_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Instructor;
import peaksoft.examp_project_with_boot.repository.CourseRepository;
import peaksoft.examp_project_with_boot.repository.InstructorRepository;
import peaksoft.examp_project_with_boot.service.InstructorService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final  InstructorRepository instructorRepository;

    private final CourseRepository courseRepository;

    @Override
    public void saveInstructor(Long id, Instructor instructor) throws IOException {
        Course course = courseRepository.findById(id).get();
        for (Instructor i : course.getInstructors()) {
            try {
                if (i.getEmail().equals(instructor.getEmail()))
                    throw new IOException();
            } catch (IOException e) {
                throw new IOException("This email already exists!");
            }
        }
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName()
                .replace(" ", ""), instructor.getFirstName()
                .replace(" ", ""));
        instructorRepository.save(instructor);

    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) throws IOException {
        Instructor instructor1 = instructorRepository.findById(id).get();
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName()
                .replace(" ", ""), instructor.getFirstName()
                .replace(" ", ""));
        instructorRepository.save(instructor1);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).get();
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return instructorRepository.getAllInstructorById(id);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getInstructors() != null) {
            for (Instructor instructor1 : course.getInstructors()) {
                if (instructor1.getId() == instructorId) {
                    throw  new IOException("This instructor already exists!");
                }
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        courseRepository.save(course);
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
