package lk.ijse.rms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MainFormController {

    @FXML
    private AnchorPane paneDashboard;

    @FXML
    private AnchorPane paneMain;
    @FXML
    private JFXButton btnMain;

    public void initialize() throws IOException {
        addCssToBtn(btnMain);
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dasboard_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);
        setDate();
        setTime();

    }

    private void addCssToBtn(JFXButton mainBtn) {
        this.btnMain.getStyleClass().remove("select_button");
        this.btnMain.getStyleClass().add("default_button");

        mainBtn.getStyleClass().remove("default_button");
        mainBtn.getStyleClass().add("select_button");


    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);

    }

    @FXML
    void btnFabricsOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/fabric_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dasboard_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);


    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do You Want to Logout ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType>buttonType=alert.showAndWait();
        if (buttonType.isPresent()){
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)){
                paneDashboard.getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
                stage.getIcons().add(new javafx.scene.image.Image("/images/logon.png"));
                stage.centerOnScreen();

                stage.show();
            }
        }

    }

    @FXML
    void btnMachineOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/machine_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);
    }
    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);


    }
    @FXML
    void btnMeasurementsOnAction(ActionEvent event) {

    }

    @FXML
    void btnTailorsOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/tailor_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);

    }

    @FXML
    void btnCoatOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/coat_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);
    }
    @FXML
    void btnOrdersOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/onGoingOrder_form.fxml"));
        this.paneDashboard.getChildren().clear();
        this.paneDashboard.getChildren().add(rootNode);
    }

    @FXML
    private Label lblDate;
    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    private Label lblTime;

    private void setTime() {
        Platform.runLater(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
                String timeNow = LocalTime.now().format(formatter);
                lblTime.setText(timeNow);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });
    }


}
