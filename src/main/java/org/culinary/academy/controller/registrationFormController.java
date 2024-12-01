package org.culinary.academy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import org.culinary.academy.bo.BOFactory;
import org.culinary.academy.bo.custom.RegistrationBO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.RegistrationDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.dto.tm.RegistrationTM;
import org.culinary.academy.dto.tm.StudentTM;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Registration;

import java.sql.Date;
import java.time.LocalDate;

public class registrationFormController {

    @FXML
    private JFXButton btnADDNew;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> clodate;

    @FXML
    private TableColumn<?, ?> closid;

    @FXML
    private ComboBox<String> cmbPid;

    @FXML
    private ComboBox<String> cmbSid;

    @FXML
    private TableColumn<?, ?> colPname;

    @FXML
    private TableColumn<?, ?> colRid;

    @FXML
    private TableColumn<?, ?> colSname;

    @FXML
    private TableColumn<?, ?> colpid;

    @FXML
    private Line idLine;

    @FXML
    private Line idLine1;

    @FXML
    private Line idLine11;

    @FXML
    private Line idLine2;

    @FXML
    private TableView<RegistrationTM> tblRegitration;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPname;

    @FXML
    private TextField txtRid;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSname;

    RegistrationBO registrationBO=(RegistrationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.REGISTRATION);


    public void initialize(){
        try {
            setCellValueFactory();
            initUI();
            loadTable();
            setTableValue();
            setSid();
            setPid();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error initializing form: " + e.getMessage()).show();
        }

    }

    private void setPid() {
        ObservableList<String> pids = FXCollections.observableArrayList();
        pids.addAll(registrationBO.getProgramId());
        cmbPid.setItems(pids);

    }

    private void setSid() {
        ObservableList<String> stdIdList = FXCollections.observableArrayList();
        stdIdList.addAll(registrationBO.getStudentId());
        cmbSid.setItems(stdIdList);

    }

    private void initUI() {
        txtRid.clear();
        txtDate.clear();
        txtSname.clear();
        txtPname.clear();
        txtRid.setDisable(true);
        cmbSid.setDisable(true);
        cmbPid.setDisable(true);
        txtDate.setDisable(true);
        txtSname.setDisable(true);
        txtPname.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);


    }

    private void setCellValueFactory() {
        colRid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        clodate.setCellValueFactory(new PropertyValueFactory<>("date"));
        closid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colpid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colSname.setCellValueFactory(new PropertyValueFactory<>("sName"));
        colPname.setCellValueFactory(new PropertyValueFactory<>("pName"));


    }

    private void loadTable() {
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();
        for (RegistrationDTO registrationDTO : registrationBO.getAll()) {
            registrationTMS.add(new RegistrationTM(
                    registrationDTO.getRid(),
                    registrationDTO.getDate(),
                    registrationDTO.getSid(),
                    registrationDTO.getPid(),
                    registrationDTO.getSName(),
                    registrationDTO.getPName())
            );
        }
        tblRegitration.setItems(registrationTMS);
    }



    private void setTableValue() {
        tblRegitration.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            //btnUpdate.setText(newValue != null ? "Update" : "Save");
            btnUpdate.setDisable(newValue == null);

            if (newValue != null) {
                txtRid.setText(newValue.getRid());
                txtDate.setText(String.valueOf(newValue.getDate()));
                cmbSid.setValue(newValue.getSid());
                cmbPid.setValue(newValue.getPid());
                txtSname.setText(newValue.getSName());
                txtPname.setText(newValue.getPName());
            }
            txtRid.setDisable(false);
            cmbSid.setDisable(false);
            cmbPid.setDisable(false);
            txtDate.setDisable(false);
            txtSname.setDisable(false);
            txtPname.setDisable(false);
            btnUpdate.setDisable(false);
        });


    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {
        txtRid.setDisable(false);
        cmbSid.setDisable(false);
        cmbPid.setDisable(false);
        txtDate.setDisable(false);
        txtSname.setDisable(false);
        txtPname.setDisable(false);
        btnSave.setDisable(false);

        txtRid.clear();
        cmbPid.setValue("");
        cmbSid.setValue("");
        txtDate.clear();
        txtSname.clear();
        txtPname.clear();
        generateNewId();

    }

    private void generateNewId() {

        LocalDate currentDate = LocalDate.now();
        txtDate.setText(currentDate.toString());
        txtRid.setText(registrationBO.getNextId());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (registrationBO.delete(txtRid.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "deleted successfully").show();
            loadTable();
            initUI();


        } else {
            new Alert(Alert.AlertType.ERROR, "deleted not successfully").show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (registrationBO.save(new RegistrationDTO(txtRid.getText(), Date.valueOf(txtDate.getText()),cmbSid.getValue(),cmbPid.getValue(),txtSname.getText(),txtPname.getText()))) {
            new Alert(Alert.AlertType.CONFIRMATION, "saved successfully").show();
            loadTable();
            initUI();


        } else {
            new Alert(Alert.AlertType.ERROR, "saved not successfully").show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (registrationBO.update(new RegistrationDTO(txtRid.getText(), Date.valueOf(txtDate.getText()),cmbSid.getValue(),cmbPid.getValue(),txtSname.getText(),txtPname.getText()))) {
            new Alert(Alert.AlertType.CONFIRMATION, "updated successfully").show();
            loadTable();
            initUI();


        } else {
            new Alert(Alert.AlertType.ERROR, "updated not successfully").show();
        }

    }

    @FXML
    void cmbPidOnAction(ActionEvent event) {
        String selectedProgramId = cmbPid.getValue();
        if (selectedProgramId != null && !selectedProgramId.isEmpty()) {
            ProgramDTO programDTO = registrationBO.getProgram(selectedProgramId);
            if (programDTO != null) {
                txtPname.setText(programDTO.getName());
            } else {
                txtPname.clear();
                new Alert(Alert.AlertType.ERROR, "Program not found").show();
            }
        }

    }

    @FXML
    void cmbSidOnAction(ActionEvent event) {
        String selectedStudentId = cmbSid.getValue();
        if (selectedStudentId != null && !selectedStudentId.isEmpty()) {
            StudentDTO studentDTO = registrationBO.getStudent(selectedStudentId);
            if (studentDTO != null) {
                txtSname.setText(studentDTO.getName());
            } else {
                txtSname.clear();
                new Alert(Alert.AlertType.ERROR, "Student not found").show();
            }
        }

    }

}
