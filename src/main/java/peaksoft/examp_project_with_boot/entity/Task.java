package peaksoft.examp_project_with_boot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.sql.Date;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_text")
    private String taskText;

    private Date deadline;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST}, fetch = FetchType.EAGER)
    private Lesson lesson;

    public Task(String taskName, String taskText, Date deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}