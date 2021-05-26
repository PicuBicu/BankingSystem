package sample;

import java.util.ArrayList;

public class BankDatabase {

    private ArrayList<UserAccount> users;
    private ArrayList<Transaction> transactions;

    public void setUsers(ArrayList<UserAccount> users) {
        this.users = users;
    }

    public ArrayList<UserAccount> getUsers() {
        return this.users;
    }

    public void addNewUser(UserAccount newUser) {
        this.users.add(newUser);
    }

    public int getLastFreeUserID() {
        int size = this.users.size();
        UserAccount lastUser = this.users.get(size - 1);
        if (lastUser != null) {
            return lastUser.getId();
        } else {
            return -1;
        }
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public void addNewTransaction(Transaction newTransaction) {
        this.transactions.add(newTransaction);
    }

    public int getLastFreeTransactionID() {
        int size = this.transactions.size();
        Transaction lastTransaction = this.transactions.get(size - 1);
        if (lastTransaction != null) {
            return lastTransaction.getId();
        } else {
            return -1;
        }
    }


}
