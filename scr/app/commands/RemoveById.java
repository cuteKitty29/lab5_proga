package commands;

public class RemoveById implements Command{

    @Override
    public void execute(String[] arguments){
    }

    @Override
    public String name(){
        return "remove_by_id";
    }
    
    @Override 
    public String description(){
        return "";
    }
}