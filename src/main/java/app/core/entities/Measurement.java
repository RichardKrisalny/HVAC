package app.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ductId;

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
