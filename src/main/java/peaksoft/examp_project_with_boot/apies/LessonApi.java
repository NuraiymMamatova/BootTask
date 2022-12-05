package peaksoft.examp_project_with_boot.apies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.examp_project_with_boot.entity.Lesson;
import peaksoft.examp_project_with_boot.service.LessonService;

@Controller
@RequestMapping("/lesson_api")
public class LessonApi {

    private final LessonService lessonService;

    @Autowired
    public LessonApi(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/allOfLessons/{id}")
    private String getAllLessons(@PathVariable Long id, Model model) {
        model.addAttribute("allLesson", lessonService.getAllLessons());
        model.addAttribute("courseId", id);
        return "/lesson/allLessons";
    }

    @GetMapping("/allOfLessonss/{id}")
    private String getAllLessonss(@PathVariable Long id, Model model) {
        model.addAttribute("myAllLesson", lessonService.getAllLessons(id));
        model.addAttribute("courseId", id);
        return "/lesson/allLessonsById";
    }

    @GetMapping("/{id}/new")
    private String newLesson(@PathVariable Long id, Model model) {
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("courseId", id);
        return "/lesson/saveLesson";
    }

    @PostMapping("/{id}/save")
    private String saveLesson(@ModelAttribute("newLesson") Lesson lesson, @PathVariable Long id) {
        lessonService.saveLesson(id, lesson);
        return "redirect:/lesson_api/allOfLessonss/" + id;
    }

    @GetMapping("/update/{id}")
    private String upLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("updateLesson", lesson);
        model.addAttribute("courseId", lesson.getCourse().getId());
        return "/lesson/updateLesson";
    }

    @PostMapping("/{courseId}/{id}/update")
    private String dateLesson(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id, @ModelAttribute("updateLesson") Lesson lesson) {
        lessonService.updateLesson(id, lesson);
        return "redirect:/lesson_api/allOfLessonss/" + courseId;
    }

    @RequestMapping("/{courseId}/{id}/delete")
    private String deleteLesson(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lesson_api/allOfLessonss/" + courseId;
    }
}