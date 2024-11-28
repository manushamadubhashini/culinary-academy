package org.culinary.academy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.util.Callback;
import org.culinary.academy.bo.BOFactory;
import org.culinary.academy.bo.custom.StudentBO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.dto.tm.StudentTM;
import org.culinary.academy.entity.Student;

import java.sql.Date;
import java.time.LocalDate;

public class studentFormController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colRDate;

    @FXML
    private TableColumn<?, ?> conName;


    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<StudentTM> tblStundet;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker txtRDate;

    StudentBO studentBO=(StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    public void initialize(){
        try {
            setCellValueFactory();
            initUI();
            loadTable();
            setTableValue();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error initializing form: " + e.getMessage()).show();
        }

    }

    private void setTableValue() {
        tblStundet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            //btnUpdate.setText(newValue != null ? "Update" : "Save");
            btnUpdate.setDisable(newValue == null);

            if (newValue != null) {
                txtID.setText(newValue.getSId());
                txtName.setText(newValue.getName());
                txtGender.setText(newValue.getGender());
                txtDOB.setValue(newValue.getDob().toLocalDate());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtRDate.setValue(newValue.getRegisterDate().toLocalDate());
            }
            txtID.setDisable(false);
            txtID.setEditable(false);
            txtName.setDisable(false);
            txtGender.setDisable(false);
            txtAddress.setDisable(false);
            txtDOB.setDisable(false);
            txtContact.setDisable(false);
            txtRDate.setDisable(false);
            btnUpdate.setDisable(false);
        });

    }


    private void loadTable() {
        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList();
        for (StudentDTO studentDTO : studentBO.getAll()) {
            studentTMS.add(new StudentTM(
                    studentDTO.getSId(),
                    studentDTO.getName(),
                    studentDTO.getGender(),
                    studentDTO.getAddress(),
                    studentDTO.getDob(),
                    studentDTO.getContact(),
                    studentDTO.getRegisterDate())
            );
        }
        tblStundet.setItems(studentTMS);
    }


    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("sId"));
        conName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

    }

    private void initUI() {
        txtID.clear();
       txtName.clear();
       txtGender.clear();
       txtAddress.clear();
       txtDOB.setValue(null);
       txtContact.clear();
       txtRDate.setValue(null);
       txtID.setDisable(true);
       txtName.setDisable(true);
        txtGender.setDisable(true);
        txtAddress.setDisable(true);
        txtDOB.setDisable(true);
        txtContact.setDisable(true);
        txtRDate.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);



    }
    @FXML
    void btnAddNewOnAction(ActionEvent event) {
        txtID.clear();
        txtName.clear();
        txtGender.clear();
        txtAddress.clear();
        txtDOB.setValue(null);
        txtContact.clear();
        txtRDate.setValue(null);
        txtID.setDisable(false);
        txtID.setEditable(false);
      txtName.setDisable(false);
      txtGender.setDisable(false);
      txtAddress.setDisable(false);
      txtDOB.setDisable(false);
      txtContact.setDisable(false);
      txtRDate.setDisable(false);
      btnSave.setDisable(false);
      generateNewID();

    }

    private void generateNewID() {
        txtID.setText(studentBO.getNextId());
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (studentBO.delete(txtID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"deleted successfully").show();
            loadTable();
            initUI();

        }else {
            new  Alert(Alert.AlertType.ERROR,"deleted not successfully").show();
        }


    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
      String sid=txtSearch.getText();
      Student studentDTO=studentBO.search(sid);
      if(studentDTO !=null) {
          txtID.setDisable(false);
          txtID.setEditable(false);
          txtName.setDisable(false);
          txtGender.setDisable(false);
          txtAddress.setDisable(false);
          txtDOB.setDisable(false);
          txtContact.setDisable(false);
          txtRDate.setDisable(false);
//          btnSave.setDisable(false);
          btnUpdate.setDisable(false);
          btnDelete.setDisable(false);

          txtID.setText(studentDTO.getStudentID());
          txtName.setText(studentDTO.getStudentName());
          txtGender.setText(studentDTO.getGender());
          txtAddress.setText(studentDTO.getAddress());
          txtDOB.setValue(studentDTO.getDob().toLocalDate());
          txtContact.setText(studentDTO.getContact());
          txtRDate.setValue(studentDTO.getRegister_date().toLocalDate());


      }else{
          new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
      }
      txtSearch.clear();


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (studentBO.save(new StudentDTO(txtID.getText(),txtName.getText(), txtGender.getText(), txtAddress.getText(),  Date.valueOf(txtDOB.getValue()), txtContact.getText(),Date.valueOf(txtRDate.getValue())))) {
           new Alert(Alert.AlertType.CONFIRMATION,"saved successfully").show();
           loadTable();
           initUI();


        } else {
           new  Alert(Alert.AlertType.ERROR,"saved not successfully").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if (studentBO.update(new StudentDTO(txtID.getText(),txtName.getText(), txtGender.getText(), txtAddress.getText(),  Date.valueOf(txtDOB.getValue()), txtContact.getText(),Date.valueOf(txtRDate.getValue())))) {
            setTableValue();
            loadTable();
            initUI();

            new Alert(Alert.AlertType.CONFIRMATION, "update successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"update not successfully").show();
        }


    }


}

