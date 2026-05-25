package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;

import javafx.fxml.FXML;


public class MenuController {
    
    

    @FXML
    public void startGameFunction(){
        System.out.println("Hola");
    }



    @FXML
    public void displayStatisticsPerGameFunction(){
        System.out.println("2");
    };

    @FXML
    public void displayStatisticsPerPlayerFunction(){
        System.out.println("3");
    };
    @FXML
    public void addPlayerFunction() throws IOException{
        
        App.setRoot("views/AddPlayer");
    };





}
