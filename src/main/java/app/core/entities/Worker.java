package app.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Builder
@ToString(exclude = "projects")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "workers_projects",joinColumns = @JoinColumn(name = "WorkerID"),inverseJoinColumns = @JoinColumn(name = "ProjectID"))
    private List<Project>projects;
    public void addProject(Project project){
        if(projects == null){
            projects=new ArrayList<>();
        }
        projects.add(project);
    }
}
