package peaksoft.examp_project_with_boot.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    private Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH,  PERSIST}, fetch = FetchType.EAGER)
    private Course course;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "lesson")
    private List<Task> tasks;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }

    public void addTasks(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
}