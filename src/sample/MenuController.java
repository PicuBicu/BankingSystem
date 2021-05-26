package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToRegistrationScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("RegistrationScene.fxml")).load();
        switchToScene(event);
    }

    public void switchToDeleteUserScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("DeleteUserScene.fxml")).load();
        switchToScene(event);
    }

    public void switchToTransactionsScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("TransactionsScene.fxml")).load();
        switchToScene(event);
    }

    public void switchToUserAccountsScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(getClass().getResource("DataDisplayScene.fxml")).load();
        switchToScene(event);
    }

    private void switchToScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


}
