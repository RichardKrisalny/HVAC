package app.core.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "CircularDuct")
public class CircularDuct extends Duct{

    private double radiosA;
    private double radiosB;
}
