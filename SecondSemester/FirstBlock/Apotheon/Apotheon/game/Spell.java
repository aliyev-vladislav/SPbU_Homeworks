package game;

public class Spell extends GameObject {

    private static final int SPELL_COST = 1; // Цена использования заклинания
    private int valueIncreaseHp;
    private int valueIncreaseDamage;

    protected Spell(Player currentPlayer) {
        /*
         * Просчитывается возможное увеличение характеристик персонажа,
         * исходя из текущих параметров
         */
        this.valueIncreaseDamage = (int) (currentPlayer.getDamage() * 0.25);
        this.valueIncreaseHp = (int) (currentPlayer.getPlayerHp() * 0.3);
    }

    public void addHealth(Player currentPlayer) {
        currentPlayer.setPlayerHp(currentPlayer.getPlayerHp() + valueIncreaseHp);
        currentPlayer.setSpellReserve(currentPlayer.getSpellReserve() - SPELL_COST);
    }

    public void addDamage(Player currentPlayer) {
        currentPlayer.setDamage(currentPlayer.getDamage() + valueIncreaseDamage);
        currentPlayer.setSpellReserve(currentPlayer.getSpellReserve() - SPELL_COST);
    }

    public static int getSpellCost() {
        return SPELL_COST;
    }

    public int getValueIncreaseHp() {
        return valueIncreaseHp;
    }

    public int getValueIncreaseDamage() {
        return valueIncreaseDamage;
    }
}
