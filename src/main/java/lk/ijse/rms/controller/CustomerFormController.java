package lk.ijse.rms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.dto.tm.CustomerTm;
import lk.ijse.rms.model.CustomerModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private AnchorPane paneCustomer;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerId2;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Label lblCustomerId;

    private CustomerModel customerModel = new CustomerModel();

    public  void initialize(){
        genarateNextCustomerId();
        setCellValueFactory();
        loadAllCustomer();
        tableListner();
    }

    private void tableListner() {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(CustomerTm newValue) {
        lblCustomerId.setText(newValue.getCustomerId());
        txtFirstName.setText(newValue.getFirstName());
        txtLastName.setText(newValue.getLastName());
        txtAddress.setText(newValue.getAddress());
        txtPhoneNumber.setText(newValue.getPhoneNumber());

    }

    private void loadAllCustomer() {
        CustomerDto customerDto = new CustomerDto();
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> dtoList=customerModel.getAllCustomer();

            for (CustomerDto dto : dtoList){
                obList.add(
                        new CustomerTm(
                                dto.getCustomerId(),
                                dto.getFirstName(),
                                dto.getLastName(),
                                dto.getAddress(),
                                dto.getPhoneNumber()
                        )
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void genarateNextCustomerId() {
        try {
            String previousCustomerID = lblCustomerId.getText();
            String customerID = customerModel.genarateNextCustomerId();
            lblCustomerId.setText(customerID);
            clearFields();
            if (btnClearPressed){
                lblCustomerId.setText(previousCustomerID);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean btnClearPressed = false;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Delete Customer "+id+" ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    boolean isDeleted = customerModel.customerDelete(id);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Customer " + id + " Deleted").show();
                        loadAllCustomer();
                        clearFields();

                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = lblCustomerId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String address = txtAddress.getText();
        String phone = txtPhoneNumber.getText();

        if (fName.isEmpty() || lName.isEmpty() || address.isEmpty() || phone.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Fill all fields").show();
            return;
        }
        CustomerDto customerDto = new CustomerDto(customerId,fName,lName,address,phone);
        boolean isValid = validateCustomer(customerDto);
        if (isValid) {
            try {
                boolean isSaved = customerModel.customerSave(customerDto);
                if (isSaved) {
                    loadAllCustomer();
                    clearFields();
                    genarateNextCustomerId();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    private boolean validateCustomer(CustomerDto customerDto) {
        boolean matches1 = Pattern.matches("\\w{3,}",customerDto.getFirstName());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Name \n Name should be Letters only!").show();
            return false;
        }
        boolean matches2 = Pattern.matches("\\w{4,}",customerDto.getLastName());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Name \n Name should be Letters only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches(".{6,30}",customerDto.getAddress());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Address is not enough").show();
            return false;
        }
        boolean matches4 = Pattern.matches("(0)\\d{9}",customerDto.getPhoneNumber());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Invalid Phone Number").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        genarateNextCustomerId();
    }
    private void clearFields() {

        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtCustomerId2.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblCustomerId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String address = txtAddress.getText();
        String phone = txtPhoneNumber.getText();

        CustomerDto customerDto = new CustomerDto(id,fName,lName,address,phone);
        try {
            boolean isUpdated = customerModel.updateCustomer(customerDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
                genarateNextCustomerId();
                loadAllCustomer();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtCustomerIdSearch(ActionEvent event) {
        String customerSearch = txtCustomerId2.getText();
        try {
           CustomerDto customerDto;
            if (customerSearch.matches("(cus|CUS|Cus)(\\d{3})")) {
                customerDto = customerModel.searchCustomerId(customerSearch);
            } else {
                customerDto =customerModel.searchCustomer(customerSearch);
            }
            if (customerDto != null){
                lblCustomerId.setText(customerDto.getCustomerId());
                txtFirstName.setText(customerDto.getFirstName());
                txtLastName.setText(customerDto.getLastName());
                txtAddress.setText(customerDto.getAddress());
                txtPhoneNumber.setText(customerDto.getPhoneNumber());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Customer ID").show();
                txtCustomerId2.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
