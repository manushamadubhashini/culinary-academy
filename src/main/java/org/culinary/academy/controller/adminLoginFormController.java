package org.culinary.academy.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Schema;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.culinary.academy.bo.BOFactory;
import org.culinary.academy.bo.custom.UserBO;
import org.culinary.academy.dto.UserDTO;
import org.culinary.academy.util.Navigation;
import org.culinary.academy.util.Rout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminLoginFormController implements Initializable {


    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private Circle cirAdminImage;

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    boolean pw,user;
    UserBO userBO=(UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        validation();
        UserDTO isUser=userBO.getUser(new UserDTO(txtUserName.getText(),txtPassword.getText()));
        if(isUser!=null){
            new Alert(Alert.AlertType.ERROR,"nll").show();
            if (txtPassword.getText().equals(isUser.getPassword())){
                try {
                    Navigation.navigation(Rout.DASHBOARD,root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    private void validation() {
       if(txtUserName.getText().length()==0){
           txtUserName.setStyle("-fx-border-color: red;-fx-border-width: 1px");
           new animatefx.animation.Shake(txtUserName).play();

       }else{
           txtUserName.setStyle("-fx-border-color: #A9A9A9;-fx-border-width: 1px");
           new animatefx.animation.FadeIn(txtUserName).setSpeed(2.0).play();
       }
       if(txtPassword.getText().length()==0){
           txtPassword.setStyle("-fx-border-color: red;-fx-border-width: 1px");
           new animatefx.animation.Shake(txtPassword).play();
       }else{
           txtUserName.setStyle("-fx-border-color: #A9A9A9;-fx-border-width: 1px");
           new animatefx.animation.FadeIn(txtUserName).setSpeed(2.0).play();
       }

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            Navigation.navigation(Rout.SIGN_UP,root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
