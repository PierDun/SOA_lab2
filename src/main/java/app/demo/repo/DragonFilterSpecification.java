package app.demo.repo;

import org.springframework.data.jpa.domain.Specification;
import app.demo.controller.DragonRequestParams;
import app.demo.model.Coordinates;
import app.demo.model.Dragon;
import app.demo.model.DragonCave;

import javax.persistence.criteria.*;
import java.text.ParseException;

public class DragonFilterSpecification implements Specification<Dragon> {
    private final DragonRequestParams filterParams;

    public DragonFilterSpecification(DragonRequestParams filterParams) {
        this.filterParams = filterParams;
    }

    @Override
    public Predicate toPredicate(Root<Dragon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Dragon, Coordinates> coordinatesJoin =  root.join("coordinates");
        Join<Dragon, DragonCave> caveJoin =  root.join("cave");
        try {
            return criteriaBuilder.and(filterParams.getPredicates(criteriaBuilder, root, coordinatesJoin, caveJoin).toArray(new Predicate[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}