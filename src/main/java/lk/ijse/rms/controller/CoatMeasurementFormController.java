package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.rms.dto.CoatMeasurementsDto;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.dao.CoatMeasurementModel;
import lk.ijse.rms.dao.custom.impl.CustomerDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class CoatMeasurementFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblMeasurementId;

    @FXML
    private AnchorPane paneShirtMeasurement;

    @FXML
    private AnchorPane paneShirtMeasurements;

    @FXML
    private TextField txtChest;

    @FXML
    private TextField txtCollar;

    @FXML
    private TextField txtElbow;

    @FXML
    private TextField txtNeck;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtShoulder;

    @FXML
    private TextField txtSleeveLength;

    @FXML
    private TextField txtWaist;

    @FXML
    private TextField txtlength;

    private CoatMeasurementModel coatMeasurementModel = new CoatMeasurementModel();

    private CustomerDAOImpl customerModel = new CustomerDAOImpl();
    public void initialize(){
        generateNextCoatId();
        setDate();
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextCoatId() {
        try {
            String previousShirtID = lblMeasurementId.getText();
            String shirtmId = coatMeasurementModel.generateNextCoatId();
            lblMeasurementId.setText(shirtmId);
            clearFields();
            if (btnClearPressed){
                lblMeasurementId.setText(previousShirtID);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean btnClearPressed=false;
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtSearch.setText("");
        lblCustomerID.setText("");
        lblCustomerName.setText("");
        txtlength.setText("");
        txtChest.setText("");
        txtShoulder.setText("");
        txtSleeveLength.setText("");
        txtCollar.setText("");
        txtWaist.setText("");
        txtNeck.setText("");
        txtElbow.setText("");
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

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String measurementId =lblMeasurementId.getText();
        String customerId = lblCustomerID.getText();
        String date = lblDate.getText();
        String length= txtlength.getText();
        String chest=txtChest.getText();
        String shoulder=txtShoulder.getText();
        String sleeveLength=txtSleeveLength.getText();
        String collar=txtCollar.getText();
        String waist= txtWaist.getText();
        String neck= txtNeck.getText();
        String elbow= txtElbow.getText();

        CoatMeasurementsDto coatMeasurementsDto = new CoatMeasurementsDto(measurementId,customerId,date,length,chest,shoulder,sleeveLength,collar,waist,neck,elbow);

        try {
            boolean isSaved = coatMeasurementModel.coatMeasurementSave(coatMeasurementsDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Shirt Measurements Saved Successfully").show();
                clearFields();
                generateNextCoatId();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String measurementId =lblMeasurementId.getText();
        String customerId = lblCustomerID.getText();
        String date = lblDate.getText();
        String length= txtlength.getText();
        String chest=txtChest.getText();
        String shoulder=txtShoulder.getText();
        String sleeveLength=txtSleeveLength.getText();
        String collar=txtCollar.getText();
        String waist= txtWaist.getText();
        String neck= txtNeck.getText();
        String elbow= txtElbow.getText();

        CoatMeasurementsDto coatMeasurementsDto = new CoatMeasurementsDto(measurementId,customerId,date,length,chest,shoulder,sleeveLength,collar,waist,neck,elbow);
        try {
            boolean isUpdated = coatMeasurementModel.updateCoat(coatMeasurementsDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Measurements Updated Successfully").show();
                generateNextCoatId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void txtCustomerSearch(ActionEvent event) {
        String phone = txtSearch.getText();
        try {
            CustomerDto customerDto =customerModel.searchCustomer(phone);
            if (customerDto != null) {
                lblCustomerID.setText(customerDto.getCustomerId());
                lblCustomerName.setText(customerDto.getFirstName());
                String coatM =lblCustomerID.getText();
                try {
                    CoatMeasurementsDto coatMeasurementsDto =coatMeasurementModel.searchMeasurements(coatM);
                    if (coatMeasurementsDto !=null){
                        lblMeasurementId.setText(coatMeasurementsDto.getCmId());
                        lblDate.setText(coatMeasurementsDto.getDate());
                        txtlength.setText(coatMeasurementsDto.getLength());
                        txtChest.setText(coatMeasurementsDto.getChest());
                        txtShoulder.setText(coatMeasurementsDto.getShoulder());
                        txtSleeveLength.setText(coatMeasurementsDto.getSleeveLength());
                        txtCollar.setText(coatMeasurementsDto.getCollar());
                        txtWaist.setText(coatMeasurementsDto.getWaist());
                        txtNeck.setText(coatMeasurementsDto.getNeck());
                        txtElbow.setText(coatMeasurementsDto.getElbow());
                    }
                }catch (SQLException e){
                    new Alert(Alert.AlertType.ERROR,e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Not a registered Customer!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
