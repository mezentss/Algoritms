module com.example.algoritms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algoritms to javafx.fxml;
    exports com.example.algoritms;
}