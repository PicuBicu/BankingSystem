package sample;

import java.io.*;
import java.util.ArrayList;

public class BankIOHandler {

    public static void saveUsersToFile(File fileName, ArrayList<UserAccount> arrayList) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName.getPath()));
        out.writeObject(arrayList);
        out.close();
    }

    public static ArrayList<UserAccount> readUsersFromFile(File fileName) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName.getPath()))) {
            ArrayList<UserAccount> usersAsList = (ArrayList<UserAccount>)in.readObject();
            return usersAsList;
        } catch (ClassNotFoundException ignored) {}
        return null;
    }

    public static void saveTransactionsToFile(File fileName, ArrayList<Transaction> arrayList) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName.getPath()));
        out.writeObject(arrayList);
        out.close();
    }

    public static ArrayList<Transaction> readTransactionsFromFile(File fileName) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName.getPath()))) {
            ArrayList<Transaction> transactionsAsList = (ArrayList<Transaction>)in.readObject();
            return transactionsAsList;
        } catch (ClassNotFoundException ignored) {}
        return null;
    }
}
