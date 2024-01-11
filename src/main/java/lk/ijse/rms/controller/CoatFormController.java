package lk.ijse.rms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.bo.BOFactory;
import lk.ijse.rms.bo.custom.CoatBo;
import lk.ijse.rms.dao.custom.CoatDAO;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CoatTm;
import lk.ijse.rms.dao.custom.impl.CoatDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CoatFormController {

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colCoatId;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Label lblCoatId;

    @FXML
    private AnchorPane paneMachine;

    @FXML
    private TableView<CoatTm> tblCoat;

    @FXML
    private TextField txtAavail;

    @FXML
    private TextField txtColour;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtType;

    @FXML
    private TableColumn<?, ?> colSize;
    @FXML
    private TextField txtSize;
    @FXML
    private Label lblmfgDatetoset;

    CoatBo coatDAOImpl = (CoatBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.COAT);

    public void initialize(){
        genarateNextCoatId();
        setCellVallueFactory();
        loadAllCoat();
        tableListner();
    }
    private void tableListner() {
        tblCoat.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(CoatTm newValue) {
        lblCoatId.setText(newValue.getCoatId());
        txtType.setText(newValue.getType());
        txtColour.setText(newValue.getColor());
        txtAavail.setText(newValue.getAvailability());
        txtPrice.setText(newValue.getPrice());
        txtSize.setText(newValue.getSize());

    }

    private void loadAllCoat() {
        CoatDto coatDto = new CoatDto();
        ObservableList<CoatTm> obList = FXCollections.observableArrayList();
        try {
            List<CoatDto> dtoList= coatDAOImpl.getAll();

            for (CoatDto dto : dtoList){
                obList.add(
                        new CoatTm(
                                dto.getCoatId(),
                                dto.getType(),
                                dto.getColor(),
                                dto.getAvailability(),
                                dto.getDate(),
                                dto.getPrice(),
                                dto.getSize()
                        )
                );
            }
            tblCoat.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellVallueFactory() {
        colCoatId.setCellValueFactory(new PropertyValueFactory<>("coatId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("color"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    }
    private boolean btnClearPressed = false;

    private void genarateNextCoatId() {
        try {
            String previousCoatID = lblCoatId.getText();
            String coatId = coatDAOImpl.generateNextID();
            lblCoatId.setText(coatId);
            clearField();
            if (btnClearPressed){
                lblCoatId.setText(previousCoatID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
    }

    private void clearField() {
        txtType.setText("");
        txtSize.setText("");
        txtColour.setText("");
        txtAavail.setText("");
        txtDate.setValue(null);
        txtPrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id =lblCoatId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Delete Coat "+id+" ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    boolean isDeleted = coatDAOImpl.delete(id);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Coat " + id + " Deleted").show();
                        clearField();
                        genarateNextCoatId();
                        loadAllCoat();

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String coatId=lblCoatId.getText();
        String type =txtType.getText();
        String color = txtColour.getText();
        String avail =txtAavail.getText();
        String date =txtDate.getValue().toString();
        String price = txtPrice.getText();
        String size = txtSize.getText();

        CoatDto coatDto = new CoatDto(coatId,type,color,avail,date,price,size);

        boolean isValid = validateCoat(coatDto);
        if (isValid){
            try {
                boolean isSaved = coatDAOImpl.save(coatDto);
                if (isSaved){
                    clearField();
                    loadAllCoat();
                    genarateNextCoatId();
                    new Alert(Alert.AlertType.INFORMATION,"New Coat Saved Successful").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }


    }

    private boolean validateCoat(CoatDto coatDto) {
        boolean matches1 = Pattern.matches("\\w.{3,20}",coatDto.getType());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Letters Only!").show();
            return false;
        }
        boolean matches2 = Pattern.matches("\\w{3,10}",coatDto.getColor());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Colour Name").show();
            return false;
        }
        boolean matches3 = Pattern.matches("\\d{4}",coatDto.getPrice());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Digits only").show();
            return false;
        }

        boolean matches4 = Pattern.matches("YES|NO",coatDto.getAvailability());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Availability Should be \n\"YES\" or \"NO\" Only").show();
            return false;
        }
        boolean matches5 = Pattern.matches("\\d{2}",coatDto.getSize());
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"2 digits Only!").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String coatId=lblCoatId.getText();
        String type =txtType.getText();
        String color = txtColour.getText();
        String avail =txtAavail.getText();
        String date =txtDate.getValue().toString();
        String price = txtPrice.getText();
        String size = txtSize.getText();

        CoatDto coatDto = new CoatDto(coatId,type,color,avail,date,price,size);
        try {
            boolean isUpdated = coatDAOImpl.update(coatDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Coat Updated Successfully").show();
                genarateNextCoatId();
                loadAllCoat();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void txtCoatIdSearch(ActionEvent event) {
        String id =txtSearch.getText();
        try {
            CoatDto coatDto = coatDAOImpl.search(id);
            if (coatDto != null){
                lblCoatId.setText(coatDto.getCoatId());
                txtType.setText(coatDto.getType());
                txtColour.setText(coatDto.getColor());
                txtPrice.setText(coatDto.getPrice());
                txtSize.setText(coatDto.getSize());
                txtAavail.setText(coatDto.getAvailability());

                loadAllCoat();

            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Coat ID").show();
                txtSearch.setText("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
