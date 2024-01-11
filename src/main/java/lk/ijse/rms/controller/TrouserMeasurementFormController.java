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
import lk.ijse.rms.bo.BOFactory;
import lk.ijse.rms.bo.custom.CustomerBo;
import lk.ijse.rms.bo.custom.TrouserBo;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.dto.TrouserDto;
import lk.ijse.rms.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.rms.dao.custom.impl.TrouserDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class TrouserMeasurementFormController {

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
    private TextField txtBottom;

    @FXML
    private TextField txtCrotch;

    @FXML
    private TextField txtHalfSeat;

    @FXML
    private TextField txtKnee;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSeat;

    @FXML
    private TextField txtWaist;

    @FXML
    private TextField txtlength;

    TrouserBo trouserDAOImpl = (TrouserBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.TROUSER);

    CustomerBo customerModel = (CustomerBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.CUSTOMER);

    public void initialize(){
        generateNextTrouserId();
        setDate();
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextTrouserId() {

        try {
            String previousTrouserID = lblMeasurementId.getText();
            String trouserId = trouserDAOImpl.generateNextID();
            lblMeasurementId.setText(trouserId);
            clearFields();
            if (btnClearPressed){
                lblMeasurementId.setText(previousTrouserID);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
        txtBottom.setText("");
        txtCrotch.setText("");
        txtKnee.setText("");
        txtSeat.setText("");
        txtHalfSeat.setText("");
        txtWaist.setText("");
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
        String measurementId = lblMeasurementId.getText();
        String customerId= lblCustomerID.getText();
        String date = lblDate.getText();
        String length = txtlength.getText();
        String waist = txtWaist.getText();
        String seat = txtSeat.getText();
        String halfSeat = txtHalfSeat.getText();
        String knee = txtKnee.getText();
        String bottom = txtBottom.getText();
        String crotch = txtCrotch.getText();

        TrouserDto trouserDto = new TrouserDto(measurementId,customerId,date,length,waist,seat,halfSeat,knee,bottom,crotch);

        try {
            boolean isSaved = trouserDAOImpl.save(trouserDto);
            if (isSaved){
                clearFields();
                new Alert(Alert.AlertType.INFORMATION,"New Trouser Measurements Saved Successful").show();
                generateNextTrouserId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String measurementId = lblMeasurementId.getText();
        String customerId= lblCustomerID.getText();
        String date = lblDate.getText();
        String length = txtlength.getText();
        String waist = txtWaist.getText();
        String seat = txtSeat.getText();
        String halfSeat = txtHalfSeat.getText();
        String knee = txtKnee.getText();
        String bottom = txtBottom.getText();
        String crotch = txtCrotch.getText();

        TrouserDto trouserDto = new TrouserDto(measurementId,customerId,date,length,waist,seat,halfSeat,knee,bottom,crotch);
        try {
            boolean isUpdated = trouserDAOImpl.update(trouserDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Measurements Updated Successfully").show();
                generateNextTrouserId();
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
                lblCustomerID.setText(customerDto.getCustomerId());
                String trouserM = lblCustomerID.getText();
                    try {
                        TrouserDto trouserDto = trouserDAOImpl.search(trouserM);
                        if (trouserDto !=null){
                            lblMeasurementId.setText(trouserDto.getTrmId());
                            lblDate.setText(trouserDto.getDate());
                            txtlength.setText(trouserDto.getLength());
                            txtWaist.setText(trouserDto.getWast());
                            txtSeat.setText(trouserDto.getSeat());
                            txtHalfSeat.setText(trouserDto.getHalfSeat());
                            txtKnee.setText(trouserDto.getKnee());
                            txtBottom.setText(trouserDto.getBottm());
                            txtCrotch.setText(trouserDto.getCrotch());
                        }
                    }catch (SQLException e ){
                        new Alert(Alert.AlertType.ERROR,e.getMessage());
                }
                lblCustomerName.setText(customerDto.getFirstName());
            }else{
                new Alert(Alert.AlertType.ERROR,"Not a registered Customer!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
