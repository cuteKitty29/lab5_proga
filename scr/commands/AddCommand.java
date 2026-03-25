public class AddCommand implements Command{
    CollectionManager cm;
    public void execute(Object ob){
        cm.add(ob);    //i don't know how i shoud get the object, which must be added
    }

    @Override
    public String name(){
        return "add";

    }

    @Override
    public void execute(String argument){    //it is a temporary solution
        
    }

    @Override
    public String description(){
        return "add new element in the collection";
    }
}