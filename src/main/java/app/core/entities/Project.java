package app.core.entities;

import app.core.entities.Contact;
import app.core.entities.Duct;
import app.core.entities.Worker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@ToString(exclude = "workers , ducts")

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String name;
    private String address;
    @OneToOne
    private Contact contact;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workers_projects",joinColumns = @JoinColumn(name = "projectID"),inverseJoinColumns = @JoinColumn(name = "WorkerID"))
    private List<Worker>workers;
    @OneToMany()
    @JoinColumn(name = "projectId")
    private List<Duct>ducts;
    private LocalDate startDate;
    private LocalDate deadLine;
    private int DegreeOfDifficulty;
    private boolean done;
}
