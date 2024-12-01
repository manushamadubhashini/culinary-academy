package org.culinary.academy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Button btnStudent;

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.STUDENT, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnHomeOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.HOME, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminLoginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("login Form");
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
        try {
            Navigation.navigation(Rout.REGISTRATION, bodyRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
