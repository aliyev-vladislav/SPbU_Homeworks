package application;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

public class Frame extends JFrame {
    private int widthFrame;
    private int heightFrame;
    private final JPanel logoPanel;
    private final JPanel notePanel;
    private final JPanel buttonPanel;
    private final JPanel searchPanel;
    private JComboBox<String> notesList;
    private final JButton newNote;
    private final JButton save;
    private final JButton delete;
    private final JLabel labelApp;
    private final JLabel labelNotesList;
    private final ImageIcon iconSearch;
    private JTextArea noteField;
    private JTextField headerField;
    private WarningDialog dialog;
    private DataBase db;


    public Frame() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException exc) {
            //
        }
        try {
            db = DataBase.getInstance();
            db.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setMinimumSize(new Dimension(200, 0));
        this.setBackground(DARK_GRAY);
        this.setSizeDefault();
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        labelNotesList = new JLabel();
        iconSearch = new ImageIcon
                (new ImageIcon("lib/src/main/resources/images/4194718.png")
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        labelNotesList.setIcon(iconSearch);
        labelNotesList.setIconTextGap(10);
        labelNotesList.setFont(new Font("Dialog", BOLD, 20));

        searchPanel = new JPanel(new FlowLayout());
        searchPanel.setMaximumSize(new Dimension(this.widthFrame, 100));
        notesList = new JComboBox<>();
        notesList.setEditable(false);
        notesList.setPreferredSize(new Dimension(150, 30));
        this.updateListOfNotes();

        newNote = new JButton("Новая заметка");
        newNote.addActionListener(new ButtonNewNoteListener());
        newNote.setForeground(YELLOW);
        searchPanel.add(labelNotesList);
        searchPanel.add(notesList);
        searchPanel.add(newNote);

        logoPanel = new JPanel();
        buttonPanel = new JPanel();
        save = new JButton("Сохранить");
        save.addActionListener(new ButtonSaveListener());

        delete = new JButton("Удалить");
        delete.addActionListener(new ButtonDeleteListener());
        labelApp = new JLabel("My Notes");
        save.setForeground(WHITE);
        delete.setForeground(RED);
        buttonPanel.setLayout(new VerticalLayout());
        buttonPanel.setMaximumSize(new Dimension(this.widthFrame, 150));
        buttonPanel.add(save);
        buttonPanel.add(delete);

        notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.PAGE_AXIS));
        notePanel.setBackground(DARK_GRAY);

        headerField = new JTextField("Заголовок",13);
        headerField.setMaximumSize(new Dimension(1000, 50));
        headerField.setFont(new Font("TimesRoman", ITALIC, 18));
        headerField.setBackground(WHITE);
        headerField.setForeground(BLACK);
        notePanel.add(headerField);

        noteField = new JTextArea(10, 27);
        noteField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        noteField.setFont(new Font("Serif", Font.PLAIN, 18));
        noteField.setTabSize(10);
        noteField.setLineWrap(true);
        noteField.setBackground(WHITE);
        noteField.setForeground(BLACK);
        notePanel.add(new JScrollPane(noteField));

        ImageIcon icon = new ImageIcon
                (new ImageIcon("lib/src/main/resources/images/533782.png")
                        .getImage()
                        .getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        labelApp.setIcon(icon);
        labelApp.setIconTextGap(7);
        labelApp.setForeground(WHITE);
        labelApp.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        logoPanel.add(labelApp);
        logoPanel.setLayout(new FlowLayout());
        logoPanel.setMaximumSize(new Dimension(widthFrame, 100));
        logoPanel.setVisible(true);

        notesList.addActionListener(new ChoiceNote());
        this.add(logoPanel);
        this.add(searchPanel);
        this.add(notePanel);
        this.add(buttonPanel);
    }

    public void setSizeDefault() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        widthFrame = screenSize.width;
        heightFrame = screenSize.height;
        this.setSize((int) (widthFrame * 0.2), (int) (heightFrame * 0.55));
    }

    private void updateListOfNotes() {
        notesList.addItem("");
        for (var note : db.getAllNotes()) {
            notesList.addItem(note.getHeader());
        }
    }

    private class ChoiceNote implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            var choice = notesList.getItemAt(notesList.getSelectedIndex());
            var header = db.getElementDB(choice).getHeader();
            var note = db.getElementDB(choice).getNote();
            headerField.setText(header);
            noteField.setText(note);
        }
    }

    private class ButtonNewNoteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            notesList.setSelectedIndex(0);
            headerField.setText("Заголовок");
            noteField.setText("");
        }
    }

    private class ButtonSaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if (headerField.getText().equals("") || headerField.getText() == null) {
                if (dialog == null)
                    dialog = new WarningDialog(Frame.this
                            , "       Заголовок не может быть пустым");
                dialog.setVisible(true);
                return;
            }

            for (var note : db.getAllNotes()) {
                if (note.getHeader().equals
                        (notesList.getItemAt(notesList.getSelectedIndex()))) {
                    db.updateNote(note.getHeader(), noteField.getText(), headerField.getText());
                    var index = notesList.getSelectedIndex();
                    notesList.removeAllItems();
                    updateListOfNotes();
                    notesList.setSelectedIndex(index);
                    return;
                }
            }

            for (var note : db.getAllNotes()) {
                if (note.getHeader().equals(headerField.getText())) {
                    if (dialog == null)
                        dialog = new WarningDialog(Frame.this,
                                "Заметка с таким заголовком уже существует");
                    dialog.setVisible(true);
                    return;
                    }
                }
            notesList.addItem(headerField.getText());
            db.addNote(new Note(headerField.getText(), noteField.getText()));
            notesList.setSelectedItem(headerField.getText());
        }
    }

    private class ButtonDeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (notesList.getSelectedIndex() == 0) {
                return;
            }
            db.deleteNote(notesList.getItemAt(notesList.getSelectedIndex()));
            notesList.removeItemAt(notesList.getSelectedIndex());
            notesList.setSelectedIndex(0);
        }
    }

}
