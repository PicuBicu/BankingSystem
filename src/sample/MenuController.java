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
    private BankDatabase database = new BankDatabase();

    public void setDatabase(BankDatabase database) {
        this.database = database;
    }

    public BankDatabase getDatabase() {
        return this.database;
    }

    public void switchToRegistrationScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationScene.fxml"));
        root = loader.load();
        RegistrationController controller = loader.getController();
        controller.setDatabase(this.database);
        switchToScene(event);
    }

    public void switchToDeleteUserScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteUserScene.fxml"));
        root = loader.load();
        DeleteUserController controller = loader.getController();
        controller.setDatabase(this.database);
        switchToScene(event);
    }

    public void switchToTransactionsScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionsScene.fxml"));
        root = loader.load();
        TransactionsController controller = loader.getController();
        controller.setDatabase(this.database);
        switchToScene(event);
    }

    public void switchToUserAccountsScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataDisplayScene.fxml"));
        root = loader.load();
        DataDisplayController controller = loader.getController();
        controller.setDatabase(this.database);
        switchToScene(event);
    }

    private void switchToScene(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


}
