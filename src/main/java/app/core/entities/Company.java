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
@EqualsAndHashCode(of="id")
@Entity
public class Company {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JsonIgnore
    private int id;
    @OneToOne
    @JoinColumn(name = "userCredentials_id")
    private UserCredentials userCredentials;
    private String companyName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String email;
    private String HP;
    private LocalDate foundation;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    @JsonIgnore
    private List<Project>projects = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    @JsonIgnore
    private List<Employee>employees = new ArrayList<>();
    private String logo;

    public void addProject(Project project){
        if (projects ==null){
            projects=new ArrayList<>();
        }
            project.setCompanyId(this.id);
        projects.add(project);
    }
    public void addEmployee(Employee employee){
        if (employee ==null){
            employees=new ArrayList<>();
        }
        employee.setCompanyId(this.id);
        employees.add(employee);
    }
}
