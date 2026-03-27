package commands;

public class InfoCommand implements Command{

    @Override
    public void execute(String[] argument){
    }

    @Override
    public String name(){
        return "info";
    }
    @Override 
    public String description(){
        return "Info about collection";
    }
}