import models.*;
import util.ValidationException;

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
        return name;
    }

    public Coordinates askCoordinates(){
        MyConsole.print("Input coordinates");
        String[] StringCoordinates = consoleInput.readLine().split(" ");
        Double xCoordinate = Double.valueOf(StringCoordinates[0]);
        Float yCoordinate = Float.valueOf(StringCoordinates[1]);
        Coordinates newCoordinates = new Coordinates(xCoordinate.doubleValue(), yCoordinate);
        return newCoordinates;
    }

    public double askCoordinateX(){
        MyConsole.print("Input a double value for x coordinate: ");
        String inpuString = consoleInput.readLine();
        Double xCoordinate = Double.valueOf(inpuString.strip());
        return xCoordinate.doubleValue();
    }

        public double askCoordinateY(){
        MyConsole.print("Input a float value for y coordinate: ");
        String inpuString = consoleInput.readLine();
        Float yCoordinate = Float.valueOf(inpuString.strip());
        return yCoordinate;
    }

    public long askAge(){
        MyConsole.print("Input a age in long format");
        String inpuString = consoleInput.readLine();
        Long age = Long.valueOf(inpuString.strip());
        return age.longValue();
    }

    public Color asColor(){
        MyConsole.print("Input a color (BLACK, BLUE, ORANGE, WHITE, BROWN): ");
        String inputString = consoleInput.readLine().strip();
        try{
            switch (inputString){
                case "BLACK":
                    return Color.BLACK;
                case "BLUE":
                    return Color.BLUE;
                case "ORANGE":
                    return Color.ORANGE;
                case "WHITE":
                    return Color.WHITE;
                case "BROWN":
                    return Color.BROWN;
                default: 
                    throw new ValidationException("Incorrect value input");
            }
        }
        catch (Exception e){
            MyConsole.println(e.getMessage());
            return null;
        }
        
    }

    public DragonType askType(){
        MyConsole.print("Input a type ( WATER, UNDERGROUND, AIR, FIRE ");
        String inputString = consoleInput.readLine().strip();
        try{
            switch (inputString){
                case "WATER":
                    return DragonType.WATER;
                case "UNDERGROUND":
                    return DragonType.UNDERGROUND;
                case "AIR":
                    return DragonType.AIR;
                case "FIRE":
                    return DragonType.FIRE;
                default: 
                    throw new ValidationException("Incorrect value input");
            }
        }
        catch (Exception e){
            MyConsole.println(e.getMessage());
            return null;
        }
    }
}
