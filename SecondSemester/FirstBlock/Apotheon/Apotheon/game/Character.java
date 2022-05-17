package game;

public abstract class Character extends GameObject {

    private final int heatPoints;
    private final int powerPoints;
    private final int spellPoints;

    protected Character(String name, int heatPoints, int powerPoints, int spellPoints) {
        super (name);
        this.heatPoints = heatPoints;
        this.powerPoints = powerPoints;
        this.spellPoints = spellPoints;
    }

    public int getHeatPoints() {
        return heatPoints;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public int getSpellPoints() {
        return spellPoints;
    }
}
