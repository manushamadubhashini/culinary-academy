package org.culinary.academy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.culinary.academy.bo.BOFactory;
import org.culinary.academy.bo.custom.UserBO;
import org.culinary.academy.dto.UserDTO;
import org.culinary.academy.util.Navigation;
import org.culinary.academy.util.Rout;

import java.io.IOException;

public class adminRegisterFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private PasswordField txtConFirmPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUseName;

    @FXML
    private TextField txtUserType;

    UserBO userBO=(UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
//        boolean isValidation=validation();
//
//        if(!isValidation){
//            return;
//        }
//        UserDTO userDTO=new UserDTO(txtUseName.getText(),txtPassword.getText(),txtUserType.getText());
//        boolean isSaved=userBO.save(userDTO);
//        if (isSaved){
//            txtUseName.setText("");
//            txtPassword.setText("");
//            txtConFirmPassword.setText("");
//            txtUserType.setText("");
//            Navigation.navigation(Rout.LOGIN,root);
//            new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
//
//        }else{
//            new Alert(Alert.AlertType.ERROR,"Saved Not Successfully").show();
//        }
//
//    }
        try {
            boolean isValidation = validation();

            if (!isValidation) {
                return;
            }

            UserDTO userDTO = new UserDTO(txtUseName.getText(), txtPassword.getText(), txtUserType.getText());
            System.out.println("Attempting to save user: " + userDTO); // Debug print

            boolean isSaved = userBO.save(userDTO);
            System.out.println("Save operation returned: " + isSaved); // Debug print

            if (isSaved) {
                txtUseName.setText("");
                txtPassword.setText("");
                txtConFirmPassword.setText("");
                txtUserType.setText("");
                Navigation.navigation(Rout.LOGIN, root);
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Saved Not Successfully").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred: " + e.getMessage()).show();
        }
    }

    private boolean validation() {
       boolean isUsername=txtUseName.getText().matches("^[a-zA-Z0-9._]{3,}$");
       if(!isUsername){
           txtUseName.setStyle("-fx-border-color: red;-fx-border-width: 1px");
           new animatefx.animation.Shake(txtUseName).play();
           return false;
       }
       txtUseName.setStyle("-fx-border-color: white;-fx-border-width: 1px");
       new animatefx.animation.FadeIn(txtUseName).setSpeed(2.0).play();

       boolean isPassword=txtPassword.getText().matches("^[a-zA-Z0-9._]{3,}$");
       if(!isPassword){
           txtPassword.setStyle("-fx-border-color: red;-fx-border-width: 1px");
           new animatefx.animation.Shake(txtPassword).play();
           return false;
       }
       txtPassword.setStyle("-fx-border-color: white;-fx-border-width: 1px");
       new animatefx.animation.FadeIn(txtPassword).setSpeed(2.0).play();

      boolean isConfirmPassword=txtConFirmPassword.getText().matches("^[a-zA-Z0-9._]{3,}$");
      if(!isConfirmPassword){
          txtConFirmPassword.setStyle("-fx-border-color: red;-fx-border-width: 1px");
          new animatefx.animation.Shake(txtConFirmPassword).play();
          return false;
      }
      txtConFirmPassword.setStyle("-fx-border-color: white;-fx-border-width: 1px");
        new animatefx.animation.FadeIn(txtConFirmPassword).setSpeed(2.0).play();

        if(!txtPassword.getText().equals(txtConFirmPassword.getText())){
            txtConFirmPassword.setStyle("-fx-border-color: red;-fx-border-width: 1px");
            new animatefx.animation.Shake(txtConFirmPassword).play();
            return false;

        }
        txtConFirmPassword.setStyle("-fx-border-color: white;-fx-border-width: 1px");
        new animatefx.animation.FadeIn(txtConFirmPassword).setSpeed(2.0).play();

        boolean isUserType=txtUserType.getText().matches("^[a-zA-Z0-9._]{3,}$");
        if(!isUserType){
            txtUserType.setStyle("-fx-border-color: red;-fx-border-width: 1px");
            new animatefx.animation.Shake(txtUserType).play();
            return false;
        }
        txtUserType.setStyle("-fx-border-color: white;-fx-border-width: 1px");
        new animatefx.animation.FadeIn(txtUserType).setSpeed(2.0).play();

       return true;
    }

}
