package org.openjfx.mynotesjavafx.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.openjfx.mynotesjavafx.model.DataBase;
import org.openjfx.mynotesjavafx.model.Note;
import org.openjfx.mynotesjavafx.model.WarningDialog;

public class BaseController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Pane buttonPanel;
    @FXML
    private Button delete;
    @FXML
    private TextField headerField;
    @FXML
    private Pane headerPanel;
    @FXML
    private ImageView icon;
    @FXML
    private ImageView iconSearch;
    @FXML
    private Label labelApp;
    @FXML
    private Pane logoPanel;
    @FXML
    private Button newNote;
    @FXML
    private TextArea noteField;
    @FXML
    private BorderPane notePanel;
    @FXML
    private ComboBox<String> notesList;
    @FXML
    private Button save;
    @FXML
    private Pane searchPanel;
    private WarningDialog dialog;
    private DataBase db;

    @FXML
    void initialize() {
        try {
            db = DataBase.getInstance();
            db.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        updateListOfNotes();
        headerField.setText("Header");

        notesList.setOnAction(event -> {
            var choice = notesList.getSelectionModel().getSelectedItem();
            var header = db.getElementDB(choice).getHeader();
            var note = db.getElementDB(choice).getNote();
            headerField.setText(header);
            noteField.setText(note);
        });

        newNote.setOnAction(event -> {
            notesList.getSelectionModel().select(0);
            headerField.setText("Header");
            noteField.setText("Start typing");
        });


        save.setOnAction(event -> {
            if (headerField.getText().equals("") || headerField.getText() == null) {
                if (dialog == null)
                    dialog = new WarningDialog(Alert.AlertType.WARNING);
                dialog.showDialog("The header cannot be empty");
                return;
            }

            for (var note : db.getAllNotes()) {
                if (note.getHeader().equals
                        (notesList.getSelectionModel().getSelectedItem())) {
                    db.updateNote(note.getHeader(), noteField.getText(), headerField.getText());
                    var index = notesList.getSelectionModel().getSelectedIndex();
                    notesList.getItems().clear();
                    updateListOfNotes();
                    notesList.getSelectionModel().select(index);
                    return;
                }
            }

            for (var note : db.getAllNotes()) {
                if (note.getHeader().equals(headerField.getText())) {
                    if (dialog == null)
                        dialog = new WarningDialog(Alert.AlertType.WARNING);
                    dialog.showDialog("A note with this header already exists");
                    return;
                }
            }

            notesList.getItems().add(headerField.getText());
            db.addNote(new Note(headerField.getText(), noteField.getText()));
            notesList.getSelectionModel()
                     .select(headerField.getText());
        });

        delete.setOnAction(event -> {

            if (notesList.getSelectionModel()
                         .getSelectedIndex() == 0) {
                return;
            }
            db.deleteNote(notesList.getSelectionModel().getSelectedItem());
            notesList.getSelectionModel().clearSelection();
            updateListOfNotes();
            notesList.getSelectionModel().select(0);
        });
    }


    public void updateListOfNotes() {
        notesList.getItems().clear();
        notesList.getItems().add("");
        for (var note : db.getAllNotes()) {
            notesList.getItems().add(note.getHeader());
        }
        notesList.getSelectionModel().selectFirst();
    }

}

