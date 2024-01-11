package lk.ijse.rms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.bo.BOFactory;
import lk.ijse.rms.bo.custom.TailorBo;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.TailorDto;
import lk.ijse.rms.dto.tm.TailorTm;
import lk.ijse.rms.dao.custom.impl.TailorDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class TailorFormController {
    @FXML
    private Label lblTailorId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colSallery;

    @FXML
    private AnchorPane paneTailor;

    @FXML
    private TableView<TailorTm> tblTailor;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSallery;

    @FXML
    private TextField txtTailorId;

    @FXML
    private TextField txtTailorId2;


     TailorBo tailorDAOImpl = (TailorBo) BOFactory.getBoFactory().getDAO(BOFactory.Botypes.TAILOR);

    public  void initialize(){
        genarateNextCustomerId();
        setCellValueFactory();
        loadAllTailor();
        tableListner();
    }

    private void tableListner() {
        tblTailor.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(TailorTm newValue) {
        lblTailorId.setText(newValue.getTailorId());
        txtFirstName.setText(newValue.getFirstName());
        txtLastName.setText(newValue.getLastName());
        txtNic.setText(newValue.getTailorNIC());
        txtAddress.setText(newValue.getAddress());
        txtPhoneNumber.setText(newValue.getPhoneNumber());
        txtSallery.setText(newValue.getSalary());
    }

    private void loadAllTailor() {
        TailorDto tailorDto = new TailorDto();
        ObservableList<TailorTm>observableList = FXCollections.observableArrayList();
        try {
            observableList.clear();
            List<TailorDto> dtoList= tailorDAOImpl.getAll();
            for (TailorDto dto : dtoList){
                observableList.add(
                        new TailorTm(
                                dto.getTailorId(),
                                dto.getFirstName(),
                                dto.getLastName(),
                                dto.getTailorNIC(),
                                dto.getAddress(),
                                dto.getPhoneNumber(),
                                dto.getSalary()
                        )
                );
            }
            tblTailor.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("tailorId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("tailorNIC"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colSallery.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void genarateNextCustomerId() {
        try {
            String previousTailorID = lblTailorId.getText();
            String tailorID = tailorDAOImpl.generateNextID();
            lblTailorId.setText(tailorID);
            clearFields();
            if (btnClearPressed){
                lblTailorId.setText(previousTailorID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean btnClearPressed = false;



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtNic.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtSallery.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String tailorId = txtTailorId2.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Delete Tailor "+tailorId+" ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType=alert.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    boolean isDeleted = tailorDAOImpl.delete(tailorId);
                    if (isDeleted) {
                        clearFields();
                        loadAllTailor();
                        txtTailorId2.clear();
                        new Alert(Alert.AlertType.INFORMATION, "Tailor " + tailorId + "deleted Successful").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

                }
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String tailorId = lblTailorId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String nic = txtNic.getText();
        String address= txtAddress.getText();
        String phone = txtPhoneNumber.getText();
        String salary = txtSallery.getText();

     /*   if (fName.isEmpty()||lName.isEmpty()||nic.isEmpty()||address.isEmpty()||phone.isEmpty()||salary.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Fill all fields").show();
            return;
        }*/
        TailorDto tailorDto = new TailorDto(tailorId,fName,lName,nic,address,phone,salary);
        boolean isValid = validateTailor(tailorDto);
        if (isValid) {
            try {
                boolean isSaved = tailorDAOImpl.save(tailorDto);
                if (isSaved) {
                    loadAllTailor();
                    genarateNextCustomerId();
                    clearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Tailor Saved Successfully").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean validateTailor(TailorDto tailorDto) {
        boolean matches1 = Pattern.matches("\\w{3,}",tailorDto.getFirstName());
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Name \n Name should be Letters only!").show();
            return false;
        }
        boolean matches2 = Pattern.matches("\\w{4,}",tailorDto.getLastName());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Name \n Name should be Letters only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches("\\d{10}(v)|\\d{12}",tailorDto.getTailorNIC());
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid NIC").show();
            return false;
        }
        boolean matches4 = Pattern.matches("((\\d{5})?((.?)\\d{2})?)",tailorDto.getSalary());
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Salary Should be Numbers Only!").show();
            return false;
        }
        boolean matches5 = Pattern.matches("(0)\\d{9}",tailorDto.getPhoneNumber());
        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Invalid Phone Number").show();
            return false;
        }
        boolean matches6 = Pattern.matches(".{10,50}",tailorDto.getAddress());
        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Address is very short!\nUse following Structure \"NUMBER,ROAD,CITY\"").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String tailorId = lblTailorId.getText();
        String fName = txtFirstName.getText();
        String lName = txtLastName.getText();
        String nic = txtNic.getText();
        String address= txtAddress.getText();
        String phone = txtPhoneNumber.getText();
        String salary = txtSallery.getText();

        TailorDto tailorDto= new TailorDto(tailorId,fName,lName,nic,address,phone,salary);
        boolean isValid = validateTailor(tailorDto);
        if (isValid) {
            try {
                boolean isUpdated = tailorDAOImpl.update(tailorDto);
                if (isUpdated) {
                    clearFields();
                    genarateNextCustomerId();
                    txtTailorId2.clear();
                    new Alert(Alert.AlertType.INFORMATION, "Tailor Updated Successfully ").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String tailorId = txtTailorId2.getText();

        try {
            TailorDto tailorDto= tailorDAOImpl.search(tailorId);
            if (tailorDto != null){
                lblTailorId.setText(tailorDto.getTailorId());
                txtFirstName.setText(tailorDto.getFirstName());
                txtLastName.setText(tailorDto.getLastName());
                txtNic.setText(tailorDto.getTailorNIC());
                txtAddress.setText(tailorDto.getAddress());
                txtPhoneNumber.setText(tailorDto.getPhoneNumber());
                txtSallery.setText(tailorDto.getSalary());
            }else {
                lblTailorId.setText("");
                txtTailorId2.setText("");
                new Alert(Alert.AlertType.ERROR,"Invalid Tailor Id!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/repo/Tailor_A4.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()

        );
        JasperViewer.viewReport(jasperPrint,false);

    }

}
