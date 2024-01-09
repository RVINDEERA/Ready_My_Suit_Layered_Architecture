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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.dao.custom.impl.*;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.*;
import lk.ijse.rms.dto.tm.OrderCartTm;
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

import static java.time.LocalDate.*;

public class PlaceOrderFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReport;

    @FXML
    private JFXComboBox<String> cmbItem;

    @FXML
    private JFXComboBox<String> cmbMeasurements;

    @FXML
    private JFXComboBox<String> cmbTailorId;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCusName;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItrmId;
    @FXML
    private Label lblOrderNo;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblType;

    @FXML
    private TextArea txtAreaNote;

    @FXML
    private TableView<OrderCartTm> tblOrder;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colNote;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Label lblMeasurements;

    @FXML
    private AnchorPane paneRentCoat;

    @FXML
    private TextField txtAdvance;
    @FXML
    private TextField txtReqiredDate;

    @FXML
    private DatePicker txtCompletionDate;

    @FXML
    private TextField txtFullAmount;

    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private Label lblCompletionDate;

    @FXML
    private TextField txtQty;

    private CustomerDAOImpl customerModel = new CustomerDAOImpl();
    private PlaceOrderFormModel placeOrderFormModel = new PlaceOrderFormModel();
    OrderDAOImpl orderDAOImpl =new OrderDAOImpl();
    private ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    private ShirtMeasurementDAOImpl shirtMeasurementDAOImpl = new ShirtMeasurementDAOImpl();
    private TrouserDAOImpl trouserDAOImpl = new TrouserDAOImpl();
    private CoatMeasurementDAOImpl coatMeasurementModel = new CoatMeasurementDAOImpl();
    TailorDAOImpl tailorDAOImpl = new TailorDAOImpl();

    private ObservableList<OrderCartTm> obList = FXCollections.observableArrayList();


    public void initialize(){
        setCellValueFactory();
        generateNextOrderId();
        setDate();
        loadAllTailors();
        loadAllItem();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }


    private void loadAllItem() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> idList = itemDAOImpl.getAllItemId();
            for (ItemDto dto : idList){
                obList.add(dto.getType());
            }
            cmbItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllTailors() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TailorDto> tailorDtos = tailorDAOImpl.getAllTailor();

            for (TailorDto dto : tailorDtos) {
                obList.add(dto.getTailorId());
            }
            cmbTailorId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setDate() {
        lblDate.setText(String.valueOf(now()));
    }

    private void generateNextOrderId() {
        try {
            String previousRentCoatId = lblOrderNo.getText();
            String orderId = orderDAOImpl.genarateNextOrderId();
            lblOrderNo.setText(orderId);
            clearFields();
            if (btnClearPressed){
                lblOrderNo.setText(previousRentCoatId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private boolean btnClearPressed = false;
    private void clearFields() {
        cmbTailorId.setValue("");
        txtAreaNote.setText("");
        lblCompletionDate.setText("");
        txtPhoneNumber.setText("");
        lblCustomerId.setText("");
        lblCusName.setText("");
        lblMeasurements.setText("");
        cmbItem.setValue("");
        txtQty.setText("");
        txtFullAmount.setText("");
        txtAdvance.setText("");
        lblBalance.setText("");
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String itemId =lblItrmId.getText();
        String type = cmbItem.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        String note = txtAreaNote.getText();
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        OrderCartTm orderCartTm = new OrderCartTm(itemId,type,qty,note,btn);

        obList.add(orderCartTm);
        tblOrder.setItems(obList);


    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblOrder.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblOrder.refresh();

            }
        });
    }

    @FXML
    void btnRentedOutOnAction(ActionEvent event) {
        String orderId = lblOrderNo.getText();
        String date = lblDate.getText();
        String customerId= lblCustomerId.getText();
        String tailorId = cmbTailorId.getValue();
        double fullAmount = Double.parseDouble(txtFullAmount.getText());
        double advance = Double.parseDouble(txtAdvance.getText());
        double balance = Double.parseDouble(lblBalance.getText());
        String status = lblStatus.getText();
        String completeDate =lblCompletionDate.getText();

        List<OrderCartTm> orderCartTmList = new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            OrderCartTm orderCartTm = obList.get(i);

            orderCartTmList.add(orderCartTm);
        }
        
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(orderId,date,customerId,tailorId,fullAmount,advance,balance,status,completeDate,orderCartTmList);

        try {
            boolean isSuccess = placeOrderFormModel.placeOrder(placeOrderDto);
            if (isSuccess){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/order_form.fxml"));
                this.paneRentCoat.getChildren().clear();
                this.paneRentCoat.getChildren().add(rootNode);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void btnReportOnActoion(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/repo/order_A4.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()

        );
        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void cmbCoatOnAction(ActionEvent event) throws SQLException {
        String item =cmbItem.getValue();
        String id = lblCustomerId.getText();
        String type = cmbItem.getValue();
        ItemDto itemDto = itemDAOImpl.searchItemId(type);
        if (item.equals("SHIRT")){
            if (itemDto !=null){
                lblItrmId.setText(itemDto.getItemId());
            }
                ShirtDto shirtDto = shirtMeasurementDAOImpl.searchMeasurements(id);
                if (shirtDto !=null ){
                    lblMeasurements.setText(shirtDto.getSmId());
                }
        } else if (item.equals("TROUSER")) {
            if (itemDto !=null){
                lblItrmId.setText(itemDto.getItemId());
            }
            TrouserDto trouserDto = trouserDAOImpl.searchMeasurements(id);
            if (trouserDto !=null){
                lblMeasurements.setText(trouserDto.getTrmId());
            }
        }else if (item.equals("COAT")){
            if (itemDto !=null){
                lblItrmId.setText(itemDto.getItemId());
            }
            CoatMeasurementsDto coatMeasurementsDto =coatMeasurementModel.searchMeasurements(id);
            if (coatMeasurementsDto != null){
                lblMeasurements.setText(coatMeasurementsDto.getCmId());
            }
        }

    }

    @FXML
    void txtPhoneNumberOnAction(ActionEvent event) {
        String id = txtPhoneNumber.getText();
        try {
            CustomerDto customerDto =customerModel.searchCustomer(id);
            if (customerDto != null) {
                lblCusName.setText(customerDto.getFirstName());
                // lblCusName.setText(customerDto.getLastName());
                lblCustomerId.setText(customerDto.getCustomerId());
            }else {
                new Alert(Alert.AlertType.ERROR,"Not a registered Customer!").show();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/selectItem_form.fxml"));
        this.paneRentCoat.getChildren().clear();
        this.paneRentCoat.getChildren().add(rootNode);

    }

    @FXML
    void txtAdvanceOnAction(ActionEvent event) {
        double fullamount = Double.parseDouble(txtFullAmount.getText());
        double advance = Double.parseDouble(txtAdvance.getText());
        if (advance >= fullamount){
            new Alert(Alert.AlertType.ERROR,"Invalid!").show();
            return ;
        }
        lblBalance.setText(String.valueOf(fullamount-advance));
    }
    @FXML
    void txtRequiredDatesOnAction(ActionEvent event) {
        int date = Integer.parseInt(txtReqiredDate.getText());
        lblCompletionDate.setText(String.valueOf(LocalDate.now().plusDays(date)));
    }

}
