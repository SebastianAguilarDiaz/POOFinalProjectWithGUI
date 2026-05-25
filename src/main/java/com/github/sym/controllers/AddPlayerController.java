package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPlayerController {


    @FXML
    TextField textField;

    public void addPlayerFunction() {
        

    }

    public void returnFunction() throws IOException{
        App.setRoot("views/Menu");
    }

}
