package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private int id;
    private String name;
    private String TZ;
    private String phone;
    @JsonIgnore
    private int companyId;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private LocalDate dateOfBirth;
    private LocalDate startWork;
    private double salary;
    private String image;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_credentials_id")
    private UserCredentials userCredentials;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void addProject(Project project) {
        if (projects == null) {
            projects = new ArrayList<>();
        }
        projects.add(project);
    }
}
