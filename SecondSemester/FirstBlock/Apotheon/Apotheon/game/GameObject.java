package game;

import static game.MenuGame.mainMenu;

public abstract class GameObject {

    protected String name;

    protected GameObject() {
    }

    protected GameObject(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    public static void startGame() {
       mainMenu();
    }
    public static void stopGame() {
        System.exit(0);
    }

}
