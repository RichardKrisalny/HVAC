package app.core.entities;

import lombok.*;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@ToString(exclude = "projects")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @OneToMany()
    @JoinColumn(name = "userId")
    private List<Project>projects;
    public void addProject(Project project){
        if(projects==null){
            projects=new ArrayList<>();
        }
        project.setUserId(this.id);
        projects.add(project);
    }
}
