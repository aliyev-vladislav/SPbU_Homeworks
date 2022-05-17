package game;

import units.*;

public class Player extends GameObject {

    private String namePlayer;
    private int heatPoints;
    private int powerPoints;
    private int spellPoints;
    private boolean playerState; // Защита

    protected Player() {

    }
     protected Player(Character currentCharacter, String namePlayer, boolean playerState) {
        this.namePlayer = namePlayer;
        this.name = currentCharacter.getName();
        this.heatPoints = currentCharacter.getHeatPoints();
        this.powerPoints = currentCharacter.getPowerPoints();
        this.spellPoints = currentCharacter.getSpellPoints();
        this.playerState = playerState;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public int getPlayerHp() {
        return heatPoints;
    }

    public void setPlayerHp(int heatPoints) {
        this.heatPoints = heatPoints;
    }

    public int getDamage() {
        return powerPoints;
    }

    public boolean isPlayerState() {
        return playerState; // Наличие щита
    }

    public void setState(boolean playerState) {
        this.playerState = playerState;
    }

    public void setDamage(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getSpellReserve() {
        return spellPoints;
    }

    public void setSpellReserve(int spellPoints) {
        this.spellPoints = spellPoints;
    }

    public void takeDamage(int damage) {
         this.heatPoints -= damage;
    }

    public static Character createPlayer(String character) {
        switch (character) {
            case "1":
                return new Aphrodite();
            case "2":
                return new Apollo();
            case "3":
                return new Ares();
            case "4":
                return new Artemis();
            case "5":
                return new Athena();
            case "6":
                return new Demeter();
            case "7":
                return new Dionysus();
            case "8":
                return new Hades();
            case "9":
                return new Hephaestus();
            case "10":
                return new Hera();
            case "11":
                return new Hermes();
            case "12":
                return new Kronos();
            case "13":
                return new Poseidon();
            default:
                return new Zeus();
        }
    }
}
