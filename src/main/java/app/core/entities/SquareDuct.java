package app.core.entities;
import lombok.*;
import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "SquareDuct")
public class SquareDuct extends Duct{

    private double lengthA;
    private double widthA;
    private double lengthB;
    private double widthB;
    private double lengthC;
    private double widthC;
    private double widthForT;
    private double heightForLamed;
}
