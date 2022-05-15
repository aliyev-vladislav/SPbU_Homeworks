package LogicalGame;

public abstract class Character extends GameObject {

    private int heatPoints;

    private int powerPoints;

    private int spellPoints;


    public Character(String name, int heatPoints, int powerPoints, int spellPoints) {

        super(name);

        this.heatPoints = heatPoints;

        this.powerPoints = powerPoints;

        this.spellPoints = spellPoints;
    }


    public int getHeatPoints() {
        return heatPoints;
    }

    public void setHeatPoints(int heatPoints) {
        this.heatPoints = heatPoints;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getSpellPoints() {
        return spellPoints;
    }

    public void setSpellPoints(int spellPoints) {
        this.spellPoints = spellPoints;
    }

}
