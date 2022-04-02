package app.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import app.demo.model.enums.Color;
import app.demo.model.enums.DragonType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity(name = "dragon")
@Table
public class Dragon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "age")
    private Long age;

    @Column(name = "color")
    @Enumerated(EnumType.ORDINAL)
    private Color color;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DragonType type;

    @Column(name = "birth", nullable = false)
    private ZonedDateTime creationDate = ZonedDateTime.now();

    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate", nullable = false)
    private Coordinates coordinates;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cave", nullable = false)
    private DragonCave cave;

    public Dragon() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dragon dragons = (Dragon) o;
        return id == dragons.id && Objects.equals(name, dragons.name) && Objects.equals(age, dragons.age) && Objects.equals(color, dragons.color) && Objects.equals(type, dragons.type) && Objects.equals(creationDate, dragons.creationDate) && Objects.equals(description, dragons.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, color, type, creationDate, description);
    }
}