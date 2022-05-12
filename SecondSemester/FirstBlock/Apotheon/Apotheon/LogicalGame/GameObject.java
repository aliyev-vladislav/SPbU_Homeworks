package LogicalGame;

import javafx.scene.Camera;

public abstract class GameObject {
    // Очки жизни объекта
    public int heatPoints;
     // Очки силы объекта
    public int powerPoints;

    protected GameObject(int heatPoints, int powerPoints) {
        this.heatPoints = heatPoints;
        this.powerPoints = powerPoints;
    }

    //Каждый объект должен атаковать оппонента

    public void addHealth(int value) {
        this.heatPoints += value;
    }
}
