package game;

import static game.Main.*;
import static game.GameController.*;

public class GameMode extends MenuGame {

    public static void gameModeSelection() {

        do {
            framing();
            System.out.println(ANSI_CYAN
                    + "Выбор режима игры" + ANSI_RESET);
            System.out.println("[ 1 ] - " + ANSI_YELLOW
                    + "Игрок против игрока" + ANSI_RESET);
            System.out.println("[ 2 ] - " + ANSI_YELLOW
                    + "Игрок против бота" + ANSI_RESET);
            System.out.println("[ 3 ] - " + ANSI_YELLOW
                    + "Бот против бота" + ANSI_RESET);
            System.out.println("[ 4 ] - " + ANSI_RED
                    + "Назад" + ANSI_RESET);
            showInputField();

            switch (command) {
                case "1":

                    battlePlayers();
                    break;

                case "2":
                    battlePlayersAndBot();
                    break;

                case "3":
                    battleBots();
                    break;

                case "4":
                    mainMenu();
                    break;

                default:
                    showInputError();
                    break;
            }
        } while (!command.equals("4"));
    }
}

