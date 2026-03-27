package util;

import models.*;

public class Validator{
    public void validateDragon(Dragon dr){
        if (!(dr.getId() != null && dr.getId() > 0 && dr.getName() != null && dr.getCoordinates() != null && dr.getCreationDate() != null && dr.getAge() > 0 && dr.getHead() != null)){
            throw new ValidationException("Value in class Dragon incorrect");
        }
    }

    public void validateCoordinates(Coordinates coor){
        if (!(coor.getY() != null && coor.getY() > -524)){
            throw new ValidationException("Invalid value in the class Coordinates in value Y");
        }
    }

    public void validateHead(DragonHead head){
        if ((head.getSize() == null)){
            throw new ValidationException("Invalid value in DragonHead class (size is null)");
        }
    }
}