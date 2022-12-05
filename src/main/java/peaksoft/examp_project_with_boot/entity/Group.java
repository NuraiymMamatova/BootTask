package peaksoft.examp_project_with_boot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq",sequenceName = "group_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private Date dateOfStart;

    private String image;

    @ManyToMany(cascade = {MERGE, REFRESH, DETACH, PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "groups_courses",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    public Group(String groupName, Date dateOfStart, String image) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.image = image;
    }

    public void addStudents(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

}
