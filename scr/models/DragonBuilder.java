package models;

public class implements DragonBuilder{

    public DragonBuilder(){
        Dragon thisDragon = new Dragon();
    }

    public DragonBuilder setName(String name){
        thisDragon.setName(name);
        return this;
    }

    public DragonBuilder setCoordinates(Coordinates coordinates){
        thisDragon.setCoordinates(coordinates);
        return this;
    }
    
    public DragonBuilder setCreationDate(Date date){
        thisDragon.setCreationDate(date);
        return this;
    }

    public DragonBuilder setAge(long age){
        thisDragon.setAge(age);
        return this;
    }

    public DragonBuilder setColor(Color color){
        thisDragon.setColor(color);
        return this;
    }

    public DragonBuilder setType(DragonType type){
        thisDragon.setType(type);
        return this;
    }

    public DragonBuilder setHead(DragonHead head){
        thisDragon.setHead(head);
        return this;
    }

    public DragonBuilder setCharacter(DragonCharacter character){
        thisDragon.setCharacter(character);
        return this;
    }
    
    
    
    public Dragon create(){
        return thisDragon;
    }
}