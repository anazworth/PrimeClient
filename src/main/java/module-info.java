module com.anazworth.primeclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anazworth.primeclient to javafx.fxml;
    exports com.anazworth.primeclient;
}