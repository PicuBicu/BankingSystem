package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {

    protected Stage stage;
    protected Scene scene;
    protected Parent root;

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("MenuScene.fxml")).load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

}
