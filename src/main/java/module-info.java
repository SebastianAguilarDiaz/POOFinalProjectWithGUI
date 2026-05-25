module com.github.sym {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.sym to javafx.fxml;
    exports com.github.sym;
    opens com.github.sym.UnoBasicGame to javafx.fxml;
    exports com.github.sym.UnoBasicGame;
}
