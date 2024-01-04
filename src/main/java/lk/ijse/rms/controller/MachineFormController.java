package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.dto.MachineDto;
import lk.ijse.rms.dto.TailorDto;
import lk.ijse.rms.dto.tm.MachineTm;
import lk.ijse.rms.dao.custom.impl.MachineDAOImpl;
import lk.ijse.rms.dao.custom.impl.TailorDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class MachineFormController {

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMachineId;

    @FXML
    private TableColumn<?, ?> colTailorId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private DatePicker txtDate;

    @FXML
    private MenuButton mniAvailability;

    @FXML
    private AnchorPane paneMachine;

    @FXML
    private TableView<MachineTm> tblMachine;
    @FXML
    private TextField txtMachineId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtAavail;
    @FXML
    private TextField txtTailorId;

    @FXML
    private TextField txtType;

    @FXML
    private Label lblMachineId;

    @FXML
    private JFXComboBox<String> cmbTailorId;

    private MachineDAOImpl machineDAOImpl =new MachineDAOImpl();
    private boolean btnClearPressed=false;
    private TailorDAOImpl tailorDAOImpl =new TailorDAOImpl();

    public void initialize(){
        setCellValueFactory();
        genarateNextMachineId();
        loaddAllMachine();
        loadTailorIds();
    }

    private void loadTailorIds() {
        ObservableList<String> obList =FXCollections.observableArrayList();

        try {
            List<TailorDto> idList = tailorDAOImpl.getAllMachine();
            for (TailorDto dto : idList){
                obList.add(dto.getTailorId());
            }
            cmbTailorId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loaddAllMachine() {
        MachineDto machineDto = new MachineDto();
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();
        try {
            List<MachineDto> dtoList= machineDAOImpl.getAllCustomer();

            for (MachineDto dto : dtoList){
                obList.add(
                        new MachineTm(
                                dto.getMachineId(),
                                dto.getTailorId(),
                                dto.getType(),
                                dto.getDate(),
                                dto.getAvail()
                        )
                );
            }
            tblMachine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    private void setCellValueFactory() {
        colMachineId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colTailorId.setCellValueFactory(new PropertyValueFactory<>("tailorId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("avail"));
    }

    private void genarateNextMachineId() {
        try {
            String previousCustomerID = lblMachineId.getText();
            String machineIdID = machineDAOImpl.genarateNextMachineId();
            lblMachineId.setText(machineIdID);
            clearFields();
            if (btnClearPressed){
                lblMachineId.setText(previousCustomerID);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearFields() {
        cmbTailorId.setValue("");
        txtType.setText("");
        txtDate.setValue(null);
        txtAavail.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id= lblMachineId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Delete Machine "+id+" ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    boolean isDeleted = machineDAOImpl.machineDelete(id);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Machine " + id + " Deleted").show();
                        loaddAllMachine();
                        genarateNextMachineId();
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
        String machineId=lblMachineId.getText();
        String tailorId =cmbTailorId.getValue();
        String type = txtType.getText();
        String date = txtDate.getValue().toString();
        String avail = txtAavail.getText();

        MachineDto machineDto = new MachineDto(machineId,tailorId,type,date,avail);
        boolean isVlidate = validateMachine(machineDto);
        if (isVlidate) {
            try {
                boolean isSaved = machineDAOImpl.machineSave(machineDto);
                if (isSaved) {
                    clearFields();
                    genarateNextMachineId();
                    loaddAllMachine();
                    new Alert(Alert.AlertType.INFORMATION, "New Machine Added Successful").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validateMachine(MachineDto machineDto) {
        boolean matches1 = Pattern.matches("[A-Z]{3,10}",machineDto.getType());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Capital Letters Only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches("YES|NO",machineDto.getAvail());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Availability Should be \n\"YES\" or \"NO\" Only").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String machineId=lblMachineId.getText();
        String tailorId =cmbTailorId.getValue();
        String type = txtType.getText();
        String date = txtDate.getValue().toString();
        String avail = txtAavail.getText();

        MachineDto machineDto = new MachineDto(machineId,tailorId,type,date,avail);
        boolean isVlidate = validateMachine(machineDto);
        if (isVlidate) {
            try {
                boolean isUpdated = machineDAOImpl.updateCustomer(machineDto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Machine Updated Successfully").show();
                    genarateNextMachineId();
                    loaddAllMachine();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    @FXML
    void txtCustomerIdSearch(ActionEvent event) {
        String id =txtSearch.getText();
        try {
            MachineDto machineDto = machineDAOImpl.searchMachine(id);
            if (machineDto != null){
                lblMachineId.setText(machineDto.getMachineId());
                cmbTailorId.setValue(machineDto.getTailorId());
                txtType.setText(machineDto.getType());
                txtAavail.setText(machineDto.getAvail());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Machine ID").show();
                txtSearch.setText("");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    }


