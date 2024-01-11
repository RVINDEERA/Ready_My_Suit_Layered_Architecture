package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.dto.*;
import lk.ijse.rms.dto.tm.FabricTm;
import lk.ijse.rms.dao.custom.impl.FabricDAOImpl;
import lk.ijse.rms.dao.custom.impl.ItemDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class FabricFormController {

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Label lblFabricId;

    @FXML
    private AnchorPane paneCustomer;

    @FXML
    private TableView<FabricTm> tblFabric;

    @FXML
    private TextField txtColur;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRollQty;

    @FXML
    private TextField txtSearch;

    FabricDAOImpl fabricDAOImpl = new FabricDAOImpl();

    ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

    public void initialize(){

        genarateNextFabricId();
        loadItemType();
        loadAllFabrics();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("fabricId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("rollqty"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
    }

    private void loadAllFabrics() {
        FabricDto fabricDto = new FabricDto();

        ObservableList<FabricTm> obList = FXCollections.observableArrayList();
        try {
            List<FabricDto> dtoList= fabricDAOImpl.getAll();

            for (FabricDto dto : dtoList){
                obList.add(
                        new FabricTm(
                                dto.getFabricId(),
                                dto.getName(),
                                dto.getRollqty(),
                                dto.getType(),
                                dto.getColour()
                        )
                );
            }
            tblFabric.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void genarateNextFabricId() {
        try {
            String previousFabricID = lblFabricId.getText();
            String fabricId = fabricDAOImpl.generateNextID();
            lblFabricId.setText(fabricId);
            clearFields();
            if (btnClearPressed){
                lblFabricId.setText(previousFabricID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtRollQty.setText("");
        cmbType.setValue("");
        txtColur.setText("");
    }

    private boolean btnClearPressed = false;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id= lblFabricId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Delete Fabric "+id+" ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    boolean isDeleted = fabricDAOImpl.delete(id);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Fabric " + id + " Deleted").show();
                        genarateNextFabricId();
                        loadAllFabrics();
                        clearFields();

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }

    }
    private  void  loadItemType(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> idList = itemDAOImpl.getAll();
            for (ItemDto dto : idList){
                obList.add(dto.getType());
            }
            cmbType.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String fabricId = lblFabricId.getText();
        String name = txtName.getText();
        String rollqty=txtRollQty.getText();
        String type = cmbType.getValue();
        String colour = txtColur.getText();

        if (name.isEmpty()||rollqty.isEmpty() || type.isEmpty() || colour.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Fill all fields").show();
            return;
        }
        FabricDto fabricDto = new FabricDto(fabricId,name,rollqty,type,colour);
        boolean isValid = validateFabric(fabricDto);
        if (isValid) {
            try {
                boolean isSaved = fabricDAOImpl.save(fabricDto);
                if (isSaved) {
                    loadAllFabrics();
                    clearFields();
                    genarateNextFabricId();
                    new Alert(Alert.AlertType.INFORMATION, "Fabric Saved Successfully").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean validateFabric(FabricDto fabricDto) {
        boolean matches1 = Pattern.matches("[A-Z]{3,}",fabricDto.getName());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Name \nCapital Letters Only!").show();
            return false;
        }
        boolean matches2 = Pattern.matches("\\d{1,2}",fabricDto.getRollqty());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"QTY Digits only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches("[A-Za-z]{3,10}",fabricDto.getColour());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid Colour\nLetters Only!").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String fabricId = lblFabricId.getText();
        String name = txtName.getText();
        String rollqty=txtRollQty.getText();
        String type = cmbType.getValue();
        String colour = txtColur.getText();

        FabricDto fabricDto = new FabricDto(fabricId,name,rollqty,type,colour);
        boolean isVlidate = validateFabric(fabricDto);
        if (isVlidate) {
            try {
                boolean isUpdated = fabricDAOImpl.update(fabricDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Fabric Updated Successfully").show();
                        genarateNextFabricId();
                        loadAllFabrics();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    @FXML
    void txtFabricSearch(ActionEvent event) {
        String id =txtSearch.getText();
        try {
            FabricDto fabricDto = fabricDAOImpl.search(id);
            if (fabricDto != null){
                lblFabricId.setText(fabricDto.getFabricId());
                txtName.setText(fabricDto.getName());
                txtRollQty.setText(fabricDto.getRollqty());
                cmbType.setValue(fabricDto.getType());
                txtColur.setText(fabricDto.getColour());

            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Fabric ID").show();
                txtSearch.setText("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

}
