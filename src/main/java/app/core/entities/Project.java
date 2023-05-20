package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Builder
@NoArgsConstructor
public class Project {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
@JsonIgnore
    private int id;
        @OneToOne
        @JoinColumn(name = "manager_id")
        @JsonIgnore
    private Employee manager;
        private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double priceForMeter;
    @JsonIgnore
    private int companyId;
        @OneToOne
        @JoinColumn(name = "address_id")
    private Address address;
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "employees_projects",joinColumns = @JoinColumn(name = "project_id"),inverseJoinColumns = @JoinColumn(name = "employee_id"))
        @JsonIgnore
    private List<Employee>employees=new ArrayList<>();
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "projectId")
        @JsonIgnore
    private List<Duct>Ducts=new ArrayList<>();
        @ElementCollection
        @JsonIgnore
    private Map<String,String>blueprints=new HashMap<>();
        @OneToOne
        @JoinColumn(name = "client_id")
    private Client client;
    private double totalPrice;

}
