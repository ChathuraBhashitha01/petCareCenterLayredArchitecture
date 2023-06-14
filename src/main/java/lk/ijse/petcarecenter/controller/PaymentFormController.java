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
import lk.ijse.petcarecenter.bo.custom.PaymentBO;
import lk.ijse.petcarecenter.model.*;
import lk.ijse.petcarecenter.model.tdm.ItemCartTM;
import lk.ijse.petcarecenter.model.tdm.MedicineCartTM;
import lk.ijse.petcarecenter.model.tdm.ServiceCartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    public Button btnBack;
    public Label lblPetId;
    public Label lblPrice;
    public TextField txtPetId;
    public Button btnPetAdd;
    public Button btnPriceAdd;
    public Button btnClear;
    public Button btnPrint;
    public AnchorPane root;
    public Label lblOwnerId;
    public Label lblPetName;
    public ComboBox cmbItemCode;
    public ComboBox cmbServiceId;
    public ComboBox cmbMedicineId;
    public Label lblIemDescription;
    public Label lblItemPrice;
    public Label lblMedicineDescription;
    public Label lblMedicinePrice;
    public Label lblServiceDescription;
    public Label lblServicePrice;
    public TextField txtMedicineQuantity;
    public TextField txtItemQuantity;
    public TableColumn colNo;
    public TableColumn colDescription;
    public TableColumn colQuantity;
    public TableColumn colPrice;
    public TableColumn colTotal;
    public TableView tblPayment;
    public Label lblNetTotal;
    public Label lblOrderDate;
    public TableView tblMedicinePayment;
    public TableColumn colMedicineID;
    public TableColumn colMedicineDescription;
    public TableColumn colMedicineQuantity;
    public TableColumn colMedicinePrice;
    public TableColumn colMedicineTotal;
    public TableView tblServicePayment;
    public TableColumn colServiceNo;
    public TableColumn colServiceDescription;
    public TableColumn colServicePrice;
    public TableColumn colServiceTotal;
    public Button btnMedicinePriceAdd;
    public Button btnMedicineClear;
    public Button btnServicePriceAdd;
    public Button btnServiceClear;
    public ComboBox cmbPetId;
    public Label lblPaymentId;

    private double allNetTotal = 0.0;
    private double itemNetTotal = 0.0;
    private double serviceNetTotal = 0.0;
    private double medicineNetTotal = 0.0;

    PaymentBO paymentBO= BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    private ObservableList<ItemCartTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItemCodes();
        loadMedicineID();
        loadServiceID();
        setCellValueFactory();
        setMedicineCellValueFactory();
        setServiceCellValueFactory();
        setOrderDate();
        generateNextOrderId();

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
    private void generateNextOrderId() {
        try {
            String id = paymentBO.getNextOrderId();
            lblPaymentId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void txtMedicineCostOnAction(ActionEvent actionEvent) {

    }


    public void txtItemPriceOnAction(ActionEvent actionEvent) {

    }

    private void setOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    public void btnPetAddOnAction(ActionEvent actionEvent) throws SQLException {
        String petID = txtPetId.getText();
        String ownerId = null;

        boolean isTrue= paymentBO.seachValiedPetID(petID);
        if (isTrue==false) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID not Founded :Wrong Pet ID)").show();
        }

        if (petID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Pet ID :)").show();
        }
        if(isTrue==true){
            PetDTO petDTO= paymentBO.searchPetDetails(petID);
            if (petDTO !=null) {
                String name = petDTO.getName();
                ownerId =petDTO.getOwnerID();

                lblPetName.setText(name);
            }
            OwnerDTO ownerDTO= paymentBO.searchOwnerDetails(ownerId);
            if (ownerDTO !=null) {
                String cus_name =ownerDTO.getName();

                lblOwnerId.setText(cus_name);
            }
        }
    }

    public void btnPriceAddOnAction(ActionEvent actionEvent) throws SQLException {

        String petID = txtPetId.getText();
        if (petID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID is Required:)").show();
        } else {

            if (txtItemQuantity.getText().isEmpty()) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Quantity is Required:)").show();
            }else {
                String itemCode = (String) cmbItemCode.getValue();
                String itemDes = lblIemDescription.getText();
                double itemPrice = Double.parseDouble(lblItemPrice.getText());
                int itemQuantity = Integer.parseInt(txtItemQuantity.getText());
                double total = itemQuantity * itemPrice;


                if (!obList.isEmpty()) {
                    for (int i = 0; i < tblPayment.getItems().size(); i++) {
                        if (colNo.getCellData(i).equals(itemCode)) {
                            itemQuantity += (int) colQuantity.getCellData(i);
                            total = itemQuantity * (int) itemPrice;

                            obList.get(i).setItemQuantity(itemQuantity);
                            obList.get(i).setTotal(total);

                            tblPayment.refresh();
                            calculateNetTotal();
                            return;
                        }
                    }
                }
                ItemCartTM tm = new ItemCartTM(itemCode, itemDes, itemPrice, itemQuantity, total);

                obList.add(tm);
                tblPayment.setItems(obList);
                calculateNetTotal();

                txtItemQuantity.setText("");
            }

        }

    }

    private void calculateNetTotal() {
        double total = (double) colTotal.getCellData(tblPayment.getItems().size()-1);
        allNetTotal += total;
        itemNetTotal+=total;
        lblNetTotal.setText(String.valueOf(allNetTotal));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try{
                int index=tblPayment.getItems().size()-1;
                ItemCartTM itemCartTM = obList.get(index);
                double total = itemCartTM.getTotal();
                allNetTotal = allNetTotal - total;
                lblNetTotal.setText(String.valueOf(allNetTotal));
                tblPayment.refresh();
                obList.remove(tblPayment.getItems().size()-1);
            }catch (Exception e){}
        }

    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws SQLException {

        String petID=txtPetId.getText();
        String paymentId=lblPaymentId.getText();
        String date=lblOrderDate.getText();
        double itemTotal=itemNetTotal;
        double medicineTotal=medicineNetTotal;
        double serviceTotal=serviceNetTotal;
        double allTotal=allNetTotal;

        List<ItemCartDTO> itemDetailsDTOList = new ArrayList<>();
        for (int i = 0; i <tblPayment.getItems().size(); i++) {
            ItemCartTM itemCartTM = obList.get(i);

            ItemCartDTO dto = new ItemCartDTO(
                    itemCartTM.getItemCode(),
                    itemCartTM.getItemQuantity()
            );
            itemDetailsDTOList.add(dto);
        }

        List<MedicineCartDTO> medicineDetailsDTOList = new ArrayList<>();
        for (int i = 0; i <tblMedicinePayment.getItems().size(); i++) {
            MedicineCartTM medicineCartTM = medicineObList.get(i);

            MedicineCartDTO dto = new MedicineCartDTO(
                    medicineCartTM.getMedicineID(),
                    medicineCartTM.getMedicineQuantity()
            );
            medicineDetailsDTOList.add(dto);
        }

        List<ServiceCartDTO> serviceDetailsDTOList = new ArrayList<>();
        for (int i = 0; i <tblServicePayment.getItems().size(); i++) {
            ServiceCartTM serviceCartTM = serviceObList.get(i);

            ServiceCartDTO dto = new ServiceCartDTO(
                    serviceCartTM.getServiceID()
            );
            serviceDetailsDTOList.add(dto);
        }

        boolean isPlaced = false;
        try {
            PaymentDTO paymentDTO=new PaymentDTO(petID, paymentId,date,itemTotal,medicineTotal,serviceTotal,allTotal, itemDetailsDTOList, medicineDetailsDTOList, serviceDetailsDTOList);
            isPlaced = paymentBO.payment(paymentDTO);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Done").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Not Done").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
        generateNextOrderId();
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = paymentBO.loadItemCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }
    private void loadMedicineID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = paymentBO.loadMedicineCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbMedicineId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void fillItemFields(PetItemDTO item) {
        lblIemDescription.setText(item.getName());
        lblItemPrice.setText(String.valueOf(item.getPrice()));
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        String code = (String) cmbItemCode.getValue();
        try {
            PetItemDTO item = paymentBO.searchItemCodes(code);
            fillItemFields(item);

            txtItemQuantity.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillMedicineFields(MedicineDTO medicineDTO) {
        lblMedicineDescription.setText(medicineDTO.getName());
        lblMedicinePrice.setText(String.valueOf(medicineDTO.getPrice()));
    }

    public void cmbMedicineIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbMedicineId.getValue();
        try {
            MedicineDTO medicineDTO = paymentBO.searchMedicineId(id);
            fillMedicineFields(medicineDTO);

            txtMedicineQuantity.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadServiceID() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = paymentBO.loadServiceID();

            for (String id : ids) {
                obList.add(id);
            }
            cmbServiceId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    public void cmbServiceIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbServiceId.getValue();
        try {
            ServiceDTO serviceDTO = paymentBO.searchServiceIDs(id);
            fillServiceFields(serviceDTO);

            txtMedicineQuantity.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillServiceFields(ServiceDTO serviceDTO) {
        lblServiceDescription.setText(serviceDTO.getName());
        lblServicePrice.setText(String.valueOf(serviceDTO.getCost()));
    }

    void setCellValueFactory() {
        colNo.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDes"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    void setMedicineCellValueFactory() {
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colMedicineDescription.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMedicineQuantity.setCellValueFactory(new PropertyValueFactory<>("medicineQuantity"));
        colMedicinePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colMedicineTotal.setCellValueFactory(new PropertyValueFactory<>("medicineTotal"));

    }

    void setServiceCellValueFactory() {
        colServiceNo.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        colServiceDescription.setCellValueFactory(new PropertyValueFactory<>("serviceDes"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("serviceCharge"));
        colServiceTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    ObservableList<MedicineCartTM> medicineObList = FXCollections.observableArrayList();

    public void btnMedicinePriceAddOnAction(ActionEvent actionEvent) throws SQLException {

        String petID = txtPetId.getText();
        if (petID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID Not Added :)").show();
        } else {
            if (txtMedicineQuantity.getText().isEmpty()) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Quantity is Required:)").show();
            }else {
                String medicineID = (String) cmbMedicineId.getValue();
                String name = lblMedicineDescription.getText();
                double price = Double.parseDouble(lblMedicinePrice.getText());
                int medicineQuantity = Integer.parseInt(txtMedicineQuantity.getText());
                double total = medicineQuantity * price;


                if (!medicineObList.isEmpty()) {
                    for (int i = 0; i < tblMedicinePayment.getItems().size(); i++) {
                        if (colMedicineID.getCellData(i).equals(medicineID)) {
                            medicineQuantity += (int) colMedicineQuantity.getCellData(i);
                            total = medicineQuantity * (int) price;

                            medicineObList.get(i).setMedicineQuantity(medicineQuantity);
                            medicineObList.get(i).setMedicineTotal(total);

                            tblMedicinePayment.refresh();
                            calculateNetTotal();
                            return;
                        }
                    }
                }
                MedicineCartTM tm = new MedicineCartTM(medicineID, name, medicineQuantity, price, total);

                medicineObList.add(tm);
                tblMedicinePayment.setItems(medicineObList);
                calculateMedicineNetTotal();
                txtMedicineQuantity.setText("");
            }

        }

    }

    public void btnMedicineClearOnAction(ActionEvent actionEvent) throws SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {

            try{
                int index=tblMedicinePayment.getItems().size()-1;
                MedicineCartTM medicineCartTM = medicineObList.get(index);
                double total = medicineCartTM.getMedicineTotal();
                allNetTotal = allNetTotal - total;
                lblNetTotal.setText(String.valueOf(allNetTotal));
                tblMedicinePayment.refresh();
                medicineObList.remove(tblMedicinePayment.getItems().size()-1);
            }catch (Exception e){}
        }
    }

    ObservableList<ServiceCartTM> serviceObList = FXCollections.observableArrayList();

    public void btnServicePriceAddOnAction(ActionEvent actionEvent) throws SQLException {


        String petID = txtPetId.getText();
        if (petID.isEmpty()) {
            new Alert(Alert.AlertType.CONFIRMATION, "Pet ID Not Added :)").show();
        } else {
            String petId = txtPetId.getText();
            String date = lblOrderDate.getText();
            String serviceID = String.valueOf(cmbServiceId.getValue());
            String serviceDes = lblServiceDescription.getText();
            double serviceCharge = Integer.parseInt(lblServicePrice.getText());
            double total = Double.parseDouble(String.valueOf(serviceCharge));

            if (!serviceObList.isEmpty()) {
                for (int i = 0; i < tblServicePayment.getItems().size(); i++) {
                    if (colNo.getCellData(i).equals(serviceID)) {
                        total = Double.parseDouble(String.valueOf(serviceCharge));

                        serviceObList.get(i).setServiceCharge(total);

                        tblPayment.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }
            ServiceCartTM tm = new ServiceCartTM(serviceID, serviceDes, serviceCharge, total);

            serviceObList.add(tm);
            tblServicePayment.setItems(serviceObList);
            calculateServiceNetTotal();

        }
    }

    public void btnServiceClearOnAction(ActionEvent actionEvent) {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {

            try{
                int index=tblServicePayment.getItems().size()-1;
                ServiceCartTM serviceCartTM = serviceObList.get(index);
                double total = serviceCartTM.getTotal();
                allNetTotal = allNetTotal - total;
                lblNetTotal.setText(String.valueOf(allNetTotal));
                tblServicePayment.refresh();
                serviceObList.remove(tblServicePayment.getItems().size()-1);

            }catch (Exception e){}
        }


    }

    private void calculateMedicineNetTotal() {
        double total = (double) colMedicineTotal.getCellData(tblMedicinePayment.getItems().size()-1);
        allNetTotal +=total ;
        medicineNetTotal+=total;
        lblNetTotal.setText(String.valueOf(allNetTotal));
    }

    private void calculateServiceNetTotal() {
        double total = (double) colServiceTotal.getCellData(tblServicePayment.getItems().size()-1);
        allNetTotal += total;
        serviceNetTotal+=total;
        lblNetTotal.setText(String.valueOf(allNetTotal));
    }
}
