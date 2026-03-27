import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class ScriptInputHadler implements InputHadler{

    private BufferedReader reader;
    private boolean hasNext = false;

    public ScriptInputHadler(String nameFile){
        try{
        reader = new BufferedReader(new FileReader(nameFile));
        }
        catch (FileNotFoundException e){
            e.getMessage();
        }
    }
    

    @Override
    public String readLine(){
        try{
            String nextLine = reader.readLine();
            if (nextLine == null){
                hasNext = false;
            }
            else{
                hasNext = true;
            }
            return nextLine;
        }
        catch (Exception e){
            e.getMessage();
            return "";
        }
    }

    @Override 
    public boolean hasNextLine(){
        return hasNext;
    }
}
