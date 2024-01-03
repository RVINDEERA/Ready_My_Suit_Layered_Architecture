//package lk.ijse.rms.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import lk.ijse.rms.dto.BroughtCoatDto;
//import lk.ijse.rms.dto.CoatDto;
//import lk.ijse.rms.dto.tm.BroughtCoatTm;
//import lk.ijse.rms.model.BroughtCoatModel;
//import lk.ijse.rms.model.CustomerModel;
//
//import java.awt.*;
//import java.sql.SQLException;
//import java.util.List;
//
//public class BroughtCoatFormController {
//
//    @FXML
//    private TableColumn<?, ?> colBond;
//
//    @FXML
//    private TableColumn<?, ?> colCoatId;
//
//    @FXML
//    private TableColumn<?, ?> colCustomerId;
//
//    @FXML
//    private TableColumn<?, ?> colHandedOver;
//
//    @FXML
//    private TableColumn<?, ?> colName;
//
//    @FXML
//    private TableColumn<?, ?> colPhone;
//
//    @FXML
//    private TableColumn<?, ?> colRentDate;
//
//    @FXML
//    private TableColumn<?, ?> colRentId;
//
//    @FXML
//    private TableColumn<?, ?> colReturnDate;
//
//    @FXML
//    private Label lblCustomerId;
//
//    @FXML
//    private AnchorPane paneCustomer;
//
//    @FXML
//    private TableView<BroughtCoatTm> tblCoatReturn;
//
//    private CustomerModel customerModel = new CustomerModel();
//    private BroughtCoatModel broughtCoatModel = new BroughtCoatModel();
//
//    public void initialize() {
//        setCellValueFactory();
//        loadData();
//
//    }
//
//    private void setCellValueFactory() {
//        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//        colCoatId.setCellValueFactory(new PropertyValueFactory<>("coatId"));
//        colBond.setCellValueFactory(new PropertyValueFactory<>("rebtalBond"));
//        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
//        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
//        colHandedOver.setCellValueFactory(new PropertyValueFactory<>("btn"));
//    }
//
//
//    private void loadData() {
//        ObservableList<BroughtCoatTm> obList = FXCollections.observableArrayList();
//
//        try {
//            List<BroughtCoatDto> dtoList = broughtCoatModel.loadAllItems();
//
//            for (BroughtCoatDto dto : dtoList){
//                obList.add(new BroughtCoatTm(
//                        dto.getRentId(),
//                        dto.getCustomerId(),
//                        dto.getFirstName(),
//                        dto.getPhoneNUmber(),
//                        dto.getCoatId(),
//                        dto.getRebtalBond(),
//                        dto.getRentDate(),
//                        dto.getReturnDate(),
//                        new Button("Handed Over")
//
//                ));
//            }
//            tblCoatReturn.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void setUpdateBtnAction(Button btn){
//
//    }
//}
