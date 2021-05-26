package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private BankDatabase database = new BankDatabase();

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getRoot() {
        return root;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setDatabase(BankDatabase database) {
        this.database = database;
    }

    public BankDatabase getDatabase() {
        return this.database;
    }

}
