package LogicalGame;

import Characters.*;

public class Player extends GameObject {

    private String namePlayer;

    private int heatPoints;

    private int powerPoints;

    private int spellPoints;

    private boolean playerState;

    public Player(Character dataCharacter, boolean playerState) {

        this.namePlayer = "Бот";

        this.name = dataCharacter.getName();

        this.heatPoints = dataCharacter.getHeatPoints();

        this.powerPoints = dataCharacter.getPowerPoints();

        this.spellPoints = dataCharacter.getSpellPoints();

        this.playerState = playerState;
    }


     public Player(Character dataCharacter, String namePlayer, boolean playerState) {

        this.namePlayer = namePlayer;

        this.name = dataCharacter.getName();

        this.heatPoints = dataCharacter.getHeatPoints();

        this.powerPoints = dataCharacter.getPowerPoints();

        this.spellPoints = dataCharacter.getSpellPoints();

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

    public int getPlayerDamage() {
        return powerPoints;
    }

    public boolean isPlayerState() {
        return playerState;
    }

    public void setPlayerState(boolean playerState) {
        this.playerState = playerState;
    }

    public void setDamage(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getPlayerSpellReserve() {
        return spellPoints;
    }

    public void setPlayerSpellReserve(int spellPoints) {
        this.spellPoints = spellPoints;
    }

    public void takeDamage(int damage) {
         this.heatPoints -= damage;
    }

    static Character creatPlayer(String character) {

        if (character.equals("1")) {
            return new Aphrodite();

        } else if (character.equals("2")) {
            return new Apollo();

        } else if (character.equals("3")) {
            return new Ares();

        } else if (character.equals("4")) {
            return new Artemis();

        } else if (character.equals("5")) {
            return new Athena();

        } else if (character.equals("6")) {
            return new Demeter();

        } else if (character.equals("7")) {
            return new Dionysus();

        } else if (character.equals("8")) {
            return new Hades();

        } else if (character.equals("9")) {
            return new Hephaestus();

        } else if (character.equals("10")) {
            return new Hera();

        } else if (character.equals("11")) {
            return new Hera();

        } else if (character.equals("12")) {
            return new Kronos();

        } else if (character.equals("13")) {
            return new Poseidon();

        } else {
            return new Zeus();
        }
    }
}
