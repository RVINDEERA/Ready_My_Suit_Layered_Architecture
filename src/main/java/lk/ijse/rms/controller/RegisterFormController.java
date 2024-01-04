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
import lk.ijse.rms.dto.UserDto;
import lk.ijse.rms.dao.RegisterModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterFormController {

    @FXML
    private AnchorPane paneRegister;

    @FXML
    private PasswordField txtConPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private RegisterModel registerModel = new RegisterModel();

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String conpassword = txtConPassword.getText();

        if (email.isEmpty() || userName.isEmpty() || password.isEmpty() || conpassword.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Fill all fields").show();
            return;
        }else if (!conpassword.equals(password)){
            new Alert(Alert.AlertType.ERROR,"Password Confirmation does not match").show();
            return;
        }
        UserDto userDto = new UserDto(email,userName,password);
        boolean isValidate= validateUser(userDto);
        if (isValidate) {

            try {
                boolean isDuplicated = registerModel.checkDuplicate(email, password);
                if (isDuplicated) {
                    new Alert(Alert.AlertType.ERROR, "Email or Password already taken try another ").show();
                    return;
                }
                boolean isRegisterd = registerModel.userRegister(userDto);

                if (isRegisterd) {
                    new Alert(Alert.AlertType.INFORMATION, "New Account Created ").showAndWait();navigateToMain();
                    navigateToMain();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);

            }
        }

    }

    private boolean validateUser(UserDto userDto) {
        boolean matches2 = Pattern.matches("\\w{4,}",userDto.getUserName());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid Username \n Username should be Letters only!").show();
            return false;
        }
        boolean matches3 = Pattern.matches(".{8}",userDto.getPassword());
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR, "Password Not Strong\nPassword should be 8 Digits! ").show();
            return false;

        }
        return true;

    }

    @FXML
    void hyperLoginOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        paneRegister.getChildren().clear();
        Stage primaryStage = (Stage) paneRegister.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");

    }
    private void navigateToMain() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);

        paneRegister.getChildren().clear();
        Stage stage = (Stage) paneRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Ready My Suit");


    }
    private void navigateToAdminPassword() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/adminConfirmation_form.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Admin Password");
        stage.show();


    }

}
