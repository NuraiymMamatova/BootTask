package peaksoft.examp_project_with_boot.apies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.examp_project_with_boot.entity.Group;
//import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.entity.Student;
import peaksoft.examp_project_with_boot.service.GroupService;
import peaksoft.examp_project_with_boot.service.StudentService;
//import peaksoft.examp_project_with_boot.service.StudentService;

import java.io.IOException;

@Controller
@RequestMapping("/group_api")
@RequiredArgsConstructor
public class GroupApi {

    private final GroupService groupService;
    private final StudentService studentService;

    @GetMapping("/allOfGroups/{id}")
    private String getAllGroups(@PathVariable Long id, Model model) {
        model.addAttribute("allGroup",  groupService.getAllGroups());
        model.addAttribute("courseId", id);
        return "/group/allGroups";
    }

    @GetMapping("/allOfGroupss/{id}")
    private String getAllGroupss(@PathVariable Long id,
                                 @ModelAttribute("student") Student student,
                                 Model model) {
        model.addAttribute("myAllGroup",  groupService.getAllGroups(id));
        model.addAttribute("courseId", id);
        model.addAttribute("students", studentService.getAllStudents());
        return "/group/allGroupsById";
    }

    @GetMapping("/{id}/new")
    private String newGroup(@PathVariable Long id,  Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", id);
        return "/group/saveGroup";
    }

    @PostMapping("/{id}/save")
    private String saveGroup(@ModelAttribute("newGroup") Group group, @PathVariable Long id) {
        groupService.saveGroup(id, group);
        return "redirect:/group_api/allOfGroupss/" + id;
    }

    @GetMapping("/update/{courseId}/{id}")
    private String upGroup(@PathVariable("id")Long id, @PathVariable("courseId")Long courseId, Model model) {
        model.addAttribute("updateGroup", groupService.getGroupById(id));
        model.addAttribute("courseId", courseId);
        return "/group/updateGroup";
    }

    @PostMapping("/{courseId}/{id}/update")
    private String dateGroup(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id, @ModelAttribute("updateGroup") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/group_api/allOfGroupss/" + courseId;
    }

    @RequestMapping("/{courseId}/{id}/delete")
    private String deleteGroup(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
        groupService.deleteGroup(id);
        return "redirect:/group_api/allOfGroupss/" + courseId;
    }

}
