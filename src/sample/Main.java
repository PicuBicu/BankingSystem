package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private static BankDatabase database;

    public static BankDatabase getDatabase() {
        return database;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Main.database = prepareDatabase();
            Parent root = new FXMLLoader(getClass().getResource("MenuScene.fxml")).load();
            primaryStage.setTitle("System bankowy");
            primaryStage.getIcons().add(new Image("sample/bank.png"));
            primaryStage.setOnCloseRequest(windowEvent -> {
                try {
                    System.out.println("XD");
                    BankIOHandler.saveTransactionsToFile(new File("transactions.txt"), Main.getDatabase().getTransactions());
                    BankIOHandler.saveUsersToFile(new File("users.txt"), Main.getDatabase().getUsers());
                } catch (IOException e) {
                    System.exit(-1);
                }
            });
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static BankDatabase prepareDatabase() {
        BankDatabase database = new BankDatabase();
        try {
            File usersFile = new File("users.txt");
            File transactionsFile = new File("transactions.txt");
            createFileIfNotExists(usersFile);
            createFileIfNotExists(transactionsFile);
            database.setUsers(prepareUsersTable(new File("users.txt")));
            database.setTransactions(prepareTransactionsTable(new File("transactions.txt")));
        } catch (IOException e) {
            System.exit(-1);
        }
        return database;
    }

    private static ArrayList<UserAccount> prepareUsersTable(File fileName) throws IOException {
        ArrayList<UserAccount> usersTable = new ArrayList<UserAccount>();
        try {
            usersTable = BankIOHandler.readUsersFromFile(new File("users.txt"));
        } catch (EOFException e) {
            usersTable.add(new UserAccount());
            BankIOHandler.saveUsersToFile(new File("users.txt"), usersTable);
        }
        return usersTable;
    }

    private static ArrayList<Transaction> prepareTransactionsTable(File fileName) throws IOException {
        ArrayList<Transaction> transactionsTable = new ArrayList<Transaction>();
        try {
            transactionsTable = BankIOHandler.readTransactionsFromFile(new File("transactions.txt"));
        } catch (EOFException e) {
            transactionsTable.add(new Transaction());
            BankIOHandler.saveTransactionsToFile(new File("transactions.txt"), transactionsTable);
        }
        return transactionsTable;
    }

    private static void createFileIfNotExists(File fileName) throws IOException {
        if (!fileName.exists()) {
            fileName.createNewFile();
        }
    }

}
