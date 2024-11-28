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
import org.culinary.academy.bo.custom.ProgramBO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.dto.tm.ProgramTM;
import org.culinary.academy.dto.tm.StudentTM;
import org.culinary.academy.entity.Program;


public class programFormController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnADDNew;

    @FXML
    private Button btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Line idLine;

    @FXML
    private Line idLine1;

    @FXML
    private Line idLine2;

    @FXML
    private Line idLine21;

    @FXML
    private TableView<ProgramTM> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPid;

    @FXML
    private TextField txtSearch;

    ProgramBO programBO=(ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);


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
        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            //btnUpdate.setText(newValue != null ? "Update" : "Save");
            btnUpdate.setDisable(newValue == null);

            if (newValue != null) {
                txtPid.setText(newValue.getPid());
                txtName.setText(newValue.getName());
                txtDuration.setText(String.valueOf(newValue.getDuration()));
                txtFee.setText(String.valueOf(newValue.getFee()));
            }
            txtPid.setDisable(false);
            txtName.setDisable(false);
            txtDuration.setDisable(false);
            txtFee.setDisable(false);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        });
    }

    private void initUI() {
        txtPid.clear();
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
        txtPid.setDisable(true);
        txtPid.setEditable(false);
        txtName.setDisable(true);
        txtDuration.setDisable(true);
        txtFee.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void generateNewID() {
        txtPid.setText(programBO.getNextId());
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

    }
    private void loadTable(){
        ObservableList<ProgramTM> programTMS = FXCollections.observableArrayList();
        for (ProgramDTO programDTO : programBO.getAll()) {
            programTMS.add(new ProgramTM(
                    programDTO.getPid(),
                    programDTO.getName(),
                    programDTO.getDuration(),
                    programDTO.getFee())
            );
        }
        tblProgram.setItems(programTMS);
    }
    @FXML
    void btnAddNewOnAction(ActionEvent event) {
        txtPid.clear();
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
        txtPid.setDisable(false);
        txtName.setDisable(false);
        txtDuration.setDisable(false);
        txtFee.setDisable(false);
        btnSave.setDisable(false);
        generateNewID();


    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (programBO.delete(txtPid.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"deleted successfully").show();
            loadTable();
            initUI();
        }else{
            new Alert(Alert.AlertType.ERROR, "deleted not successfully").show();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (programBO.save(new ProgramDTO(txtPid.getText(), txtName.getText(), txtDuration.getText(),txtFee.getText()))) {
            new Alert(Alert.AlertType.CONFIRMATION, "saved successfully").show();
            loadTable();
            initUI();


        } else {
            new Alert(Alert.AlertType.ERROR, "saved not successfully").show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id=txtSearch.getText();
        Program programDTO=programBO.search(id);
        if(programDTO!=null){
            txtPid.setDisable(false);
            txtName.setDisable(false);
            txtDuration.setDisable(false);
            txtFee.setDisable(false);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            txtPid.setText(programDTO.getProgramID());
            txtName.setText(programDTO.getProgramName());
            txtDuration.setText(programDTO.getDuration());
            txtFee.setText(programDTO.getFee());

        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid Id").show();
        }
        txtSearch.clear();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (programBO.update(new ProgramDTO(txtPid.getText(), txtName.getText(), txtDuration.getText(),txtFee.getText()))) {
            new Alert(Alert.AlertType.CONFIRMATION, "updated successfully").show();
            setTableValue();
            loadTable();
            initUI();


        } else {
            new Alert(Alert.AlertType.ERROR, "updated not successfully").show();
        }


    }

}
