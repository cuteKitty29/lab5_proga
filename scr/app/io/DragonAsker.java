public class DragonAsker {

    private Console MyConsole;
    private ConsoleInputHadler consoleInput;

    public DragonAsker(){
        Console MyConsole = new Console();
        ConsoleInputHadler consoleInput = new ConsoleInputHadler();
    }


    public String askName(){
        MyConsole.print("Input a name: ");
        String name = consoleInput.readLine();
        
    }
}
