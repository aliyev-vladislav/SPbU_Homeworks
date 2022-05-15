package LogicalGame;

import static LogicalGame.GameController.*;

public class Status extends GameObject {
    static public void statusPlayer(Player player) {

        Menu.framing();

        System.out.println("Ходит игрок:     " + ANSI_PURPLE
                + player.getNamePlayer() + ANSI_RESET);

        System.out.println("Персонаж:        " + ANSI_PURPLE
                + player.getName() + ANSI_RESET);

        System.out.println("HP:              " + ANSI_PURPLE
                + player.getPlayerHp() + ANSI_RESET);

        System.out.println("Сила:            " + ANSI_PURPLE
                + player.getPlayerDamage() + ANSI_RESET);

        System.out.println("Очки заклинаний: " + ANSI_PURPLE
                + player.getPlayerSpellReserve() + ANSI_RESET);

    }

}
