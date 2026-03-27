package commands;

public class AddIfMinCommand implements Command{

    @Override
    public void execute(String[] argument){

    }

    @Override
    public String name(){
        return "add if min";
    }
    @Override
    public String description(){
        return "Add the ellement if it is less then the smallest ellement in the collection";
        }
}