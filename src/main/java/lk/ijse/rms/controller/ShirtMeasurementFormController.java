package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rms.bo.BOFactory;
import lk.ijse.rms.bo.custom.CustomerBo;
import lk.ijse.rms.bo.custom.ShirtMeasurementBo;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.dto.ShirtDto;
import lk.ijse.rms.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.rms.dao.custom.impl.ShirtMeasurementDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class ShirtMeasurementFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblMeasurementId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private AnchorPane paneShirtMeasurement;

    @FXML
    private AnchorPane paneShirtMeasurements;

    @FXML
    private TextField txtChest;

    @FXML
    private TextField txtCollar;

    @FXML
    private TextField txtCuff;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtShoulder;

    @FXML
    private TextField txtSleevelength;

    @FXML
    private TextField txtWaist;

    @FXML
    private TextField txtlength;

    @FXML
    private JFXComboBox<String> cmbCustomer;

    @FXML
    private TextField txtcustomerid;
    private AnchorPane paneSelectItem;
    CustomerBo customerModel = (CustomerBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.CUSTOMER);
    ShirtMeasurementBo shirtMeasurementDAOImpl = (ShirtMeasurementBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.SHIRTMEASUREMENT);

    public  void initialize(){
        generateNextShirtId();
        setDate();

    }



    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
    private boolean btnClearPressed=false;

    private void generateNextShirtId() {
        try {
            String previousShirtID = lblMeasurementId.getText();
            String shirtmId = shirtMeasurementDAOImpl.generateNextID();
            lblMeasurementId.setText(shirtmId);
            clearFields();
            if (btnClearPressed){
                lblMeasurementId.setText(previousShirtID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtSearch.setText("");
        lblCustomerId.setText("");
        lblCustomerName.setText("");
        txtlength.setText("");
        txtChest.setText("");
        txtCollar.setText("");
        txtCuff.setText("");
        txtWaist.setText("");
        txtSleevelength.setText("");
        txtShoulder.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String measurementId =lblMeasurementId.getText();
        String customerId = lblCustomerId.getText();
        String date = lblDate.getText();
        String length= txtlength.getText();
        String chest=txtChest.getText();
        String shoulder=txtShoulder.getText();
        String sleeveLength=txtSleevelength.getText();
        String collar=txtCollar.getText();
        String cuff= txtCuff.getText();
        String waist= txtWaist.getText();

        ShirtDto shirtDto = new ShirtDto(measurementId,customerId,date,length,chest,shoulder,sleeveLength,collar, cuff, waist);
      //  boolean isVlidate = validateMeasurement(shirtDto);
     //   if (isVlidate) {
            try {
                boolean isSaved = shirtMeasurementDAOImpl.save(shirtDto);
                if (isSaved){
                    generateNextShirtId();
                    clearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Shirt Measurements Saved Successfully").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
      //  }

    }

    private boolean validateMeasurement(ShirtDto shirtDto) {
        boolean matches1 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getLength());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }
        boolean matches2 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getChest());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getCollar());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }
        boolean matches4 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getCuff());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }
        boolean matches5 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getSleeveLength());
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }  boolean matches6 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getWaist());
        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }
        boolean matches7 = Pattern.matches("(\\d{1,2})((.*)\\d{1,2})*",shirtDto.getShoulder());
        if (!matches7){
            new Alert(Alert.AlertType.ERROR,"Numbers Only!").show();
            return false;
        }


        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String measurementId =lblMeasurementId.getText();
        String customerId = lblCustomerId.getText();
        String date = lblDate.getText();
        String length= txtlength.getText();
        String chest=txtChest.getText();
        String shoulder=txtShoulder.getText();
        String sleeveLength=txtSleevelength.getText();
        String collar=txtCollar.getText();
        String cuff= txtCuff.getText();
        String waist= txtWaist.getText();

        ShirtDto shirtDto = new ShirtDto(measurementId,customerId,date,length,chest,shoulder,sleeveLength,collar, cuff, waist);
        try {
            boolean isUpdated = shirtMeasurementDAOImpl.update(shirtDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Measurements Updated Successfully").show();
                generateNextShirtId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtCustomerSearch(ActionEvent event) {
        String phone = txtSearch.getText();
        try {
            CustomerDto customerDto =customerModel.searchCustomer(phone);
            if (customerDto != null) {
                lblCustomerId.setText(customerDto.getCustomerId());
                lblCustomerName.setText(customerDto.getFirstName());

                String shirtM = lblCustomerId.getText();
                try {
                    ShirtDto shirtDto = shirtMeasurementDAOImpl.search(shirtM);
                    if (shirtDto !=null){
                        lblMeasurementId.setText(shirtDto.getSmId());
                        lblDate.setText(shirtDto.getDate());
                        txtlength.setText(shirtDto.getLength());
                        txtChest.setText(shirtDto.getChest());
                        txtShoulder.setText(shirtDto.getShoulder());
                        txtSleevelength.setText(shirtDto.getSleeveLength());
                        txtCollar.setText(shirtDto.getCollar());
                        txtCuff.setText(shirtDto.getCuff());
                        txtWaist.setText(shirtDto.getWaist());
                    }
                }catch (SQLException e){
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Not a registered Customer!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }



}
