package LogicalGame;
import java.util.Random;

import static LogicalGame.GameController.*;
import static LogicalGame.Spells.SPELL_COST;


public class Start extends GameMode {
    static void battlePlayers() {
        Player player1 = new Player(characterSelection(),
                playerNameInput(), true);

        Player player2 = new Player(characterSelection(),
                playerNameInput(), true);

        gameProcess(player1, player2);
    }

    static void battlePlayersAndAI() {

        Player player1 = new Player(characterSelection(),
                playerNameInput(), true);

        Player bot = new Player(Player.creatPlayer(botBrain(14)),
                true);

        gameProcess(player1, bot);
    }

    static void battleAI() {

        Player bot1 = new Player(Player.creatPlayer(botBrain(14)),
                true);
        Player bot2 = new Player(Player.creatPlayer(botBrain(14)),
                true);

        gameProcess(bot1, bot2);
    }

    static String botBrain(int number) {
        Random random = new Random();
        return Integer.toString((random.nextInt(number) + 1));
    }


    static String playerNameInput() {

        System.out.print(ANSI_GREEN + "\nНазови имя: " + ANSI_RESET);

        return command = scanner.nextLine();

    }

    static void gameProcess(Player player1, Player player2) {

        while ((player1.getPlayerHp() > 0) && (player2.getPlayerHp() > 0)) {

            Status.statusPlayer(player1);

            movePlayer(player1, player2);

            player2.setPlayerState(true);

            Status.statusPlayer(player2);

            movePlayer(player2, player1);

            player1.setPlayerState(true);

        }

        winnerCheck(player1, player2);
    }

    static void movePlayer(Player currentPlayer1, Player currentPlayer2) {

        if(currentPlayer1.getNamePlayer().equals("Бот")) {

            command = botBrain(2);

        } else {

            System.out.println("\n[ 1 ] - " + ANSI_YELLOW
                    + "Атаковать" + ANSI_RESET);

            System.out.println("[ 2 ] - " + ANSI_YELLOW
                    + "Заклинание + Атака" + ANSI_RESET);

            inputField();

            command = scanner.nextLine();

        }

        switch (command) {

            case "1":
                if (currentPlayer2.isPlayerState()) {

                    currentPlayer2.takeDamage(currentPlayer1.getPlayerDamage());

                }
                break;

            case "2":

                framing();

                spellUse(currentPlayer1, currentPlayer2);

                break;

            default:

                inputError();

                framing();

                movePlayer(currentPlayer1, currentPlayer2);

                break;
        }
    }

    static void winnerCheck(Player player1, Player player2) {

        if (player1.getPlayerHp() > player2.getPlayerHp()) {

            framing();

            System.out.println("Победитель: " + ANSI_YELLOW
                            + player1.getNamePlayer() + ANSI_RESET + " \\(★ω★)/");

            mainMenu();

        } else {

            framing();

            System.out.println("Победитель: " + ANSI_YELLOW
                    + player2.getNamePlayer() + ANSI_RESET + " \\(★ω★)/");

            mainMenu();

        }
    }

    static void spellUse(Player currentPlayer1, Player currentPlayer2) {

        Spells spellPlayer1 = new Spells(currentPlayer1);

        if (currentPlayer1.getPlayerSpellReserve() <= 0) {

            framing();

            inputError();

            movePlayer(currentPlayer1, currentPlayer2);

        } else {

            loop : while (true) {

                if(currentPlayer1.getNamePlayer().equals("Бот")) {

                    command = botBrain(3);

                } else {

                    System.out.println("[ 1 ] - " + ANSI_YELLOW + "Здоровье: "
                            + "+" + spellPlayer1.getValueIncreaseHp() + " очков" + ANSI_RESET);

                    System.out.println("[ 2 ] - " + ANSI_YELLOW + "Cила: "
                            + "+" + spellPlayer1.getValueIncreaseDamage() + " очков" + ANSI_RESET);

                    System.out.println("[ 3 ] - " + ANSI_YELLOW + "Одноразовый щит" + ANSI_RESET);

                    inputField();

                    command = scanner.nextLine();

                }

                switch (command) {

                    case "1":

                        spellPlayer1.addHealth(currentPlayer1);

                        if (currentPlayer2.isPlayerState()) {

                            currentPlayer2.takeDamage(currentPlayer1.getPlayerDamage());

                        }
                        break loop;

                    case "2":

                        spellPlayer1.addDamage(currentPlayer1);

                        if (currentPlayer2.isPlayerState()) {

                            currentPlayer2.takeDamage(currentPlayer1.getPlayerDamage());

                        }
                        break loop;

                    case "3":

                        currentPlayer1.setPlayerSpellReserve(currentPlayer1.getPlayerSpellReserve()
                                - SPELL_COST);

                        currentPlayer1.setPlayerState(false);

                        if (currentPlayer2.isPlayerState()) {

                            currentPlayer2.takeDamage(currentPlayer1.getPlayerDamage());

                        }
                        break loop;

                    default:

                        inputError();

                        framing();

                        break;

                }
            }
        }
    }

    static Character characterSelection() {

        while (true) {

            int number;

            framing();

            listCharacter();

            System.out.println("[ 15 ] - " + GameController.ANSI_RED
                    + "Назад" + GameController.ANSI_RESET);

            inputField();
            command = scanner.nextLine();

            try {

                number = Integer.parseInt(command.trim());

            } catch (NumberFormatException nfe) {

                inputError();

                continue;

            }

            if (number < 1 || number > 15) {
                inputError();

            } else if (number == 15) {
                GameMode.gameModeSelection();

            } else {
                return Player.creatPlayer(command);

            }
        }
    }
}
