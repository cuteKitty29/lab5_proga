import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;
import commands.*;


public class Invoker{

    Map<String, Command> mapCommands = new HashMap<>();


    mapCommands.put("help", new HelpCommand());
    mapCommands.put("add", new AddCommand());
    mapCommands.put("info", new InfoCommand());
    mapCommands.put("show", new ShowCommand());
    mapCommands.put("update", new UpdateCommand());
    mapCommands.put("remove_by_id", new RemoveById());
    mapCommands.put("clear", new ClearCommand());
    mapCommands.put("save", new SaveCommand());
    mapCommands.put("execute_script", new ExecuteScript());
    mapCommands.put("exit", new ExitCommand());
    mapCommands.put("head", new HeadCommand());
    mapCommands.put("remove_head", new RemoveHeadCommand());
    mapCommands.put("add_if_min", new AddIfMinCommand());
    mapCommands.put("sum_of_age", new SumOfAge());
    mapCommands.put("count_greater_than_age", new CountGreaterThanAgeCommand());
    mapCommands.put("print_field_descending_head", new PrintFieldDescendingHeadCommand());

    Scanner sc = new Scanner(System.in); 
    while (sc.hasNext()){
        String line = sc.next();
        String[] tokens = line.split(" ");
        Command command = mapCommands.get(tokens[0]);
        command.execute(Arrays.copyOfRange(tokens, 1, tokens.length + 1));
    }
}