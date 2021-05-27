package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionsController extends Controller implements Initializable {

    @FXML
    public TextField receiverIDTextField;
    public TextField senderIDTextField;
    public TextField moneyTextField;
    public Text infoText;
    public TableView table;
    public TableColumn<Transaction, Integer> tabID;
    public TableColumn<Transaction, Integer> tabSenderID;
    public TableColumn<Transaction, Integer> tabReceiverID;
    public TableColumn<Transaction, Integer> tabMoney;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("id"));
        tabSenderID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("senderID"));
        tabReceiverID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("receiverID"));
        tabMoney.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("money"));
        table.getItems().addAll(Main.getDatabase().getTransactions());
    }

    public void handleDataReset() {
        senderIDTextField.setText("");
        receiverIDTextField.setText("");
        moneyTextField.setText("");
        infoText.setText("");
    }

    private Transaction prepareTransaction() throws BadDataFormatException{
        Transaction newTransaction = new Transaction();
        newTransaction.setSenderID(senderIDTextField.getText());
        newTransaction.setReceiverID(receiverIDTextField.getText());
        newTransaction.setMoney(moneyTextField.getText());
        newTransaction.setId(Main.getDatabase().getLastFreeTransactionID() + 1);
        return newTransaction;
    }

    public void handleAddingTransaction() {
        try {
            Transaction newTransaction = prepareTransaction();
            transferMoney(newTransaction.getReceiverID(), newTransaction.getSenderID(), newTransaction.getMoney());
            Main.getDatabase().addNewTransaction(newTransaction);
            table.getItems().clear();
            table.getItems().addAll(Main.getDatabase().getTransactions());
            handleDataReset();
        } catch (BadDataFormatException e) {
            System.out.println(e.getMessage());
            infoText.setText("Wprowadź poprawne dane");
        } catch (NoSuchUserException e) {
            System.out.println(e.getMessage() + e.getUserID());
            infoText.setText("Nie ma takiego użytkownika jak " + e.getUserID());
        } catch (NotEnoughMoneyException e) {
            infoText.setText("Brak wystarczających środków na koncie " + e.getUserID());
        }
    }

    private void transferMoney(int receiverID, int senderID, double money) throws NoSuchUserException, NotEnoughMoneyException {

        System.out.println(receiverID + " " + senderID + " " + money);
        Predicate<UserAccount> bySenderID = userAccount -> (userAccount.getId() == senderID);
        List<UserAccount> senderList = Main.getDatabase().getUsers().stream().filter(bySenderID).collect(Collectors.toList());
        if (senderList.isEmpty()) {
            throw new NoSuchUserException("Error while - transferring money, there is no such user as sender", senderID);
        }

        Predicate<UserAccount> byReceiverID = userAccount -> (userAccount.getId() == receiverID);
        List<UserAccount> receiverList = Main.getDatabase().getUsers().stream().filter(byReceiverID).collect(Collectors.toList());
        if (receiverList.isEmpty()) {
            throw new NoSuchUserException("Error while - transferring money, there is no such user as receiver", receiverID);
        }
        double calc = (double)Math.round((senderList.get(0).getMoney() - money) * 100) / 100;
        if (calc < 0) throw new NotEnoughMoneyException("Error while - transferring money from sender to receiver", senderID);
        senderList.get(0).setMoney(calc);
        double receiverMoney = (double)Math.round((receiverList.get(0).getMoney() + money) * 100) / 100;
        receiverList.get(0).setMoney(receiverMoney);

    }

}
