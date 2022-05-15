package LogicalGame;

import java.util.Scanner;

public class Menu extends GameObject {

    static String[] nameCharacter =
            {
                    "Афродита", "Аполлон", "Арес", "Артемида",
                    "Афина", "Деметра", "Дионис",
                    "Аид", "Гефест", "Гера",
                    "Гермес", "Кронос", "Посейдон", "Зевс"
            };

    static Scanner scanner = new Scanner(System.in);
    static String command;

    public static void mainMenu() {


        do {

            framing();

            System.out.println("[ 1 ] - " + GameController.ANSI_CYAN
                    + "Начать игру" + GameController.ANSI_RESET);

            System.out.println("[ 2 ] - " + GameController.ANSI_CYAN
                    + "Информация о персонажах" + GameController.ANSI_RESET);

            System.out.println("[ 3 ] - " + GameController.ANSI_CYAN
                    + "Авторы" + GameController.ANSI_RESET);

            System.out.println("[ 4 ] - " + GameController.ANSI_RED
                    + "Выход" + GameController.ANSI_RESET);

            inputField();
            command = scanner.nextLine();

            switch (command) {

                case "1":
                    GameMode.gameModeSelection();
                    break;
                case "2":
                    openInfoCharacter();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    GameObject.stopGame();
                default:
                    inputError();

            }

        } while (!command.equals("4"));

    }

    private static void showCredits() {

        framing();

        System.out.println("\nVladlen\n" +
                "Версия 1.0, дата последнего обновления 12.05.2022\n" +
                "E1mail: dalv_veiyla@mail.com");
    }

    public static void listCharacter() {

        for (int i = 0; i <= 13; i++) {

            System.out.println("[ " + (i + 1) + " ]" + " - " + GameController.ANSI_CYAN +
                    nameCharacter[i] + GameController.ANSI_RESET);

        }

    }

    private static void openInfoCharacter() {

        do {
            framing();

            listCharacter();

            System.out.println("[ 15 ] - " + GameController.ANSI_RED
                    + "Назад" + GameController.ANSI_RESET);

            inputField();
            command = scanner.nextLine();

            System.out.println();

            switch (command) {

                case "1":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Афродита — богиня любви и красоты, \nвозникшая из морской пены." +
                            GameController.ANSI_RESET);
                    break;
                case "2":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Аполлон — бог, покровитель искусств, \nпрорицатель и целитель. " +
                            GameController.ANSI_RESET);
                    break;
                case "3":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Арес — бог войны." + GameController.ANSI_RESET);
                    break;
                case "4":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Артемида — богиня охоты, лунная богиня, \n" +
                            "Великая матерь всех вещей \nи покровительница жизненных сил." +
                            GameController.ANSI_RESET);
                    break;
                case "5":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Афина — богиня войны и победы, \nа также государственной мудрости, \n" +
                            "знаний, искусств и ремёсел." +
                            GameController.ANSI_RESET);
                    break;
                case "6":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Деметра — богиня плодородия, \nпокровительница земледелия." +
                            GameController.ANSI_RESET);
                    break;
                case "7":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Дионис — бог виноградарства и виноделия." +
                            GameController.ANSI_RESET);
                    break;
                case "8":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Аид — верховный бог подземного царства мёртвых." +
                            GameController.ANSI_RESET);
                    break;
                case "9":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Гефест — бог огня, \nпокровитель кузнечного ремесла. \n" +
                            "Сын Зевса и Геры." + GameController.ANSI_RESET);
                    break;
                case "10":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Гера — царица богов. \nПокровительница брака. \n1" +
                            "Жестокая, властная, ревнивая." + GameController.ANSI_RESET);
                    break;
                case "11":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Гермес — вестник богов, \nбог торговли и прибыли, \n" +
                            "покровитель пастухов и путников." +
                            GameController.ANSI_RESET);
                    break;
                case "12":
                    framing();
                    System.out.println(GameController.ANSI_RED +
                            "Кронос - титан, пожиратель богов." +GameController.ANSI_RESET);
                    break;
                case "13":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Посейдон — повелитель морей, \nа также землетрясений и лошадей." +
                            GameController.ANSI_RESET);
                    break;
                case "14":
                    framing();
                    System.out.println(GameController.ANSI_YELLOW +
                            "Зевс — верховный бог.\nВладыка богов и людей." +
                            GameController.ANSI_RESET);
                    break;
                case "15":
                    mainMenu();
                    break;
                default:
                    inputError();
            }

        } while(!command.equals("15"));

    }

    static void framing() {
        System.out.println("--------------------------------------");
    }

    static void inputField() {
        System.out.print(GameController.ANSI_GREEN
                + "Поле для ввода >> " + GameController.ANSI_RESET);
    }

    static void inputError() {
        framing();
        System.out.println(GameController.ANSI_RED +
                "Команда не распознана, попробуй ещё" + GameController.ANSI_RESET);
    }
}

