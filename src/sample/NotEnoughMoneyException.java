package sample;

public class NotEnoughMoneyException extends Exception {

    private int userID;

    public NotEnoughMoneyException(String message, int userID) {
        super(message);
        this.userID = userID;
    }
    public int getUserID() {
        return this.userID;
    }
}
