package peaksoft.examp_project_with_boot.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Lesson;
import peaksoft.examp_project_with_boot.repository.CourseRepository;
import peaksoft.examp_project_with_boot.repository.LessonRepository;
import peaksoft.examp_project_with_boot.service.LessonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    @Override
    public void saveLesson(Long id, Lesson lesson) {
        Course course = courseRepository.findById(id).get();
        course.addLesson(lesson);
        lesson.setCourse(course);
        courseRepository.save(course);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).get();
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return lessonRepository.getLessonsById(id);
    }
}