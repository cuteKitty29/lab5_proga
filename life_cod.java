import java.util.Comparator;
import java.util.TreeSet;

class Person {
    Person (String name, int age){
        this.age = age;
        this.name = name;
    }
    public String name;
    public int age;

    public int getAge(){
        return age;
    }
}

public class life_cod {
    static void main (String[] args){
        Person person1 = new Person("AAAAA", 10);
        Person person2 = new Person("BBBB", 10);
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        TreeSet<Person> personSet = new TreeSet<>((person1, person2) -> byAge);
        System.out.println(personSet.size());   
    }

}