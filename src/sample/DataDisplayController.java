package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataDisplayController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    private BankDatabase database = new BankDatabase();
    private final String[] filters = {"imie", "nazwisko", "PESEL", "adres"};

    public void setDatabase(BankDatabase database) {
        this.database = database;
    }

    public BankDatabase getDatabase() {
        return this.database;
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        root = loader.load();
        MenuController controller = loader.getController();
        controller.setDatabase(database);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        filterChoiceBox.getItems().addAll(filters);
        tabID.setCellValueFactory(new PropertyValueFactory<UserAccount, Integer>("id"));
        tabFirstName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("firstName"));
        tabLastName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("lastName"));
        tabPESEL.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("PESEL"));
        tabAddress.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("address"));
        tabMoney.setCellValueFactory(new PropertyValueFactory<UserAccount, Integer>("money"));
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
                    default ->  database.getUsers();
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
        List<UserAccount> filteredList = database.getUsers().stream().filter(byFirstName).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by first name");
        }
        return filteredList;
    }

    private List<UserAccount> filterByLastName(String lastName) throws NoSuchUserException {
        Predicate<UserAccount> byLastName = userAccount -> userAccount.getLastName().contains(lastName);
        List<UserAccount> filteredList = database.getUsers().stream().filter(byLastName).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by last name");
        }
        return filteredList;
    }

    private List<UserAccount> filterByPESEL(String PESEL) throws NoSuchUserException {
        Predicate<UserAccount> byPESEL = userAccount -> userAccount.getPESEL().contains(PESEL);
        List<UserAccount> filteredList = database.getUsers().stream().filter(byPESEL).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by PESEL");
        }
        return filteredList;
    }

    private List<UserAccount> filterByAddress(String address) throws NoSuchUserException {
        Predicate<UserAccount> byAddress = userAccount -> userAccount.getAddress().toString().contains(address);
        List<UserAccount> filteredList = database.getUsers().stream().filter(byAddress).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new NoSuchUserException("Error while - filtering users table by address");
        }
        return filteredList;
    }

}