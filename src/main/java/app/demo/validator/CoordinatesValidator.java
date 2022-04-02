package app.demo.validator;

import app.demo.model.Coordinates;
import app.demo.model.dto.CoordinatesFromClient;
import app.demo.model.Error;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CoordinatesValidator {
    public Coordinates validate(CoordinatesFromClient coordinates) throws IllegalAccessException, ValidateFieldsException {
        List<Error> errorList = new ArrayList<>();
        Coordinates validatedCoordinates = new Coordinates();

            if (coordinates == null) throw new ValidateFieldsException(errorList);

            for (Field f : CoordinatesFromClient.class.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(coordinates) == null)
                    errorList.add(new Error(700, f.getName(), String.format("Coordinates %s is not specified", f.getName())));
            }

            try {
                int x;
                if (coordinates.getX() != null) {
                    x = Integer.parseInt(coordinates.getX());
                    validatedCoordinates.setX(x);
                }
            } catch (NumberFormatException e) {
                errorList.add(new Error(702, "x", "The entered value is not an integer value"));
            }

            try {
                int y = 0;
                if (coordinates.getY() != null)
                    y = Integer.parseInt(coordinates.getY());

                if (y > 449)
                    errorList.add(new Error(701, "y", "Coordinates y should be less or equal than 449"));
                else validatedCoordinates.setY(y);

            } catch (NumberFormatException e) {
                errorList.add(new Error(702, "y", "The entered value is not an integer value"));
            }

            if (errorList.size() > 0) throw new ValidateFieldsException(errorList);

            if (coordinates.getId() != 0) validatedCoordinates.setId(coordinates.getId());

            return validatedCoordinates;
    }
}