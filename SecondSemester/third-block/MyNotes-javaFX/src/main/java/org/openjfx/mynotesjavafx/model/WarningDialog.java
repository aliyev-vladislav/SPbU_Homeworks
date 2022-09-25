package org.openjfx.mynotesjavafx.model;

import javafx.scene.control.Alert;

public class WarningDialog extends Alert {
    public WarningDialog(AlertType alertType) {
        super(alertType);
        this.setTitle("Warning");
    }

    public void showDialog(String message) {
        this.setContentText(message);
        this.showAndWait();
    }
}
