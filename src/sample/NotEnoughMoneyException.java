package sample;

public class NotEnoughMoneyException extends Exception {

    private int userID;

    public NotEnoughMoneyException(String s, int userID) {
        super(s);
        this.userID = userID;
    }
    public int getUserID() {
        return this.userID;
    }
}
