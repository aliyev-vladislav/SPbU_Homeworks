package game;
import java.util.Random;

import static game.Player.*;
import static game.Main.*;
import static game.Spell.*;
import static game.Status.statusPlayer;

public class GameController extends GameMode {

    public static void battlePlayers() {
        Player firstPlayer = new Player(characterSelection(),
                playerNameInput(), true);
        Player secondPlayer = new Player(characterSelection(),
                playerNameInput(), true);
        gameProcess(firstPlayer, secondPlayer);
    }

    public static void battlePlayersAndBot() {
        Player firstPlayer = new Player(characterSelection(),
                playerNameInput(), true);
        Player bot = new Player(createPlayer(botBrain(14)),
                "Бот", true);
        gameProcess(firstPlayer, bot);
    }

    public static void battleBots() {
        Player bot1 = new Player(createPlayer(botBrain(14)),
                "Бот-1", true);
        Player bot2 = new Player(createPlayer(botBrain(14)),
                "Бот-2", true);
        gameProcess(bot1, bot2);
    }

    public static Character characterSelection() {
        while (true) {
            int number;

            framing();
            listCharacter();
            System.out.println("[ 15 ] - " + Main.ANSI_RED
                    + "Назад" + Main.ANSI_RESET);
            showInputField();

            // Обрабатывается возможность преобразования строки в число
            try {
                number = Integer.parseInt(command.trim());
            } catch (NumberFormatException nfe) {
                showInputError();
                continue;
            }
            if (number < 1 || number > 15) {
                showInputError();
            } else if (number == 15) {
                gameModeSelection();
            } else {
                return createPlayer(command);
            }
        }
    }

    // Бот выбирает ход
    public static String botBrain(int number) {
        Random random = new Random();
        return Integer.toString((random.nextInt(number) + 1));
    }

    public static String playerNameInput() {
        while (true) {
            System.out.print(ANSI_GREEN + "\nНазови имя: " + ANSI_RESET);
            command = scanner.nextLine();
            /*
             * Проверка на доступность выбранного игроком имени,
             * оно не должно совпадать с зарезервированным именем ботов.
             */
            if (command.contains("Бот")) {
                System.out.println(ANSI_RED + "Недопустимое имя, попробуй ещё" + ANSI_RESET);
                continue;
            }
            return command;
        }
    }

    public static void gameProcess(Player firstPlayer, Player secondPlayer) {

        while ((firstPlayer.getPlayerHp() > 0) && (secondPlayer.getPlayerHp() > 0)) {
            statusPlayer(firstPlayer);
            movePlayer(firstPlayer, secondPlayer);
            secondPlayer.setState(true);

            statusPlayer(secondPlayer);
            movePlayer(secondPlayer, firstPlayer);
            firstPlayer.setState(true);
        }

        winnerCheck(firstPlayer, secondPlayer);
    }

    public static void showMoveOptionsPlayer() {
        System.out.println("\n[ 1 ] - " + ANSI_YELLOW
                + "Атаковать" + ANSI_RESET);
        System.out.println("[ 2 ] - " + ANSI_YELLOW
                + "Заклинание + Атака" + ANSI_RESET);
        showInputField();
    }

    public static void movePlayer(Player firstPlayer, Player secondPlayer) {

        if(firstPlayer.getNamePlayer().contains("Бот")) {

            // Ходит бот
            command = botBrain(2);
        } else {

            // Ходит игрок
            showMoveOptionsPlayer();
        }

        switch (command) {
            case "1":
                if (secondPlayer.isPlayerState()) {
                    secondPlayer.takeDamage(firstPlayer.getDamage());
                }
                break;
                
            case "2":
                spellUse(firstPlayer, secondPlayer);
                break;
                
            default:
                showInputError();
                movePlayer(firstPlayer, secondPlayer);
                break;
        }
    }

    public static void showListSpells(Spell spellfirstPlayer) {
        System.out.println("[ 1 ] - " + ANSI_YELLOW + "Здоровье: "
                + "+" + spellfirstPlayer.getValueIncreaseHp()
                + " очков" + ANSI_RESET);
        System.out.println("[ 2 ] - " + ANSI_YELLOW + "Cила: "
                + "+" + spellfirstPlayer.getValueIncreaseDamage()
                + " очков" + ANSI_RESET);
        System.out.println("[ 3 ] - " + ANSI_YELLOW
                + "Одноразовый щит" + ANSI_RESET);
        showInputField();
    }

    /*
     * Метод производит атаку при условии,
     * что противник не использовал заклинание "Щит".
     */
    public static void attackValidation(Player firstPlayer, Player secondPlayer) {
        if (secondPlayer.isPlayerState()) {
            secondPlayer.takeDamage(firstPlayer.getDamage());
        }
    }

    public static void spellUse(Player firstPlayer, Player secondPlayer) {
        Spell spellfirstPlayer = new Spell(firstPlayer);

        // Проверка наличия заклинаний
        if (firstPlayer.getSpellReserve() <= 0) {
            framing();
            System.out.println(ANSI_RED + "Нет заклинаний!" + ANSI_RESET);
            movePlayer(firstPlayer, secondPlayer);
        } else {
            while (true) {
                if (firstPlayer.getNamePlayer().contains("Бот")) {

                    // Ходит бот
                    command = botBrain(3);
                } else {

                    // Ходит игрок
                    showListSpells(spellfirstPlayer);
                }

                if (command.equals("1")) {
                    spellfirstPlayer.addHealth(firstPlayer);
                    attackValidation(firstPlayer, secondPlayer);
                    break;
                } else if (command.equals("2")) {
                    spellfirstPlayer.addDamage(firstPlayer);
                    attackValidation(firstPlayer, secondPlayer);
                    break;
                } else if (command.equals("3")) {
                    firstPlayer.setSpellReserve(firstPlayer.getSpellReserve()
                            - getSpellCost());
                    firstPlayer.setState(false);
                    attackValidation(firstPlayer, secondPlayer);
                    break;
                } else {
                    showInputError();
                    break;
                }
            }
        }
    }

    public static void winnerCheck(Player firstPlayer, Player secondPlayer) {

        if (firstPlayer.getPlayerHp() > secondPlayer.getPlayerHp()) {
            framing();
            System.out.println("Победитель: " + ANSI_YELLOW
                            + firstPlayer.getNamePlayer() + ANSI_RESET + " \\(★ω★)/");
            mainMenu();
        } else {
            framing();
            System.out.println("Победитель: " + ANSI_YELLOW
                    + secondPlayer.getNamePlayer() + ANSI_RESET + " \\(★ω★)/");
            mainMenu();
        }
    }
}
