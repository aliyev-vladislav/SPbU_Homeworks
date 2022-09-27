package application.controller;

import application.model.Note;
import sqlite.DataBase;

import java.sql.SQLException;
import java.util.Scanner;

public class BaseController {
    private String noteField = "";
    private String headerField = "";
    private DataBase db;
    private Scanner scanner;
    private String command = "";

    public BaseController() {
        try {
            db = DataBase.getInstance();
            db.createDB();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("My Notes");
        scanner = new Scanner(System.in);
        do {
            framing();
            System.out.println("[ 1 ] - Создать заметку");
            System.out.println("[ 2 ] - Найти заметку");
            System.out.println("[ 3 ] - Выйти");
            System.out.print("Введите команду [1-3] >> ");
            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
            }
            switch (command) {
                case "1" -> createNote();
                case "2" -> searchNote();
                case "3" -> exitApp();
                default -> showInputError();
            }
        } while(!command.equals("4"));
    }

    public void createNote() {
        this.inputHeader();
        this.inputNote();
        this.saveAndDeleteMenu();
    }

    public void searchNote() {
        System.out.print("\nВведите имя заметки >> ");
        this.headerField = scanner.nextLine();
        for (var note : db.getAllNotes()) {
            if (note.getHeader().equals(headerField)) {
                System.out.println();
                framing();
                System.out.println("Заголовок: " + note.getHeader());
                System.out.print("Содержимое:\n" + note.getNote());
                framing();
                saveAndDeleteMenu();
                return;
            }
        }
        System.out.println("\nЗаметка не найдена, проверьте правильность ввода\n");
    }

    public void inputHeader() {
        while (true) {
            System.out.print("\nВведите имя заметки >> ");
            this.headerField = scanner.nextLine();
            if (isHeader())
                return;
        }
    }

    public void inputNote() {
        System.out.println("Начните печатать (для завершение ввода введите #exit):");
        while (true) {
            var input = scanner.nextLine();
            if (input.equals("#exit"))
                break;
            noteField += input + "\n";
        }
    }

    public boolean isHeader() {
        if (headerField.equals("")) {
            System.out.println("\nЗаголовок не может быть пустым\n");
            return false;
        }

        for (var note : db.getAllNotes()) {
            if (note.getHeader().equals(headerField)) {
                System.out.println("\nЗаметка с таким заголовком уже существует");
                return false;
            }
        }
        return true;
    }

    public void saveAndDeleteMenu() {
        while (true) {
            System.out.println("[ 1 ] - Сохранить заметку");
            System.out.println("[ 2 ] - Удалить заметку");
            System.out.print("Введите команду [1-2] >> ");
            scanner = new Scanner(System.in);
            command = scanner.nextLine();
            switch (command) {
                case "1":
                    saveNote();
                    return;

                case "2":
                    deleteNote();
                    return;

                default:
                    showInputError();
            }
        }
    }

    public void saveNote() {
        db.addNote(new Note(headerField, noteField));
        System.out.println("\nЗаметка сохранена!\n");
    }

    public void deleteNote() {
        for (var note : db.getAllNotes()) {
            if (note.getHeader().equals(headerField)) {
                db.deleteNote(headerField);
                System.out.println("\nЗаметка удалена!\n");
                return;
            }
        }
    }

    public void showInputError() {
        System.out.println("\nКоманда не разпознана, проверьте правильность ввода\n");
    }

    public static void framing() {
        System.out.println("--------------------------------------");
    }
    public void exitApp() {
        System.exit(0);
    }
}
