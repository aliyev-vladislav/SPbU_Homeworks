import controller.CommandProcessor;

public class Main {
    public static void main(String[] args) {
        CommandProcessor start = CommandProcessor.getInstance();
        start.execute();
    }
}
