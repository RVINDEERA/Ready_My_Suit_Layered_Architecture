package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.model.CoatModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardFormController {

    @FXML
    private Label lblOngoingOrders;

    @FXML
    private Label lblREntCoats;

    @FXML
    private AnchorPane paneDashboard1;
    @FXML
    private JFXButton btnDashBoard;
    private CoatModel coatModel =new CoatModel();
    @FXML
    private JFXButton btnDashBoard1;

    public void initialize() throws SQLException {

        addCssToBtn(btnDashBoard);
        rentCoatCount();
        ongoingOrdersCount();

    }

    private void ongoingOrdersCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("select count(*) from orders where status = 'Ongoing'");

        ResultSet resultSet = pstm.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        lblOngoingOrders.setText(String.valueOf(count));
    }

    private void rentCoatCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("select count(*) from coat where avail = 'No'");

        ResultSet resultSet =pstm.executeQuery();
        resultSet.next();

        int count = resultSet.getInt(1);
        lblREntCoats.setText(String.valueOf(count));
    }

    @FXML
    void btnMakeNewSuitOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/selectItem_form.fxml"));
        this.paneDashboard1.getChildren().clear();
        this.paneDashboard1.getChildren().add(rootNode);

    }
    private void addCssToBtn(JFXButton mainBtn) {
        this.btnDashBoard.getStyleClass().remove("select_button");
        this.btnDashBoard.getStyleClass().remove("select_button");
        this.btnDashBoard.getStyleClass().add("default_button");
        this.btnDashBoard.getStyleClass().add("default_button");

        mainBtn.getStyleClass().remove("default_button");
        mainBtn.getStyleClass().add("select_button");


    }

    @FXML
    void btnRentCoatOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/rentCoat_form.fxml"));
        this.paneDashboard1.getChildren().clear();
        this.paneDashboard1.getChildren().add(rootNode);


    }
    @FXML
    void btnHandOverOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/broughtCoat_form.fxml"));
        this.paneDashboard1.getChildren().clear();
        this.paneDashboard1.getChildren().add(rootNode);
    }

}
