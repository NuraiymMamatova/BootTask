package peaksoft.examp_project_with_boot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq",sequenceName = "course_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    private Integer duration;

    private String description;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST}, fetch = LAZY)
    private Company company;

    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    @ManyToMany(cascade = ALL, fetch = LAZY, mappedBy = "courses")
    private List<Group> groups;

    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    public Course(String courseName, int duration, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.description = description;
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }instructors.add(instructor);
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }
}
