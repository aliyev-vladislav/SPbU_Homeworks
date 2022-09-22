package application;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

public class App extends JFrame implements Serializable {
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


    public App() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException exc) {
            //
        }
        this.setMinimumSize(new Dimension(150, 0));
        this.setBackground(DARK_GRAY);
        this.setSizeDefault();
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        labelNotesList = new JLabel();
        iconSearch = new ImageIcon
                (new ImageIcon("/home/vladislav/Изображения/4194718.png")
                        .getImage()
                        .getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        labelNotesList.setIcon(iconSearch);
        labelNotesList.setIconTextGap(10);
        labelNotesList.setFont(new Font("Dialog", BOLD, 20));

        searchPanel = new JPanel(new FlowLayout());
        searchPanel.setMaximumSize(new Dimension(this.widthFrame, 100));
        notesList = new JComboBox<>();
        notesList.setEditable(false);

        newNote = new JButton("Добавить заметку");
        newNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                notesList.addItem("Заметка");
            }
        });
        newNote.setForeground(Color.yellow);
        searchPanel.add(labelNotesList);
        searchPanel.add(notesList);
        searchPanel.add(newNote);

        logoPanel = new JPanel();
        buttonPanel = new JPanel();
        save = new JButton("Сохранить");
        delete = new JButton("Удалить");
        labelApp = new JLabel("My Notes");
        save.setForeground(WHITE);
        delete.setForeground(RED);
        buttonPanel.setLayout(new VerticalLayout());
        buttonPanel.setMaximumSize(new Dimension(this.widthFrame, 150));
        buttonPanel.add(save);
        buttonPanel.add(delete);

        notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.PAGE_AXIS));
        notePanel.setBackground(Color.darkGray);

        headerField = new JTextField("Заголовок",13);
        headerField.setMaximumSize(new Dimension(1000, 50));
        headerField.setFont(new Font("TimesRoman", ITALIC, 18));
        headerField.setBackground(Color.WHITE);
        headerField.setForeground(Color.BLACK);
        notePanel.add(headerField);

        noteField = new JTextArea(10, 27);
        noteField.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        noteField.setFont(new Font("Serif", Font.PLAIN, 18));
        noteField.setTabSize(10);
        noteField.setLineWrap(true);
        noteField.setBackground(Color.white);
        noteField.setForeground(Color.BLACK);
        notePanel.add(new JScrollPane(noteField));

        ImageIcon icon = new ImageIcon
                (new ImageIcon("/home/vladislav/Изображения/533782.png")
                        .getImage()
                        .getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        labelApp.setIcon(icon);
        labelApp.setIconTextGap(7);
        labelApp.setForeground(Color.white);
        labelApp.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        logoPanel.add(labelApp);
        logoPanel.setLayout(new FlowLayout());
        logoPanel.setMaximumSize(new Dimension(widthFrame, 100));
        logoPanel.setVisible(true);
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

    public void choiceNote() {
        notesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                notesList.getItemAt(notesList.getSelectedIndex());
            }
        });
    }
}
