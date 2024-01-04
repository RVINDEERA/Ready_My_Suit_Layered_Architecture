package lk.ijse.rms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.dto.OrderDto;
import lk.ijse.rms.dto.tm.OrderTm;
import lk.ijse.rms.dao.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OnGoingOrderFormController {

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCompleteDate;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFullAmount;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTailorId;

    @FXML
    private AnchorPane paneCustomer;

    @FXML
    private TableView<OrderTm> tblOrder;

    @FXML
    private TextField txtCustomerId2;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtStatus;
    private OrderModel orderModel = new OrderModel();

    public void initialize(){
        setCellValueFactory();
        loadAllOrders();
        tableListener();
    }

    private void tableListener() {
        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(OrderTm row) {
        txtOrderId.setText(row.getOrderId());
        txtStatus.setText(row.getStatus());
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTailorId.setCellValueFactory(new PropertyValueFactory<>("tailorId"));
        colFullAmount.setCellValueFactory(new PropertyValueFactory<>("fullAmount"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCompleteDate.setCellValueFactory(new PropertyValueFactory<>("completeDate"));
    }

    private void loadAllOrders() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<OrderDto> dtoList = orderModel.getAllCustomer();

            for (OrderDto dto : dtoList) {
                obList.add(
                        new OrderTm(
                                dto.getOrderId(),
                                dto.getDate(),
                                dto.getCustomerId(),
                                dto.getTailorId(),
                                dto.getFullAmount(),
                                dto.getAdvance(),
                                dto.getBalance(),
                                dto.getStatus(),
                                dto.getCompleteDate()
                        )
                );
            }

            tblOrder.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtOrderId.getText();
        String status = txtStatus.getText();

        try {
            boolean isUpdated = orderModel.updateOrderStatus(id,status);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, id+" Order Completed").show();
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/onGoingOrder_form.fxml"));
                this.paneCustomer.getChildren().clear();
                this.paneCustomer.getChildren().add(rootNode);
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearch(ActionEvent event) {
        String id =txtCustomerId2.getText();

        try {
            OrderDto orderDto = orderModel.searchOrder(id);
            if (orderDto != null) {
                txtOrderId.setText(orderDto.getOrderId());
                txtStatus.setText(orderDto.getStatus());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }


}
