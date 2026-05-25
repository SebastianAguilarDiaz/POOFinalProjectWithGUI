module com.github.sym {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.sym to javafx.fxml;
    exports com.github.sym;
}
