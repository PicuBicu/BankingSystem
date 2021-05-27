package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataDisplayController extends Controller implements Initializable {

    @FXML
    public TextField inputTextField;
    public Text infoText;
    public Button searchButton;
    public ChoiceBox<String> filterChoiceBox;
    public TableView<UserAccount> table;
    public TableColumn<UserAccount, Integer> tabID;
    public TableColumn<UserAccount, String> tabFirstName;
    public TableColumn<UserAccount, String> tabLastName;
    public TableColumn<UserAccount, String> tabPESEL;
    public TableColumn<UserAccount, String> tabAddress;
    public TableColumn<UserAccount, Integer> tabMoney;

    private final String[] filters = {"imie", "nazwisko", "PESEL", "adres"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        filterChoiceBox.getItems().addAll(filters);
        tabID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tabLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tabPESEL.setCellValueFactory(new PropertyValueFactory<>("PESEL"));
        tabAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tabMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
    }

    public void handleFiltering() {
        try {
            String selectedFilter = filterChoiceBox.getValue();
            String searchedWord = inputTextField.getText();
            if (selectedFilter != null && searchedWord != null) {
                List<UserAccount> filteredList = switch (selectedFilter) {
                    case "imie" -> filterFirstByName(searchedWord);
                    case "nazwisko" -> filterByLastName(searchedWord);
                    case "PESEL" -> filterByPESEL(searchedWord);
                    case "adres" -> filterByAddress(searchedWord);
                    default ->  Main.getDatabase().getUsers();
                };
                table.getItems().clear();
                table.getItems().addAll(filteredList);
                infoText.setText("");
            } else {
                infoText.setText("Wprowadź dane");
            }
        } catch (NoSuchUserException e) {
            infoText.setText("Brak użytkowników spełniających podany filtr");
        }
    }

    private List<UserAccount> filterFirstByName(String firstName) throws NoSuchUserException {

        Predicate<UserAccount> byFirstName = userAccount -> userAccount.getFirstName().contains(firstName);
        List<UserAccount> filteredList = Main.getDatabase().getUsers().stream().filter(byFirstName).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by first name");
        }
        return filteredList;
    }

    private List<UserAccount> filterByLastName(String lastName) throws NoSuchUserException {
        Predicate<UserAccount> byLastName = userAccount -> userAccount.getLastName().contains(lastName);
        List<UserAccount> filteredList = Main.getDatabase().getUsers().stream().filter(byLastName).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by last name");
        }
        return filteredList;
    }

    private List<UserAccount> filterByPESEL(String PESEL) throws NoSuchUserException {
        Predicate<UserAccount> byPESEL = userAccount -> userAccount.getPESEL().contains(PESEL);
        List<UserAccount> filteredList = Main.getDatabase().getUsers().stream().filter(byPESEL).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by PESEL");
        }
        return filteredList;
    }

    private List<UserAccount> filterByAddress(String address) throws NoSuchUserException {
        Predicate<UserAccount> byAddress = userAccount -> userAccount.getAddress().contains(address);
        List<UserAccount> filteredList = Main.getDatabase().getUsers().stream().filter(byAddress).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by address");
        }
        return filteredList;
    }

}
