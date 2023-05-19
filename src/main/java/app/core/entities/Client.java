package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Builder
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @OneToOne
    @JoinColumn(name = "user_credentials_id")
    private UserCredentials userCredentials;
    private String name;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String email;
}
