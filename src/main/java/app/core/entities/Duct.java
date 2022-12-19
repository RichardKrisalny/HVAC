package app.core.entities;

import app.core.entities.Enums.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@ToString(exclude = "measurement")
public class Duct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Connect connect;
    @Enumerated(EnumType.STRING)
    private IronType ironType;
    @Enumerated(EnumType.STRING)
    private Isolation isolation;
    @Enumerated(EnumType.STRING)
    private Shape shape;
    private double thick;
    private double price;
    private boolean color;
    @OneToOne
    @JoinColumn
    private  Measurement measurement;
    private int projectId;

}
