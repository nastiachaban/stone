module com.example.stonecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.stonecollection to javafx.fxml;
    exports com.example.stonecollection;
}