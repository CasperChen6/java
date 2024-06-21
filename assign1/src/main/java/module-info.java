module com.example.assign1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    opens com.example.assign1 to javafx.fxml;
    exports com.example.assign1;
}