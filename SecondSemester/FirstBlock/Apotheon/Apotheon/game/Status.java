package game;

import static game.Main.*;
import static game.MenuGame.framing;

public class Status extends Player {

    static public void statusPlayer(Player currentPlayer) {
        framing();
        System.out.println("Ходит игрок:     " + ANSI_PURPLE
                + currentPlayer.getNamePlayer() + ANSI_RESET);
        System.out.println("Персонаж:        " + ANSI_PURPLE
                + currentPlayer.getName() + ANSI_RESET);
        System.out.println("HP:              " + ANSI_PURPLE
                + currentPlayer.getPlayerHp() + ANSI_RESET);
        System.out.println("Сила:            " + ANSI_PURPLE
                + currentPlayer.getDamage() + ANSI_RESET);
        System.out.println("Очки заклинаний: " + ANSI_PURPLE
                + currentPlayer.getSpellReserve() + ANSI_RESET);
    }
}
