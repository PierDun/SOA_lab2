package app.demo.controller;

import org.springframework.data.domain.Sort;
import app.demo.model.Coordinates;
import app.demo.model.Dragon;
import app.demo.model.DragonCave;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DragonRequestParams {
    public String name;
    public String[] x;
    public String[] y;
    public String[] creationDate;
    public String[] age;
    public String[] population;
    public String[] metersAboveSeaLevel;
    public String[] type;
    public String[] color;
    public String[] depth;
    public String[] sort;
    public int page;
    public int size;

    private static final String NAME_PARAM = "name";
    private static final String X_PARAM = "x";
    private static final String Y_PARAM = "y";
    private static final String AGE_PARAM = "age";
    private static final String CREATION_DATE_PARAM = "creation_date";
    private static final String POPULATION_PARAM = "population";
    private static final String METERS_ABOVE_SEA_LEVEL_PARAM = "meters_above_sea_level";
    private static final String COLOR_PARAM = "color";
    private static final String TYPE_PARAM = "type";
    private static final String DEPTH_PARAM = "depth";
    private static final String SORTING_PARAM = "sort";
    private static final String PAGE_INDEX = "page";
    private static final String PAGE_SIZE_PARAM = "size";


    DragonRequestParams(Map<String, String[]> info) {
        setDragonRequestParams(info.get(NAME_PARAM),
                info.get(X_PARAM),
                info.get(Y_PARAM),
                info.get(AGE_PARAM),
                info.get(CREATION_DATE_PARAM),
                info.get(POPULATION_PARAM),
                info.get(METERS_ABOVE_SEA_LEVEL_PARAM),
                info.get(COLOR_PARAM),
                info.get(TYPE_PARAM),
                info.get(DEPTH_PARAM),
                info.get(SORTING_PARAM),
                info.get(PAGE_INDEX),
                info.get(PAGE_SIZE_PARAM)
        );
    }

    private void setDragonRequestParams(String[] name,
                                        String[] x,
                                        String[] y,
                                        String[] creationDate,
                                        String[] area,
                                        String[] population,
                                        String[] metersAboveSeaLevel,
                                        String[] government,
                                        String[] color,
                                        String[] depth,
                                        String[] sort,
                                        String[] page,
                                        String[] size) {
        this.name = name == null ? null : name[0];
        this.x = x;
        this.y = y;
        this.creationDate = creationDate;
        this.age = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.type = government;
        this.color = color;
        this.depth = depth;
        this.sort = sort;
        this.page = page == null ? 0 : Integer.parseInt(page[0]);
        this.size = size == null ? 5 : Integer.parseInt(size[0]);
    }

    private String like(String val) {
        return "%" + val + "%";
    }

    public List<Predicate> getPredicates(
            CriteriaBuilder cb,
            Root<Dragon> root,
            Join<Dragon, Coordinates> joinCoordinates,
            Join<Dragon, DragonCave> joinCave
    ) throws ParseException {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null)
            predicates.add(cb.like(root.get("name"), like(name)));

        if (x != null)
            if (x.length > 1) {
                if (x[0] != null && !x[0].isEmpty())
                    // больше или равен
                    predicates.add(cb.ge(joinCoordinates.get("x"), Integer.parseInt(x[0])));
                if (x[1] != null && !x[1].isEmpty())
                    // меньше или равен
                    predicates.add(cb.le(joinCoordinates.get("x"), Integer.parseInt(x[1])));
            } else if (x[0] != null && !x[0].isEmpty())
                predicates.add(cb.equal(joinCoordinates.get("x"), Integer.parseInt(x[0])));

        if (y != null)
            if (y.length > 1) {
                if (y[0] != null && !y[0].isEmpty())
                    predicates.add(cb.ge(joinCoordinates.get("y"), Long.parseLong(y[0])));
                if (y[1] != null && !y[1].isEmpty())
                    predicates.add(cb.le(joinCoordinates.get("y"), Long.parseLong(y[1])));
            } else if (y[0] != null && !y[0].isEmpty())
                predicates.add(cb.equal(joinCoordinates.get("y"), Long.parseLong(y[0])));

        if (creationDate != null)
            if (creationDate.length > 1) {
                if (creationDate[0] != null && !creationDate[0].isEmpty())
                    predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), new SimpleDateFormat("dd.MM.yyyy").parse(creationDate[0])));
                if (creationDate[1] != null && !creationDate[1].isEmpty())
                    predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), new SimpleDateFormat("dd.MM.yyyy").parse(creationDate[1])));
            } else if (creationDate[0] != null && !creationDate[0].isEmpty())
                predicates.add(cb.equal(root.get("creationDate"), new SimpleDateFormat("dd.MM.yyyy").parse(creationDate[0])));


        if (age != null)
            if (age.length > 1) {
                if (age[0] != null && !age[0].isEmpty())
                    predicates.add(cb.ge(joinCoordinates.get("area"), Float.parseFloat(age[0])));
                if (age[1] != null && !age[1].isEmpty())
                    predicates.add(cb.le(joinCoordinates.get("area"), Float.parseFloat(age[1])));
            } else if (age[0] != null && !age[0].isEmpty())
                predicates.add(cb.equal(joinCoordinates.get("area"), Float.parseFloat(age[0])));

        if (population != null)
            if (population.length > 1) {
                if (population[0] != null && !population[0].isEmpty())
                    // больше или равен
                    predicates.add(cb.ge(joinCoordinates.get("population"), Integer.parseInt(population[0])));
                if (population[1] != null && !population[1].isEmpty())
                    // меньше или равен
                    predicates.add(cb.le(joinCoordinates.get("population"), Integer.parseInt(population[1])));
            } else if (population[0] != null && !population[0].isEmpty())
                predicates.add(cb.equal(joinCoordinates.get("population"), Integer.parseInt(population[0])));

        if (metersAboveSeaLevel != null)
            if (metersAboveSeaLevel.length > 1) {
                if (metersAboveSeaLevel[0] != null && !metersAboveSeaLevel[0].isEmpty())
                    // больше или равен
                    predicates.add(cb.ge(joinCoordinates.get("metersAboveSeaLevel"), Integer.parseInt(metersAboveSeaLevel[0])));
                if (metersAboveSeaLevel[1] != null && !metersAboveSeaLevel[1].isEmpty())
                    // меньше или равен
                    predicates.add(cb.le(joinCoordinates.get("metersAboveSeaLevel"), Integer.parseInt(metersAboveSeaLevel[1])));
            } else if (metersAboveSeaLevel[0] != null && !metersAboveSeaLevel[0].isEmpty())
                predicates.add(cb.equal(joinCoordinates.get("metersAboveSeaLevel"), Integer.parseInt(metersAboveSeaLevel[0])));

        if (type != null)
            predicates.add(root.get("type").as(String.class).in(type));

        if (color != null)
            predicates.add(root.get("color").as(String.class).in(color));

        if (depth != null)
            if (depth.length > 1) {
                if (depth[0] != null && !depth[0].isEmpty())
                    predicates.add(cb.ge(joinCave.get("height"), Double.parseDouble(depth[0])));
                if (depth[1] != null && !depth[1].isEmpty())
                    predicates.add(cb.le(joinCave.get("height"), Double.parseDouble(depth[1])));
            } else if (depth[0] != null && !depth[0].isEmpty())
                predicates.add(cb.equal(joinCave.get("height"), Double.parseDouble(depth[0])));

        return predicates;
    }

    public Sort parseSorting() throws ParseException {
        String[] args = sort[0].split("_", 2);
        if (args.length != 2)
            throw new ParseException("incorrect sort parameter " + sort[0], 0);
        if (args[0].equals(X_PARAM) || args[0].equals(Y_PARAM)) {
            args[0] = "coordinates." + args[0];
        }
        if (args[0].equals(DEPTH_PARAM)) {
            args[0] = "governor." + args[0];
        }
        String field = args[0];
        Sort currentSorting = Sort.by(field);
        if (args[1].equals("asc"))
            currentSorting = currentSorting.ascending();
        else if (args[1].equals("desc"))
            currentSorting = currentSorting.descending();
        else
            throw new ParseException("incorrect sort parameter " + sort[0], 0);
        return currentSorting;
    }
}