import java.util.Scanner;
public class Menu {
    public static void mainMenu() {

        Scanner scanner1 = new Scanner(System.in);
        String command;

        do {
            System.out.println();
            System.out.println("----------------------------");
            System.out.println("1. " + GameController.ANSI_CYAN + "Начать игру" + GameController.ANSI_RESET);
            System.out.println("2. " + GameController.ANSI_CYAN + "Информация о персонажах" + GameController.ANSI_RESET);
            System.out.println("3. " + GameController.ANSI_CYAN + "Авторы" + GameController.ANSI_RESET);
            System.out.println("4. " + GameController.ANSI_RED + "Выход" + GameController.ANSI_RESET);
            System.out.println("---------------------------");
            System.out.println();
            System.out.print(GameController.ANSI_GREEN + "Поле для ввода >> " + GameController.ANSI_RESET);
            command = scanner1.nextLine();
            System.out.println();

            switch (command) {
                case "1":
                    startGame();
                    break;
                case "2":
                    openInfoCharacter();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("---------------------------");
                    System.out.println(GameController.ANSI_RED + "\nКоманда не распознана, попробуй ещё" + GameController.ANSI_RESET);
            }
        } while (!command.equals("4"));
    }
    private static void startGame() {

    }

    private static void showCredits() {
        System.out.println("---------------------------");
        System.out.println("Vladlen\n" +
                "Версия 1.0, дата последнего обновления 12.05.2022\n" +
                "E1mail: dalv_veiyla@mail.com");
    }

    private static void openInfoCharacter() {
        Scanner scanner2 = new Scanner(System.in);
        String command;

        do {
            String[] nameCharacter = { "Зевс", "Гера", "Посейдон", "Аид", "Афродита", "Гефест", "Деметра", "Дионис", "Аполлон",
                    "Артемида", "Гермес", "Афина", "Арес" };

            System.out.println("----------------------------");

            for (int i = 0; i <= 12; i++) {
                System.out.println((i + 1) + ". " + GameController.ANSI_CYAN + nameCharacter[i] + GameController.ANSI_RESET);
            }
            System.out.println("14. " + GameController.ANSI_RED + "Назад" + GameController.ANSI_RESET);
            System.out.println("----------------------------");
            System.out.print(GameController.ANSI_GREEN + "\nПоле для ввода >> " + GameController.ANSI_RESET);

            command = scanner2.nextLine();

            System.out.println();
            System.out.println("----------------------------");



            switch (command) {
                case "1":
                    System.out.println(GameController.ANSI_YELLOW + "\nЗевс — верховный бог. Владыка богов и людей.\n" + GameController.ANSI_RESET);
                    break;
                case "2":
                    System.out.println(GameController.ANSI_YELLOW + "\nГера — царица богов. Покровительница брака. Жестокая, властная, ревнивая.\n" + GameController.ANSI_RESET);
                    break;
                case "3":
                    System.out.println(GameController.ANSI_YELLOW + "\nПосейдон — повелитель морей, а также землетрясений и лошадей.\n" + GameController.ANSI_RESET);
                    break;
                case "4":
                    System.out.println(GameController.ANSI_YELLOW + "\nАид — верховный бог подземного царства мёртвых.\n" + GameController.ANSI_RESET);
                    break;
                case "5":
                    System.out.println(GameController.ANSI_YELLOW + "\nАфродита — богиня любви и красоты, возникшая из морской пены.\n" + GameController.ANSI_RESET);
                    break;
                case "6":
                    System.out.println(GameController.ANSI_YELLOW + "\nГефест — бог огня, покровитель кузнечного ремесла. Сын Зевса и Геры.\n" + GameController.ANSI_RESET);
                    break;
                case "7":
                    System.out.println(GameController.ANSI_YELLOW + "\nДеметра — богиня плодородия, покровительница земледелия.\n" + GameController.ANSI_RESET);
                    break;
                case "8":
                    System.out.println(GameController.ANSI_YELLOW + "\nДионис — бог виноградарства и виноделия.\n" + GameController.ANSI_RESET);
                    break;
                case "9":
                    System.out.println(GameController.ANSI_YELLOW + "\nАполлон — бог, покровитель искусств, прорицатель и целитель.\n" + GameController.ANSI_RESET);
                    break;
                case "10":
                    System.out.println(GameController.ANSI_YELLOW + "\nАртемида — богиня охоты, лунная богиня, Великая матерь всех вещей и покровительница жизненных сил." + GameController.ANSI_RESET);
                    break;
                case "11":
                    System.out.println(GameController.ANSI_YELLOW + "\nГермес — вестник богов, бог торговли и прибыли, покровитель пастухов и путников.\n" + GameController.ANSI_RESET);
                    break;
                case "12":
                    System.out.println(GameController.ANSI_YELLOW + "\nАфина — богиня войны и победы, а также государственной мудрости, знаний, искусств и ремёсел.\n" + GameController.ANSI_RESET);
                    break;
                case "13":
                    System.out.println(GameController.ANSI_YELLOW + "\nАрес — бог войны.\n" + GameController.ANSI_RESET);
                    break;
                case "14":
                    mainMenu();
                    break;
                default:
                    System.out.println("---------------------------");
                    System.out.println(GameController.ANSI_RED + "\nКоманда не распознана, попробуй ещё" + GameController.ANSI_RESET);
            }
        } while(!command.equals("14"));
    }
}

