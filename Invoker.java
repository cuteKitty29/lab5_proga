import java.util.HashMap;
import java.util.Scanner;

public class Invoker{

    HashMap<String, Command> commands = new HashMap<>();
    commands.put("help", new HelpCommand());
    commands.put("add", new AddCommand());

    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()){
        String line = sc.next();
        String[] tokens = line.split(" ");
        Command command = commands.get(tokens[0]);
        command.execute();
    }
}