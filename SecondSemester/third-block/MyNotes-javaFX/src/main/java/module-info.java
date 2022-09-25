module org.openjfx.mynotesjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens org.openjfx.mynotesjavafx to javafx.fxml;
    exports org.openjfx.mynotesjavafx;
    exports org.openjfx.mynotesjavafx.controller;
    opens org.openjfx.mynotesjavafx.controller to javafx.fxml;
    exports org.openjfx.mynotesjavafx.model;
    opens org.openjfx.mynotesjavafx.model to javafx.fxml;
}