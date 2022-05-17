package game;

import static game.GameController.*;

public class GameMode extends MenuGame {

    public static void gameModeSelection() {

        do {
            framing();
            System.out.println("Выбор режима игры");
            System.out.println("[ 1 ] - Игрок против игрока");
            System.out.println("[ 2 ] - Игрок против бота");
            System.out.println("[ 3 ] - Бот против бота");
            System.out.println("[ 4 ] - Назад");
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

