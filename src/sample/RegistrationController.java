package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegistrationController extends Controller{

    @FXML
    public Text infoText;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField PESELTextField;
    public TextField townTextField;
    public TextField streetTextField;
    public TextField houseNumbersTextField;
    public TextField moneyTextField;

    public void getData(ActionEvent event) {
        try {
            UserAccount newAccount = prepareFullUserData();
            resetData(event);
            Main.getDatabase().addNewUser(newAccount);
            infoText.setText("Dodano nowe konto");
        } catch (BadDataFormatException e) {
            infoText.setText("Wprowadz poprawne dane");
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
        newAccount.setId(Integer.toString(Main.getDatabase().getLastFreeUserID() + 1));
        return newAccount;
    }
}
