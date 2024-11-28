package org.culinary.academy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.culinary.academy.util.Navigation;
import org.culinary.academy.util.Rout;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnSetting;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane bodyRoot;


    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }
    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }
    @FXML
    void btnProgramOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.PROGRAM, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {

    }

}
