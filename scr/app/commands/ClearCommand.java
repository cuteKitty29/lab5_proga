package commands;

public class ClearCommand implements Command{

    @Override
    public void execute(String[] argument){
    }

    @Override
    public String name(){
        return  "clear";
    }

    @Override
    public String description(){
        return "Clear the collection";
    }
}