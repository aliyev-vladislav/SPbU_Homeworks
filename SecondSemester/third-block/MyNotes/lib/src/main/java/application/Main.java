package application;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main implements Serializable {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new App();
            frame.setTitle("My Notes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
