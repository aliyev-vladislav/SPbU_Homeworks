package game;

import java.util.Scanner;
import static game.Main.*;

public class MenuGame extends GameObject {

    private static final String[] nameCharacter =
            {
                    "Афродита", "Аполлон", "Арес", "Артемида",
                    "Афина", "Деметра", "Дионис",
                    "Аид", "Гефест", "Гера",
                    "Гермес", "Кронос", "Посейдон", "Зевс"
            };
    public static String command;
    public static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        do {
            framing();
            System.out.println("[ 1 ] - " + ANSI_CYAN
                    + "Начать игру" + ANSI_RESET);
            System.out.println("[ 2 ] - " + ANSI_CYAN
                    + "Информация о персонажах" + ANSI_RESET);
            System.out.println("[ 3 ] - " + ANSI_CYAN
                    + "Автор" + ANSI_RESET);
            System.out.println("[ 4 ] - " + ANSI_RED
                    + "Выход" + ANSI_RESET);

            showInputField();
            switch (command) {
                case "1":
                    GameMode.gameModeSelection();
                    break;

                case "2":
                    showInfoCharacter();
                    break;

                case "3":
                    showCredits();
                    break;

                case "4":
                    stopGame();
                default:
                    showInputError();
                    break;
            }
        } while (!command.equals("4"));
    }

    public static void showCredits() {
        framing();
        System.out.println(ANSI_YELLOW + "\nВладислав Алиев\n" +
                "Версия 1.0, дата последнего обновления 17.05.2022\n" +
                "Email: dalv.veiyla@gmail.com" + ANSI_RESET);
    }

    public static void listCharacter() {
        for (int i = 0; i <= 13; i++) {
            System.out.println("[ " + (i + 1) + " ]" + " - " + ANSI_CYAN +
                    nameCharacter[i] + ANSI_RESET);
        }
    }

    public static void showInfoCharacter() {
        do {
            framing();
            listCharacter();
            System.out.println("[ 15 ] - " + ANSI_RED
                    + "Назад" + ANSI_RESET);
            showInputField();
            System.out.println();

            switch (command) {
                case "1":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Афродита — богиня любви и красоты, \nвозникшая из морской пены." +
                            ANSI_RESET);
                    break;

                case "2":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Аполлон — бог, покровитель искусств, \nпрорицатель и целитель. " +
                            ANSI_RESET);
                    break;

                case "3":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Арес — бог войны." + ANSI_RESET);
                    break;

                case "4":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Артемида — богиня охоты, лунная богиня, \n" +
                            "Великая матерь всех вещей \nи покровительница жизненных сил." +
                            ANSI_RESET);
                    break;

                case "5":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Афина — богиня войны и победы, \nа также государственной мудрости, \n" +
                            "знаний, искусств и ремёсел." +
                            ANSI_RESET);
                    break;

                case "6":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Деметра — богиня плодородия, \nпокровительница земледелия." +
                            ANSI_RESET);
                    break;

                case "7":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Дионис — бог виноградарства и виноделия." +
                            ANSI_RESET);
                    break;

                case "8":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Аид — верховный бог подземного царства мёртвых." +
                            ANSI_RESET);
                    break;

                case "9":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Гефест — бог огня, \nпокровитель кузнечного ремесла. \n" +
                            "Сын Зевса и Геры." + ANSI_RESET);
                    break;

                case "10":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Гера — царица богов. \nПокровительница брака. \n1" +
                            "Жестокая, властная, ревнивая." + ANSI_RESET);
                    break;

                case "11":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Гермес — вестник богов, \nбог торговли и прибыли, \n" +
                            "покровитель пастухов и путников." +
                            ANSI_RESET);
                    break;

                case "12":
                    framing();
                    System.out.println(ANSI_RED +
                            "Кронос - титан, пожиратель богов." + ANSI_RESET);
                    break;

                case "13":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Посейдон — повелитель морей, \nа также землетрясений и лошадей." +
                            ANSI_RESET);
                    break;

                case "14":
                    framing();
                    System.out.println(ANSI_YELLOW +
                            "Зевс — верховный бог.\nВладыка богов и людей." +
                            ANSI_RESET);
                    break;

                case "15":
                    mainMenu();
                    break;

                default:
                    showInputError();
                    break;
            }

        } while(!command.equals("15"));
    }

    public static void showInputField() {
        System.out.print(ANSI_GREEN
                + "Поле для ввода >> " + ANSI_RESET);
        command = scanner.nextLine();
    }

    public static void showInputError() {
        framing();
        System.out.println(ANSI_RED +
                "Команда не распознана, попробуй ещё" + ANSI_RESET);
    }

    public static void framing() {
        System.out.println("--------------------------------------");
    }
}

