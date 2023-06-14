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
import lk.ijse.petcarecenter.bo.custom.MedicineBO;
import lk.ijse.petcarecenter.model.MedicineDTO;
import lk.ijse.petcarecenter.model.tdm.MedicineTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineManagementFormController implements Initializable {
    public Button btnBack;
    public TextField txtMedicineId;
    public TextField txtMedicineName;
    public TextField txtQuantity;
    public AnchorPane root;
    public Button btnSave;
    public TableColumn<?,?> colMedicineId;
    public TableColumn<?,?> colMedicineName;
    public TableColumn<?,?> colQuantity;
    public Button btnUpdate;
    public Button btnDelect;
    public TableView tblMedicine;
    public TextField txtPrice;
    public TableColumn colPrice;
    MedicineBO medicineBO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEDICINE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        generateNextOrderId();
        setCellValueFactory();
    }
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        if (backButtonController.backButton == 1) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/managerDashBoard_form.fxml"))));
            stage.setTitle("Manage");
            stage.centerOnScreen();
            stage.show();
        } else {
            System.out.println("employee");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/receptionistDashBoard_form.fxml"))));
            stage.setTitle("Manage");
            stage.centerOnScreen();
            stage.show();
        }

    }

    public void txtMedicineIdOnAction(ActionEvent actionEvent) {
        try {
            MedicineDTO medicineDTO = medicineBO.searchMedicineId(txtMedicineId.getText());
            if (medicineDTO != null) {
                txtMedicineId.setText(medicineDTO.getMedicineID());
                txtMedicineName.setText(medicineDTO.getName());
                txtPrice.setText(String.valueOf(medicineDTO.getPrice()));
                txtQuantity.setText(String.valueOf(medicineDTO.getQuantity()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    public void txtMedicineNameOnAction(ActionEvent actionEvent) {

    }

    public void txtQuantityOnAction(ActionEvent actionEvent) {

    }
    private void generateNextOrderId() {
        try {
            String id = medicineBO.getNextOrderId();
            txtMedicineId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtMedicineId.getText();
        boolean isTrue= medicineBO.seachValiedID(id);
        if (isTrue==true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Medicine ID already added:)").show();
        }
        if (txtMedicineId.getText().isBlank()||txtMedicineName.getText().isBlank()||txtQuantity.getText().isBlank()||txtPrice.getText().isBlank()) {
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        }
        if (isTrue==false){
            try {
                String name = txtMedicineName.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                MedicineDTO medicineDTO = new MedicineDTO(id, name, quantity, price);
                boolean isSaved = medicineBO.saveMedicine(medicineDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Medicine added :)").show();
                }
            }catch (Exception e){

            }
        }
        setCellValueFactory();
        getAll();
        generateNextOrderId();
        txtMedicineName.clear();
        txtQuantity.clear();
        txtPrice.clear();
    }



    private void getAll() {
        try {
            ObservableList<MedicineTM> obList = FXCollections.observableArrayList();
            List<MedicineDTO> cusList = medicineBO.getAllMedicine();

            for (MedicineDTO medicineDTO : cusList) {
                obList.add(new MedicineTM(
                        medicineDTO.getMedicineID(),
                        medicineDTO.getName(),
                        medicineDTO.getQuantity(),
                        medicineDTO.getPrice()
                ));
            }
            tblMedicine.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setCellValueFactory() {
        colMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtMedicineId.getText().isEmpty()||txtMedicineName.getText().isEmpty()||txtQuantity.getText().isEmpty()||txtPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        } else {
            String id = txtMedicineId.getText();
            String name = txtMedicineName.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(txtPrice.getText());

            MedicineDTO medicineDTO =new MedicineDTO(id,name,quantity,price);
            boolean isSaved= medicineBO.updateMedicine(medicineDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!! :)").show();
            }
            generateNextOrderId();
            setCellValueFactory();
            getAll();
            txtMedicineName.clear();
            txtQuantity.clear();
            txtPrice.clear();
        }

    }
    public void btnDelectOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtMedicineId.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "MedicineID Field Are Required:)").show();
        } else {
            String id = txtMedicineId.getText();
            boolean isSaved= medicineBO.delectMedicine(id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "deletd! :)")
                        .show();
            }
            generateNextOrderId();
            setCellValueFactory();
            getAll();
            txtPrice.clear();
            txtMedicineName.clear();
            txtQuantity.clear();
        }

    }
}
