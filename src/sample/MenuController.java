package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Controller {

    public void switchToRegistrationScene(ActionEvent event) throws IOException {
        setRoot(new FXMLLoader(getClass().getResource("RegistrationScene.fxml")).load());
        switchToScene(event);
    }

    public void switchToDeleteUserScene(ActionEvent event) throws IOException {
        setRoot(new FXMLLoader(getClass().getResource("DeleteUserScene.fxml")).load());
        switchToScene(event);
    }

    public void switchToTransactionsScene(ActionEvent event) throws IOException {
        setRoot(new FXMLLoader(getClass().getResource("TransactionsScene.fxml")).load());
        switchToScene(event);
    }

    public void switchToUserAccountsScene(ActionEvent event) throws IOException {
        setRoot(new FXMLLoader(getClass().getResource("DataDisplayScene.fxml")).load());
        switchToScene(event);
    }

    private void switchToScene(ActionEvent event) {
        setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        setScene(new Scene(getRoot()));
        getStage().setScene(getScene());
    }

    @Override
    public void switchToMainScene(ActionEvent event) throws IOException {}
}
