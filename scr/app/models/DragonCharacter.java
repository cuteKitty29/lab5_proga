package models;

public enum DragonCharacter {
    CUNNING("CUNNING"),
    EVIL("EVIL"),
    GOOD("GOOD"),
    CHAOTIC("CHAOTIC");

    private final String name;
    
    DragonCharacter(String name){
        this.name = name;
    }
}