package peaksoft.examp_project_with_boot.service;


import peaksoft.examp_project_with_boot.entity.Lesson;

import java.util.List;

public interface LessonService {

    void saveLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);

    void updateLesson(Long id, Lesson lesson);

    Lesson getLessonById(Long id);

    List<Lesson> getAllLessons();

    List<Lesson> getAllLessons(Long id);
}