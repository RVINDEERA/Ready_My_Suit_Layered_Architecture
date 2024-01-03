package lk.ijse.rms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rms.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private AnchorPane paneLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private LoginModel loginModel = new LoginModel();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        if (userName.isEmpty() ||password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Type Username and Password to Login ").show();
        }

        try {
            boolean isValid = loginModel.userLogin(userName,password);
            if (isValid){
                navigateToMain();
            }else {
                new Alert(Alert.AlertType.ERROR,"Wrong Username or Password ").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void hyperCreateAccOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register_form.fxml"));
        Scene scene = new Scene(rootNode);

        paneLogin.getChildren().clear();
        Stage stage = (Stage) paneLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ready My Suit");
        stage.centerOnScreen();

    }
    private void navigateToMain() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(rootNode);

        paneLogin.getChildren().clear();
        Stage stage = (Stage) paneLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Ready My Suit");
    }

    @FXML
    void txtLoginOnAction(ActionEvent event) throws IOException {
        btnLoginOnAction(new ActionEvent());
    }


}
