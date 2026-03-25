public interface Command{

    String name();

    void execute(String argument);

    String description();
}