package app.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity(name = "dragon_cave")
@Table
public class DragonCave {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "depth")
    private Double depth;

    public DragonCave() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DragonCave cave = (DragonCave) o;
        return id == cave.id && Objects.equals(depth, cave.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, depth);
    }
}