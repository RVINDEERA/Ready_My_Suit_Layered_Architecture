package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rms.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.*;
import lk.ijse.rms.dto.tm.CartTm;
import lk.ijse.rms.dao.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentCoatFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnRentOrder;

    @FXML
    private JFXComboBox<String> cmbCoatId;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colCoatId;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colRentDate;

    @FXML
    private TableColumn<?, ?> colRentNo;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colColour;
    @FXML
    private TableColumn<?, ?> colMfgDate;
    @FXML
    private TableColumn<?, ?> colAvail;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblMachineId12;

    @FXML
    private Label lblPhoneNmber;

    @FXML
    private Label lblRentDate;

    @FXML
    private Label lblRentNo;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblSize;

    @FXML
    private Label lblType;

    @FXML
    private AnchorPane paneRentCoat;

    @FXML
    private TableView<CartTm> tblRent;

    @FXML
    private TextField txtQty;

    @FXML
    private DatePicker txtReturnDate;

    private CustomerDAOImpl customerModel = new CustomerDAOImpl();
    private CoatModel coatModel = new CoatModel();

    private RentModel rentModel=new RentModel();
    @FXML
    private JFXComboBox<String> cmbRentalBond;

    private RentBondModel rentBondModel = new RentBondModel();

    private RentCoatModel rentCoatModel=new RentCoatModel();

    @FXML
    private Label lblColor;

    @FXML
    private Label lblMfgDate;

    @FXML
    private Label lblAvail;

    @FXML
    private TextField txtRentId;
    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Label lblReturnDate;


    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public  void initialize(){
        generateNextRentOrderId();
        setDate();
        setReturnDate();
        //loadCustomerId();
        loadCoatId();
        loadRentalBond();
        setcellValueFactor();
    }

    private void setReturnDate() {
        lblReturnDate.setText(String.valueOf(LocalDate.now().plusDays(4)));
    }

    private void generateNextRentOrderId() {
        try {
            String previousRentCoatId = lblRentNo.getText();
            String rentCoatId = rentModel.genarateNextRentCoatId();
            lblRentNo.setText(rentCoatId);
            clearFields();
            if (btnClearPressed){
                lblRentNo.setText(previousRentCoatId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    private boolean btnClearPressed = false;

    private void clearFields() {
        cmbCoatId.setValue("");
        cmbRentalBond.setValue("");
        txtPhoneNumber.setText("");
        lblAvail.setText("");
        lblColor.setText("");
        lblSize.setText("");
        lblMfgDate.setText("");
        lblType.setText("");
        lblCusName.setText("");
        lblPhoneNmber.setText("");
        lblUnitPrice.setText("");
        lblReturnDate.setText("");
    }

    //    private void generateNextRentOrderId() {
//        try {
//            String rentId = rentModel.generateNextRentOrderId();
//            lblRentNo.setText(rentId);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
//    }
    private void loadRentalBond() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<RentalBondDto> rentalBondDto = rentBondModel.loadAllBond();

            for (RentalBondDto dto : rentalBondDto) {
                obList.add(dto.getType());
            }
            cmbRentalBond.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setcellValueFactor() {
        colCoatId.setCellValueFactory(new PropertyValueFactory<>("coatId"));
        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colMfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        colAvail.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadCoatId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CoatDto> coatDtos = coatModel.loadAllItems();

            for (CoatDto dto : coatDtos) {
                obList.add(dto.getCoatId());
            }
            cmbCoatId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        lblRentDate.setText(String.valueOf(LocalDate.now()));
    }

//    private void loadCustomerId() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        try {
//            List<CustomerDto> idList = customerModel.getAllCustomer();
//
//            for (CustomerDto dto : idList) {
//                obList.add(dto.getCustomerId());
//            }
//
//            cmbCustomerId.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String coatId = cmbCoatId.getValue();
        String rentDate = lblRentDate.getText();
        String returnDate =lblReturnDate.getText();
        String price = lblUnitPrice.getText();
        String size = lblSize.getText();
        String type = lblType.getText();
        String colour = lblColor.getText();
        String mfgDate = lblMfgDate.getText();
        String availability =lblAvail.getText();
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        CartTm cartTm =new CartTm(coatId,rentDate,returnDate,price,size,type,colour,mfgDate,availability,btn);

        obList.add(cartTm);
        tblRent.setItems(obList);

    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblRent.getSelectionModel().getSelectedIndex();
                obList.remove(focusedIndex);
                tblRent.refresh();
            }
        });
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
    void btnRentedOutOnAction(ActionEvent event) {
        String rentId = lblRentNo.getText();
        String customerId =lblPhoneNmber.getText();
        String rentalBond =cmbRentalBond.getValue();

        List<CartTm> cartTmList = new ArrayList<>();
        for (int i = 0; i < tblRent.getItems().size(); i++) {
            CartTm cartTm = obList.get(i);

            cartTmList.add(cartTm);

            RentCoatDto rentCoatDto = new RentCoatDto(rentId,customerId,rentalBond,cartTmList);
            try {
                boolean isSuccesss=rentCoatModel.placeRentOrder(rentCoatDto);
                if (isSuccesss){
                    new Alert(Alert.AlertType.INFORMATION,"Rent Success").show();
                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/rentCoat_form.fxml"));
                    this.paneRentCoat.getChildren().clear();
                    this.paneRentCoat.getChildren().add(rootNode);
                    generateNextRentOrderId();

                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void cmbCoatOnAction(ActionEvent event) {
        String id = cmbCoatId.getValue();
        try {
            CoatDto coatDto = coatModel.searchCoat(id);
            lblUnitPrice.setText(coatDto.getPrice());
            lblType.setText(coatDto.getType());
            lblSize.setText(coatDto.getSize());
            lblColor.setText(coatDto.getColor());
            lblMfgDate.setText(coatDto.getDate());
            lblAvail.setText(coatDto.getAvailability());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @FXML
//    void cmbCustomerOnAction(ActionEvent event) {
//        String id = cmbCustomerId.getValue();
//
//        try {
//            CustomerDto customerDto =customerModel.searchCustomer(id);
//            lblCusName.setText(customerDto.getFirstName());
//           // lblCusName.setText(customerDto.getLastName());
//            lblPhoneNmber.setText(customerDto.getPhoneNumber());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    @FXML
    void txtPhoneNumberOnAction(ActionEvent event) {
        String id = txtPhoneNumber.getText();
        try {
            CustomerDto customerDto =customerModel.searchCustomer(id);
            if (customerDto != null) {
                lblCusName.setText(customerDto.getFirstName());
                // lblCusName.setText(customerDto.getLastName());
                lblPhoneNmber.setText(customerDto.getCustomerId());
            }else {
                new Alert(Alert.AlertType.ERROR,"Not a registered Customer!").show();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnReportOnActoion(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/repo/RentalCoat_A4.jrxml");
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
