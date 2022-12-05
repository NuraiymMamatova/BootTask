package peaksoft.examp_project_with_boot.apies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.examp_project_with_boot.entity.Group;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.enums.StudyFormat;
import peaksoft.examp_project_with_boot.service.GroupService;
import peaksoft.examp_project_with_boot.service.StudentService;

import java.io.IOException;

@Controller
@RequestMapping("/student_api")
public class StudentApi {

    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public StudentApi(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/allOfStudents/{id}")
    private String getAllStudents(@PathVariable Long id, @ModelAttribute("group") Group group, Model model) {
        model.addAttribute("allStudent", studentService.getAllStudents());
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("groupId", id);
        return "/student/allStudents";
    }

    @GetMapping("/allOfStudentss/{id}")
    private String getAllStudentss(@PathVariable Long id, Model model) {
        System.out.println("all studentss 1");
        model.addAttribute("myAllStudent", studentService.getAllStudents(id));
        System.out.println("all studentss 2");
        model.addAttribute("groupId", id);
        System.out.println("all studentss 3");
        return "/student/allStudentsById";
    }

    @GetMapping("/{id}/new")
    private String newStudent(@PathVariable Long id, Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("StudyFormatOnline", StudyFormat.ONLINE);
        model.addAttribute("StudyFormatOffline", StudyFormat.OFFLINE);
        model.addAttribute("groupId", id);
        return "/student/saveStudent";
    }

    @PostMapping("/{id}/save")
    private String saveStudent(@ModelAttribute("newStudent") Student student, @PathVariable Long id) throws IOException {
        studentService.saveStudent(id, student);
        return "redirect:/student_api/allOfStudentss/" + id;
    }

    @GetMapping("/update/{id}")
    private String upStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("updateStudent", student);
        model.addAttribute("StudyFormatOnline", StudyFormat.ONLINE);
        model.addAttribute("StudyFormatOffline", StudyFormat.OFFLINE);
        model.addAttribute("groupId", student.getGroup().getId());
        return "/student/updateStudent";
    }

    @PostMapping("/{groupId}/{id}/update")
    private String dateStudent(@PathVariable("groupId") Long groupId, @PathVariable("id") Long id, @ModelAttribute("updateStudent") Student student) throws IOException {
        studentService.updateStudent(id, student);
        return "redirect:/student_api/allOfStudentss/" + groupId;
    }

    @RequestMapping("/{groupId}/{id}/delete")
    private String deleteStudent(@PathVariable("groupId") Long groupId, @PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student_api/allOfStudentss/" + groupId;
    }

    @PostMapping("/{studentId}/assignStudentToGroup")
    private String assignStudentToGroup(@PathVariable("studentId") Long studentId, @ModelAttribute("group") Group group) throws IOException {
        studentService.assignStudentToGroup(studentId, group.getId());
        return "redirect:/student_api/allOfStudentss/" + group.getId();
    }
}