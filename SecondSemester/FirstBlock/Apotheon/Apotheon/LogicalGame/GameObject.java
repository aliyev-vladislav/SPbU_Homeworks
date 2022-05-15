package LogicalGame;

public abstract class GameObject {

    protected String name;


    protected GameObject() {
    }


    protected GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void startGame() {
       Menu.mainMenu();
    }
    public static void stopGame() {
        System.exit(0);
    }

}
