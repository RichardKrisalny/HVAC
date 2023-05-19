package app.core.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "Duct")
@Inheritance(strategy = InheritanceType.JOINED)
public  class Duct {
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
@JsonIgnore
private int id;
    @JsonIgnore
private int projectId;
private double steelThick;
private String color;
private double price;
private String image;
@Enumerated(EnumType.STRING)
private SteelType steelType;
@Enumerated(EnumType.STRING)
private Shape shape;
@Enumerated(EnumType.STRING)
private Bidud bidud;
@Enumerated(EnumType.STRING)
private ConnectionType connectionType;
private boolean isDone;
private double turningRadiosA;
private double turningRadiosB;
private double turningRadiosForT;
private double height;
private double turningAngle;
}

