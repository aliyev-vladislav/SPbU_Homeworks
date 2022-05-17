package game;

import static game.GameObject.startGame;

/**
 * @version 1.0  17 May 2022
 * @author Vladislav Aliev
 */

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в " + Main.ANSI_YELLOW
                + "«Апофеоз»" + Main.ANSI_RESET + "!");
        startGame();
    }
}
