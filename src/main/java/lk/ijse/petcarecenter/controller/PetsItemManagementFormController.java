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
import lk.ijse.petcarecenter.bo.custom.PetItemBO;
import lk.ijse.petcarecenter.model.PetItemDTO;
import lk.ijse.petcarecenter.model.tdm.PetItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PetsItemManagementFormController implements Initializable {
    public AnchorPane root;
    public Button btnBack;
    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtQuantity;
    public Button btnSave;
    public TableView tblPetItem;
    public TableColumn collemCode;
    public TableColumn colName;
    public TableColumn colQuantity;
    public Button btnDelete;
    public TextField txtPrice;
    public TableColumn colPrice;
    public Button btnUpdate;
    PetItemBO petItemBO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PET_ITEM);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        generateNextItemId();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        if(backButtonController.backButton==1){
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/managerDashBoard_form.fxml"))));
            stage.setTitle("Manager Dash Board");
            stage.centerOnScreen();
            stage.show();
        }else{
            System.out.println("employee");
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/receptionistDashBoard_form.fxml"))));
            stage.setTitle("Receptionist Dash Board");
            stage.centerOnScreen();
            stage.show();
        }

    }
    private void generateNextItemId() {
        try {
            String id = petItemBO.getNextItemId();
            txtItemCode.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void txtItemCodeOnAction(ActionEvent actionEvent) {
        try {
            PetItemDTO petItemDTO = petItemBO.searchPetItemId(txtItemCode.getText());
            if (petItemDTO != null) {
                txtItemCode.setText(petItemDTO.getItemCode());
                txtItemName.setText(petItemDTO.getName());
                txtPrice.setText(String.valueOf(petItemDTO.getPrice()));
                txtQuantity.setText(String.valueOf(petItemDTO.getQuantity()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }

    }

    public void txtItemNameOnAction(ActionEvent actionEvent) {

    }

    public void txtQuantityOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {

        String id = txtItemCode.getText();
        boolean isTrue= petItemBO.seachValiedItemID(id);
        if (isTrue==true) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet Item Code already added:)").show();
        }

        if(txtItemCode.getText().isEmpty()||txtItemName.getText().isEmpty()||txtQuantity.getText().isEmpty()||txtPrice.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        }
        if(isTrue==false){
            try {
                String name = txtItemName.getText();
                int quantity = Integer.parseInt(txtQuantity.getText());
                double price = Double.parseDouble(txtPrice.getText());

                PetItemDTO petItemDTO = new PetItemDTO(id, name, price, quantity);
                boolean isSaved = petItemBO.savePetItem(petItemDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Pet Item added :)").show();
                }
            }catch (Exception e){

            }
        }
        setCellValueFactory();
        getAll();
        generateNextItemId();
        txtItemName.clear();
        txtQuantity.clear();
        txtPrice.clear();
    }

    private void getAll() {
        try {
            ObservableList<PetItemTM> obList = FXCollections.observableArrayList();
            List<PetItemDTO> cusList = petItemBO.getAllPetItem();

            for (PetItemDTO petItemDTO : cusList) {
                obList.add(new PetItemTM(
                        petItemDTO.getItemCode(),
                        petItemDTO.getName(),
                        petItemDTO.getPrice(),
                        petItemDTO.getQuantity()
                ));
            }
            tblPetItem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setCellValueFactory() {
        collemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        if(txtItemCode.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        }else {
            String id = txtItemCode.getText();

            boolean isSaved= petItemBO.deletePetItem(id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet Item deletd:)").show();
            }

            setCellValueFactory();
            getAll();
            generateNextItemId();
            txtItemName.clear();
            txtQuantity.clear();
            txtPrice.clear();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        if (txtItemCode.getText().isEmpty()||txtItemName.getText().isEmpty()||txtQuantity.getText().isEmpty()||txtPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        } else {
            String id = txtItemCode.getText();
            String name = txtItemName.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(txtPrice.getText());

            PetItemDTO petItemDTO =new PetItemDTO(id,name,price,quantity);
            boolean isSaved= petItemBO.updatePetItem(petItemDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet Item updated:)").show();
            }

            setCellValueFactory();
            getAll();
            generateNextItemId();
            txtItemName.clear();
            txtQuantity.clear();
            txtPrice.clear();
        }

    }
}
