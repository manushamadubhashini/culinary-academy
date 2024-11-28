package org.culinary.academy.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane root;

    public static void navigation(Rout rout, AnchorPane root) throws IOException {
        Navigation.root = root;
        Navigation.root.getChildren().clear();
        Stage window = (Stage) Navigation.root.getScene().getWindow();
        switch (rout) {
            case LOGIN:
                initUi("adminLoginForm.fxml");
                break;
            case SIGN_UP:
                initUi("adminRegisterForm.fxml");
                break;
            case DASHBOARD:
                initUi("adminDashboardForm.fxml");
                break;
            case PROGRAM:
                initUi("programForm.fxml");
                break;
        }
    }

    private static void initUi(String location) throws IOException {
        Navigation.root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/view/" + location))));
    }
}