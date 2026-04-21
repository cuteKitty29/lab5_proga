package commands;

public class ExitCommand implements Command{

    @Override
    public void execute(){

    }
    

    @Override
    public String description(){
        return "End the programm (the collection is not saved in a file)";
    }
}