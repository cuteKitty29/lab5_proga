package commands;
import managers.CollectionManager;

public class AddCommand implements Command{
    CollectionManager cm;



    @Override
    public void execute(){    //it is a temporary solution
        //создание объекта
        //добавление объекта в коллекцию
    }

    @Override
    public String description(){
        return "add new element in the collection";
    }
}