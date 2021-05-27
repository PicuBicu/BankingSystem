package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class DeleteUserController extends Controller implements Initializable {

    private static final int BANK_ACCOUNT = 0;

    @FXML
    public TextField userIdTextField;
    public Text infoText;
    public TableView<UserAccount> table;
    public TableColumn<UserAccount, Integer> tabID;
    public TableColumn<UserAccount, String> tabFirstName;
    public TableColumn<UserAccount, String> tabLastName;
    public TableColumn<UserAccount, String> tabPESEL;
    public TableColumn<UserAccount, String> tabAddress;
    public TableColumn<UserAccount, Integer> tabMoney;

    private int deleteUserById() throws BadDataFormatException, NoSuchUserException{
        try {
            int id = Integer.parseInt(userIdTextField.getText());
            Predicate<UserAccount> byID = userAccount -> userAccount.getId() == id;
            if (Main.getDatabase().getUsers().stream().filter(byID).count() == 0 || id == BANK_ACCOUNT) {
                throw new NoSuchUserException("Error - there is no such user with given ID");
            }
            Main.getDatabase().getUsers().removeIf(byID);
            return id;
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - converting ID string to ID int");
        }
    }

    public void handleResetData() {
        infoText.setText("");
    }

    public void handleDeletingUser() {
        try {
            int id = deleteUserById();
            infoText.setText("Usunięto użytkownika o id" + id);
            table.getItems().clear();
            table.getItems().addAll(Main.getDatabase().getUsers());
            handleResetData();
        } catch (BadDataFormatException e) {
            infoText.setText("Podaj liczbe całkowitą w ID użytkownika");
        } catch (NoSuchUserException e) {
            infoText.setText("Nie ma podanego użytkownika");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabID.setCellValueFactory(new PropertyValueFactory<UserAccount, Integer>("id"));
        tabFirstName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("firstName"));
        tabLastName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("lastName"));
        tabPESEL.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("PESEL"));
        tabAddress.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("address"));
        tabMoney.setCellValueFactory(new PropertyValueFactory<UserAccount, Integer>("money"));
        table.getItems().addAll(Main.getDatabase().getUsers());
    }

}
