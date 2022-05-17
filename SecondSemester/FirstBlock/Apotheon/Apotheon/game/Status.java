package game;

import static game.MenuGame.framing;

public class Status extends Player {

    static public void statusPlayer(Player currentPlayer) {
        framing();
        System.out.println("Ходит игрок:     "
                + currentPlayer.getNamePlayer());
        System.out.println("Персонаж:        "
                + currentPlayer.getName());
        System.out.println("HP:              "
                + currentPlayer.getPlayerHp());
        System.out.println("Сила:            "
                + currentPlayer.getDamage());
        System.out.println("Очки заклинаний: "
                + currentPlayer.getSpellReserve());
    }
}
