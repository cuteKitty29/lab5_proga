import java.util.Scanner;

public class ConsoleInputHadler implements InputHadler {
    private Scanner sc = new Scanner(System.in); 

    @Override 
    public String readLine() {
        return sc.next();
    }

    @Override
    public boolean hasNextLine(){
        return sc.hasNext();
    }
}
