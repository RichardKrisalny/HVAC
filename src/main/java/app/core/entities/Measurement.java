package app.core.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Builder

public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy ="measurement")
    private Duct duct;
    private double radiusA;
    private double radiusB;
    private double radiusC;

    private double lengthA;
    private double lengthB;
    private double lengthC;

    private double widthA;
    private double widthB;
    private double widthC;

    private double height;

    private double deviation;

}
