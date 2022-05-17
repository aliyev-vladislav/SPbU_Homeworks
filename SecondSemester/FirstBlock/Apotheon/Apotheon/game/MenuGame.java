package game;

import java.util.Scanner;

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
            System.out.println("[ 1 ] - " + "Начать игру");
            System.out.println("[ 2 ] - " + "Информация о персонажах");
            System.out.println("[ 3 ] - " + "Автор");
            System.out.println("[ 4 ] - " + "Выход");

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
        System.out.println("\nВладислав Алиев\n" +
                "Версия 1.0, дата последнего обновления 17.05.2022\n" +
                "Email: dalv.veiyla@gmail.com");
    }

    public static void listCharacter() {
        for (int i = 0; i <= 13; i++) {
            System.out.println("[ " + (i + 1) + " ]" + " - " + nameCharacter[i]);
        }
    }

    public static void showInfoCharacter() {
        do {
            framing();
            listCharacter();
            System.out.println("[ 15 ] - Назад");
            showInputField();
            System.out.println();

            switch (command) {
                case "1":
                    framing();
                    System.out.println("Афродита - богиня любви и красоты, " +
                            "\nвозникшая из морской пены.");
                    break;

                case "2":
                    framing();
                    System.out.println("Аполлон - бог, покровитель искусств, " +
                            "\nпрорицатель и целитель. ");
                    break;

                case "3":
                    framing();
                    System.out.println("Арес - бог войны.");
                    break;

                case "4":
                    framing();
                    System.out.println("Артемида - богиня охоты, лунная богиня, \n" +
                            "Великая матерь всех вещей " +
                            "\nи покровительница жизненных сил.");
                    break;

                case "5":
                    framing();
                    System.out.println("Афина - богиня войны и победы, " +
                            "\nа также государственной мудрости, \n" +
                            "знаний, искусств и ремёсел.");
                    break;

                case "6":
                    framing();
                    System.out.println("Деметра - богиня плодородия, " +
                            "\nпокровительница земледелия.");
                    break;

                case "7":
                    framing();
                    System.out.println("Дионис - бог виноградарства и виноделия.");
                    break;

                case "8":
                    framing();
                    System.out.println("Аид - верховный бог подземного царства мёртвых.");
                    break;

                case "9":
                    framing();
                    System.out.println("Гефест - бог огня, " +
                            "\nпокровитель кузнечного ремесла. \nСын Зевса и Геры.");
                    break;

                case "10":
                    framing();
                    System.out.println("Гера - царица богов. " +
                            "\nПокровительница брака. \n1" +
                            "Жестокая, властная, ревнивая.");
                    break;

                case "11":
                    framing();
                    System.out.println("Гермес - вестник богов, " +
                            "\nбог торговли и прибыли, \n" +
                            "покровитель пастухов и путников.");
                    break;

                case "12":
                    framing();
                    System.out.println("Кронос - титан, пожиратель богов.");
                    break;

                case "13":
                    framing();
                    System.out.println("Посейдон - повелитель морей, " +
                            "\nа также землетрясений и лошадей.");
                    break;

                case "14":
                    framing();
                    System.out.println("Зевс - верховный бог.\nВладыка богов и людей.");
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
        System.out.print("Поле для ввода >> ");
        command = scanner.nextLine();
    }

    public static void showInputError() {
        framing();
        System.out.println("Команда не распознана, попробуй ещё");
    }

    public static void framing() {
        System.out.println("--------------------------------------");
    }
}

