package lk.ijse.petcarecenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.petcarecenter.bo.BOFactory;
import lk.ijse.petcarecenter.bo.custom.PetManagementBO;
import lk.ijse.petcarecenter.model.OwnerDTO;
import lk.ijse.petcarecenter.model.PetDTO;

import java.io.IOException;
import java.sql.SQLException;

public class PetManagementFormController {
    public Button btnBack;
    public TextField txtPetId;
    public Button btnSearch;
    public ComboBox txtPetType;
    public TextField txtPetName;
    public TextField txtPetAge;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtContact;
    public TextField txtBreed;
    public Button btnUpdate;
    public Button btnClear;
    public AnchorPane root;
    public TextField txtPettype;
    public Button btnDelete;
    PetManagementBO petManagementBO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PET_MANAGEMENT);

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

    public void txtPetIdOnAction(ActionEvent actionEvent) {

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtPetId.getText();
        boolean isTrue= petManagementBO.seachValiedPetID(id);
        if (isTrue==false) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID not Founded :Wrong Pet ID)").show();
        }
        if (txtPetId.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID Field Are Required:)").show();
        }
        if(isTrue==true){
            String ownerId = null;

            PetDTO petDTO= petManagementBO.searchPetDetails(id);
            if (petDTO !=null) {
                String pet_id = petDTO.getPetID();
                String pet_name = petDTO.getName();
                int age = petDTO.getAge();
                String type =petDTO.getType();
                String breed =petDTO.getBread();
                ownerId =petDTO.getOwnerID();

                txtPetId.setText(pet_id);
                txtPetName.setText(pet_name);
                txtPetAge.setText(String.valueOf(age));
                txtBreed.setText(String.valueOf(breed));
                txtPettype.setText(type);
            }

            OwnerDTO ownerDTO= petManagementBO.searchOwnerDetails(ownerId);
            if (ownerDTO !=null) {
                String cus_id = ownerDTO.getOwnerID();
                String cus_name =ownerDTO.getName();
                int contact = ownerDTO.getPhoneNumber();

                txtCustomerId.setText(ownerId);
                txtCustomerName.setText(cus_name);
                txtContact.setText(String.valueOf(contact));
            }
        }
    }

    public void txtPetNameOnAction(ActionEvent actionEvent) {

    }

    public void txtPetAgeOnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerIdOnaction(ActionEvent actionEvent) {

    }

    public void txtCustomerNameOnAction(ActionEvent actionEvent) {

    }

    public void txtContactOnAction(ActionEvent actionEvent) {

    }

    public void txtBreedOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String petId = txtPetId.getText();
        String ownerId = txtCustomerId.getText();

        boolean petIDTrue= petManagementBO.seachValiedPetID(petId);
        if (petIDTrue==false) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID not Founded :Wrong Pet ID)").show();
        }
        boolean ownerIDTrue= petManagementBO.searchValiedOwnerID(ownerId);
        if (ownerIDTrue==false) {
            new Alert(Alert.AlertType.CONFIRMATION, "Owner ID not Founded :Wrong Owner ID)").show();
        }
        if (txtPetId.getText().isEmpty()||txtPetName.getText().isEmpty()||txtPetAge.getText().isEmpty()||txtBreed.getText().isEmpty()||txtCustomerId.getText().isEmpty()||txtCustomerName.getText().isEmpty()){
            new Alert(Alert.AlertType.CONFIRMATION, "All Field Are Required:)").show();
        }
        if(petIDTrue==true&&ownerIDTrue==true){
            String petName = txtPetName.getText();
            int age = Integer.parseInt(txtPetAge.getText());
            String type = txtPettype.getText();
            String breed = txtBreed.getText();
            String ownerName = txtCustomerName.getText();
            int contact = Integer.parseInt(txtContact.getText());


            PetDTO petDTO =new PetDTO(petId,petName,age,type,breed,ownerId);
            boolean isUpdated= petManagementBO.updatePetDetails(petDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet Updated :)").show();
            }

            OwnerDTO ownerDTO =new OwnerDTO(ownerId,ownerName,contact);
            boolean isOwnerUpdated= petManagementBO.updateOwnerDetails(ownerDTO);
            if (isOwnerUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Owner Updated :)").show();
            }
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent){
        txtPetId.clear();
        txtPetAge.clear();
        txtPetName.clear();
        txtPettype.clear();
        txtBreed.clear();
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtContact.clear();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent)throws SQLException {
        if (txtPetId.getText().isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID Field Are Required:)").show();
        }else {
            String petId = txtPetId.getText();
            boolean isSaved = petManagementBO.delectPetDetails(petId);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pet deletd!").show();
            }
        }
    }
}
