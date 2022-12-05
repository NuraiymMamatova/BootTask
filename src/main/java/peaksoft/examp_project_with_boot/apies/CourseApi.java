package peaksoft.examp_project_with_boot.apies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.examp_project_with_boot.entity.Course;
//import peaksoft.examp_project_with_boot.entity.Group;
//import peaksoft.examp_project_with_boot.entity.Instructor;
import peaksoft.examp_project_with_boot.entity.Group;
import peaksoft.examp_project_with_boot.service.CourseService;
import peaksoft.examp_project_with_boot.service.GroupService;
import peaksoft.examp_project_with_boot.service.InstructorService;
//import peaksoft.examp_project_with_boot.service.GroupService;
//import peaksoft.examp_project_with_boot.service.InstructorService;

import java.io.IOException;

@Controller
@RequestMapping("/course_api")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    private final GroupService groupService;

    private final InstructorService instructorService;

    @GetMapping("/allOfCourses/{id}")
    private String getAllCourses(@PathVariable Long id, Model model) {
        model.addAttribute("allCourse", courseService.getAllCourses());
        model.addAttribute("companyId", id);
        return "/course/allCourses";
    }

    @GetMapping("/allOfCoursess/{id}")
    private String getAllCoursess(@PathVariable Long id, @ModelAttribute("group") Group group/*, @ModelAttribute("instructor") Instructor instructor*/, Model model) {
        model.addAttribute("myAllCourse", courseService.getAllCourses(id));
        model.addAttribute("companyId", id);
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "/course/allCoursesById";
    }

    @GetMapping("/{id}/new")
    private String newCourse(@PathVariable Long id, Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", id);
        return "/course/saveCourse";
    }

    @PostMapping("/{id}/save")
    private String saveCourse(@ModelAttribute("newCourse") Course course, @PathVariable Long id) {
        courseService.saveCourse(id, course);
        return "redirect:/course_api/allOfCoursess/" + id;
    }

    @GetMapping("/update/{id}")
    private String upCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("updateCourse", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }

    @PostMapping("/{companyId}/{id}/update")
    private String dateCourse(@PathVariable("companyId") Long companyId, @PathVariable("id") Long id, @ModelAttribute("updateCourse") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/course_api/allOfCoursess/" + companyId;
    }

    @GetMapping("/{companyId}/{id}/deleteCourse")
    private String deleteCourse(@PathVariable("companyId") Long companyId, @PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/course_api/allOfCoursess/" + companyId;
    }

    @PostMapping("/{courseId}/assignGroupToCourse")
    private String assignGroupToCourse(@PathVariable Long courseId, @ModelAttribute("group") Group group) throws IOException {
        System.out.println("assign group to course 1");
        groupService.assignGroupToCourse( group.getId(), courseId);
        System.out.println("assign group to course 2");
        return "redirect:/course_api/allOfCoursess/" + courseId;
    }
}