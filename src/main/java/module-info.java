module com.example.higherorlower {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;

    opens com.example.higherorlower to javafx.fxml;
    exports com.example.higherorlower;
}
