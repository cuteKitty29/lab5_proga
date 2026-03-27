package commands;

public class AddCommand implements Command{
    CollectionManager cm;


    @Override
    public String name(){
        return "add";

    }

    @Override
    public void execute(String[] argument){    //it is a temporary solution
        //создание объекта
        //добавление объекта в коллекцию
    }

    @Override
    public String description(){
        return "add new element in the collection";
    }
}