package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RegistrationController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Text infoText;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField PESELTextField;
    public TextField townTextField;
    public TextField streetTextField;
    public TextField houseNumbersTextField;
    public TextField moneyTextField;

    private BankDatabase database;

    public void setDatabase(BankDatabase database) {
        this.database = database;
    }

    public BankDatabase getDatabase() {
        return this.database;
    }

    public void getData(ActionEvent event) {
        try {
            UserAccount newAccount = prepareFullUserData();
            resetData(event);
            database.addNewUser(newAccount);
            BankIOHandler.saveUsersToFile(new File("users.txt"), database.getUsers());
            infoText.setText("Dodano nowe konto");
        } catch (BadDataFormatException e) {
            infoText.setText("Wprowadz poprawne dane");
        } catch (FileNotFoundException e) {
            infoText.setText("Nie udało się uruchomic bazy danych");
        } catch (IOException e) {
            infoText.setText("Bład zapisu użytkownika do bazy danych");
            e.printStackTrace();
        }
    }

    public void resetData(ActionEvent event) {
        infoText.setText("Zresetowano dane");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        PESELTextField.setText("");
        townTextField.setText("");
        streetTextField.setText("");
        houseNumbersTextField.setText("");
        moneyTextField.setText("");
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        root = loader.load();
        MenuController controller = loader.getController();
        controller.setDatabase(this.database);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    private UserAccount prepareFullUserData() throws BadDataFormatException {
        UserAccount newAccount = new UserAccount();
        newAccount.setFirstName(firstNameTextField.getText());
        newAccount.setLastName(lastNameTextField.getText());
        newAccount.setPESEL(PESELTextField.getText() + "L");
        newAccount.setMoney(moneyTextField.getText());
        Address newAddress = new Address();
        newAddress.setTown(townTextField.getText());
        newAddress.setStreet(streetTextField.getText());
        newAddress.setHouseNumbers(houseNumbersTextField.getText());
        newAccount.setAddress(newAddress);
        newAccount.setId(Integer.toString(database.getLastFreeUserID() + 1));
        return newAccount;
    }
}
