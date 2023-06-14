package lk.ijse.petcarecenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.petcarecenter.bo.BOFactory;
import lk.ijse.petcarecenter.bo.custom.DoctorBO;
import lk.ijse.petcarecenter.model.DoctorDTO;
import lk.ijse.petcarecenter.model.tdm.DoctorTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorManagementFormController implements Initializable {
    public AnchorPane root;
    public Button btnBack;
    public TextField txtDoctorId;
    public TextField txtDoctorName;
    public TextField txtContact;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelect;
    public TableView tblDoctor;
    public TableColumn colDoctorId;
    public TableColumn colName;
    public TableColumn colContact;

    DoctorBO doctorDAO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DOCTOR);

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        if(backButtonController.backButton==1){
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/managerDashBoard_form.fxml"))));
            stage.setTitle("Manage");
            stage.centerOnScreen();
            stage.show();
        }else{
            System.out.println("employee");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/receptionistDashBoard_form.fxml"))));
            stage.setTitle("Manage");
            stage.centerOnScreen();
            stage.show();
        }
    }
    private void generateNextDoctorId() {
        try {
            String id = doctorDAO.getNextDoctorId();
            txtDoctorId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void txtDoctorIdOnAction(ActionEvent actionEvent) {
        try {
            DoctorDTO doctorDTO = doctorDAO.searchDoctors(txtDoctorId.getText());
            if (doctorDTO != null) {
                txtDoctorId.setText(doctorDTO.getDoctorID());
                txtDoctorName.setText(doctorDTO.getName());
                txtContact.setText(String.valueOf(doctorDTO.getPhoneNumber()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    public void txtDoctorNameOnAction(ActionEvent actionEvent) {

    }

    public void txtContactOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String id=txtDoctorId.getText();
        boolean isTrue= doctorDAO.seachValiedDoctorID(id);
        if (isTrue==true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Doctor ID already added :)").show();
        }

        if (txtDoctorId.getText().isEmpty()||txtDoctorName.getText().isEmpty()||txtContact.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        }
        if(isTrue==false){
            try {
                String name = txtDoctorName.getText();
                int contact = Integer.parseInt(txtContact.getText());

                DoctorDTO doctorDTO = new DoctorDTO(id, name, contact);
                boolean isSaved = doctorDAO.saveDoctors(doctorDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appointment added :)").show();
                }
            }catch (Exception e){

            }
        }
        generateNextDoctorId();
        txtDoctorName.clear();
        txtContact.clear();
        setCellValueFactory();
        getAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {

        if (txtDoctorId.getText().isEmpty()||txtDoctorName.getText().isEmpty()||txtContact.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        } else {
            String id = txtDoctorId.getText();
            String name = txtDoctorName.getText();
            int contact = Integer.parseInt(txtContact.getText());

            DoctorDTO doctorDTO =new DoctorDTO(id,name,contact);
            boolean isSaved= doctorDAO.updateDoctorDetails(doctorDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Updated :)").show();
            }
            generateNextDoctorId();
            txtDoctorName.clear();
            txtContact.clear();
            setCellValueFactory();
            getAll();
        }

    }

    public void btnDelectOnAction(ActionEvent actionEvent) throws SQLException {
        if(txtDoctorId.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "Doctor ID Field Are Required:)").show();
        }else {
            String id = txtDoctorId.getText();
            boolean isSaved= doctorDAO.deleteDoctor(id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deletd :)").show();
            }
            generateNextDoctorId();
            txtDoctorName.clear();
            txtContact.clear();
            setCellValueFactory();
            getAll();
        }
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        generateNextDoctorId();
    }
    private void getAll() {
        try {
            ObservableList<DoctorTM> obList = FXCollections.observableArrayList();
            List<DoctorDTO> cusList = doctorDAO.getAllDoctors();

            for (DoctorDTO doctorDTO : cusList) {
                obList.add(new DoctorTM(
                        doctorDTO.getDoctorID(),
                        doctorDTO.getName(),
                        doctorDTO.getPhoneNumber()
                ));
            }
            tblDoctor.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setCellValueFactory() {
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }
}
