package LogicalGame;
import Characters.*;
import LogicalGame.Character;
import LogicalGame.GameObject;

import javax.print.DocFlavor;

public class Spells extends GameObject {
    public static final int SPELL_COST = 1;

    private int valueIncreaseHp;

    private int valueIncreaseDamage;


    protected Spells(Player character) {

        this.valueIncreaseDamage = (int) (character.getPlayerDamage() * 0.25);

        this.valueIncreaseHp = (int) (character.getPlayerHp() * 0.3);

    }

    public int getValueIncreaseHp() {
        return valueIncreaseHp;
    }

    public int getValueIncreaseDamage() {
        return valueIncreaseDamage;
    }

    public void addHealth(Player hp) {

        hp.setPlayerHp(hp.getPlayerHp() + valueIncreaseHp);

        hp.setPlayerSpellReserve(hp.getPlayerSpellReserve() - SPELL_COST);

    }
    public void addDamage(Player damage) {

        damage.setDamage(damage.getPlayerDamage() + valueIncreaseDamage);

        damage.setPlayerSpellReserve(damage.getPlayerSpellReserve() - SPELL_COST);

    }

}
