package application;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DataBase {

    // Константа, в которой хранится адрес подключения к БД
    private static final String  CON_STR = "jdbc:sqlite:sqlite/data/dataApp.db";

    /*
     * Используем шаблон одиночка, чтобы не плодить множество
     * экземпляров класса DataBase
     */
    private static DataBase instance = null;
    public static synchronized DataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private final Connection connect;

    private DataBase() throws SQLException {

        // Регистрируем драйвер, с которым будем работать
        DriverManager.registerDriver(new JDBC());

        // Выполняем подключение к БД
        this.connect = DriverManager.getConnection(CON_STR);
    }

    public List<Note> getAllNotes() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connect.createStatement()){
            List<Note> notes = new ArrayList<>();

            // В resultSet будем хранить результат нашего запроса
            ResultSet resultSet = statement.executeQuery("SELECT header, note FROM Notes");

            // Проходимся по нашему resultSet и заносим данные в notes
            while (resultSet.next()) {
                notes.add(new Note(resultSet.getString("header"),
                                        resultSet.getString("note")));
            }
            return notes;
        } catch (SQLException e) {
            e.printStackTrace();

            // Если произошла ошибка - возращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public Note getElementDB(String header) {
        try (Statement statement = this.connect.createStatement()){

            // В resultSet будем хранить результат нашего запроса
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM Notes WHERE header = " + "'" + header + "';");

            // Проходимся по нашему resultSet и заносим данные в notes
            while (resultSet.next()) {
                return new Note(resultSet.getString("header"),
                        resultSet.getString("note"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Note("", "");
    }

    public List<String> getAllHeader() {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connect.createStatement()){
            List<String> header = new ArrayList<>();

            // В resultSet будем хранить результат нашего запроса
            ResultSet resultSet = statement.executeQuery("SELECT header FROM Notes");

            // Проходимся по нашему resultSet и заносим данные header
            while (resultSet.next()) {
                header.add(resultSet.getString("header"));
            }
            return header;
        } catch (SQLException e) {
            e.printStackTrace();

            // Если произошла ошибка - возращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление заметки в БД
    public void addNote(Note note) {

        // Создадим подготовленное выражение, чтобы избежать SQL - инъекций
        try (PreparedStatement statement = this.connect.prepareStatement(
                "INSERT INTO Notes(header, note) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, note.getHeader());
            statement.setObject(2, note.getNote());

            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление заметки по header
    public void deleteNote (String header) {
        try (PreparedStatement statement = this.connect.prepareStatement(
                "DELETE FROM Notes WHERE header = ?")) {
            statement.setString(1, header);

            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(String header, String note, String newHeader) {
        try (PreparedStatement statement = this.connect.prepareStatement(
                "UPDATE Notes SET header = ?, note = ? WHERE header = ?")) {
            statement.setString(1, newHeader);
            statement.setString(2, note);
            statement.setString(3, header);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDB() throws ClassNotFoundException, SQLException
    {
        try (Statement statement = this.connect.createStatement()) {
            statement.execute("CREATE TABLE if not exists Notes (header TEXT, note TEXT)");
        }
        System.out.println("Таблица создана или уже существует.");
    }
}
