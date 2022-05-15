package LogicalGame;

import static LogicalGame.GameController.*;

public class GameMode extends Menu {
    static void gameModeSelection() {

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

            inputField();
            command = scanner.nextLine();

            switch (command) {

                case "1":

                    Start.battlePlayers();
                    break;

                case "2":

                    Start.battlePlayersAndAI();
                    break;

                case "3":

                    Start.battleAI();
                    break;

                case "4":

                    mainMenu();
                    break;

                default:

                    inputError();
                    break;

            }

        } while (!command.equals("4"));
    }
}

