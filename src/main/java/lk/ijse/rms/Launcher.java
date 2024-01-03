package lk.ijse.rms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
       Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/loading_form.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setTitle("Ready My Suit");
        stage.getIcons().add(new javafx.scene.image.Image("/images/logon.png"));
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }

}
//Ryasiru 0710821114
