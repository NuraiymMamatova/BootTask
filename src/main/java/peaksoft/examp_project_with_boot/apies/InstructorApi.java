package peaksoft.examp_project_with_boot.apies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.examp_project_with_boot.entity.Course;
import peaksoft.examp_project_with_boot.entity.Instructor;
import peaksoft.examp_project_with_boot.service.CourseService;
import peaksoft.examp_project_with_boot.service.InstructorService;

import java.io.IOException;

@Controller
@RequestMapping("/instructor_api")
@RequiredArgsConstructor
public class InstructorApi {

    private final InstructorService instructorService;

    private final CourseService courseService;

    @GetMapping("/allOfInstructors/{id}")
    private String getAllInstructors(@PathVariable Long id, @ModelAttribute("course") Course course, Model model) {
        model.addAttribute("allInstructor", instructorService.getAllInstructors());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("courseId", id);
        return "/instructor/allInstructors";
    }

    @GetMapping("/allOfInstructorss/{id}")
    private String getAllInstructorss(@PathVariable Long id, Model model) {
        model.addAttribute("myAllInstructor", instructorService.getAllInstructors(id));
        model.addAttribute("courseId", id);
        return "/instructor/allInstructorsById";
    }

    @GetMapping("/{id}/new")
    private String newInstructor(@PathVariable Long id,  Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/saveInstructor";
    }

    @PostMapping("/{id}/save")
    private String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor, @PathVariable Long id) throws IOException {
        instructorService.saveInstructor(id, instructor);
        return "redirect:/instructor_api/allOfInstructorss/" + id;
    }

    @GetMapping("/update/{id}")
    private String upInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor =  instructorService.getInstructorById(id);
        model.addAttribute("updateInstructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "/instructor/updateInstructor";
    }

    @PostMapping("/{courseId}/{id}/update")
    private String dateInstructor(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id, @ModelAttribute("updateInstructor") Instructor instructor) throws IOException {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructor_api/allOfInstructorss/" + courseId;
    }

    @RequestMapping("/{courseId}/{id}/delete")
    private String deleteInstructor(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructor_api/allOfInstructorss/" + courseId;
    }

    @PostMapping("/{instructorId}/assignInstructorToCourse")
    private String assignInstructorToCourse(@PathVariable("instructorId") Long instructorId, @ModelAttribute("course") Course course) throws IOException {
        instructorService.assignInstructorToCourse(instructorId, course.getId());
        return "redirect:/course_api/allOfCoursess/" + course.getId();
    }

}
