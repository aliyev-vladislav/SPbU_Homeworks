package controller;

public interface Command {
    boolean execute(Context context, String... args);
    String getName();
}