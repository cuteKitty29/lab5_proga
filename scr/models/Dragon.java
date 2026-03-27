package models;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;




public class Dragon implements Comparable<Dragon> {
    public static Integer nextID = Integer(0); 
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long age; //Значение поля должно быть больше 0
    private Color color; //Поле может быть null
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;

    /**
     * Полный конструктор.
     */
    /*public Dragon(Integer id,
                  String name,
                  Coordinates coordinates,
                  Date creationDate,
                  long age,
                  Color color,
                  DragonType type,
                  DragonCharacter character,
                  DragonHead head) {
        id = Dragon.nextID++;
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setAge(age);
        setColor(color);
        setType(type);
        setCharacter(character);
        setHead(head);
    }*/

   public Dragon(){
    id = Dragon.nextID++;
   }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Поле name не может быть null или пустым.");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = Objects.requireNonNull(coordinates, "Поле coordinates не может быть null.");
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = Objects.requireNonNull(creationDate, "Поле creationDate не может быть null.");
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Поле age должно быть больше 0.");
        }
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public DragonHead getHead() {
        return head;
    }

    public void setHead(DragonHead head) {
        this.head = head;
    }

    @Override
    public int compareTo(Dragon other) {
        return(this.id - other.getId());      
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creationDate);
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + date +
                ", age=" + age +
                ", color=" + color +
                ", type=" + type +
                ", character=" + character +
                ", head=" + head +
                '}';
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id, name, coordinates, creationDate, age, color, type, character, head);
    }

    @Override
    public boolean equals(Dragon other){
        return this.id == other.getId();
    }
}







