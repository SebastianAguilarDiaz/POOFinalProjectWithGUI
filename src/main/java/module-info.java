module com.github.sym {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.sym to javafx.fxml;
    exports com.github.sym;
    opens com.github.sym.unobasicgame to javafx.fxml;
    exports com.github.sym.unobasicgame;
    opens com.github.sym.controllers to javafx.fxml;
    exports com.github.sym.controllers;
}
