module com.gravity {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gravity.controllers to javafx.fxml;
    exports com.gravity;
}
