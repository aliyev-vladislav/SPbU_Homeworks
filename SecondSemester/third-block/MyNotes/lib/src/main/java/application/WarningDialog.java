package application;

import javax.swing.*;
import java.awt.*;

class WarningDialog extends JDialog {
    private final JPanel panel;
    private final JLabel labelDialog;
    private final ImageIcon warningIcon;
    private final JButton okButton;

    public WarningDialog(JFrame frame) {
        super (frame, "Warning", true);
        labelDialog = new JLabel("Заметка с таким заголовком уже существует");
        warningIcon = new ImageIcon(new ImageIcon("lib/src/main/resources/images/4344882.png")
                .getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        labelDialog.setIcon(warningIcon);
        add(labelDialog, BorderLayout.CENTER);
        okButton = new JButton("OK");
        okButton.addActionListener(actionEvent -> setVisible(false));

        panel = new JPanel();
        panel.add(okButton);
        add(panel, BorderLayout.SOUTH);
        setSize(360, 160);
        setResizable(false);
    }

    public WarningDialog(JFrame frame, String message) {
        super (frame, "Warning", true);
        labelDialog = new JLabel(message);
        warningIcon = new ImageIcon(new ImageIcon("/home/vladislav/Изображения/4344882.png")
                .getImage()
                .getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        labelDialog.setIcon(warningIcon);
        labelDialog.setIconTextGap(30);
        add(labelDialog, BorderLayout.CENTER);
        okButton = new JButton("OK");
        okButton.addActionListener(actionEvent -> setVisible(false));

        panel = new JPanel();
        panel.add(okButton);
        add(panel, BorderLayout.SOUTH);
        setSize(360, 160);
        setResizable(false);
    }
}
