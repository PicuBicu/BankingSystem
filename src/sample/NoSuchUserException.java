package sample;

public class NoSuchUserException extends Exception{
    private int userID;

    public NoSuchUserException(String message, int userID) {
        super(message);
    }

    public NoSuchUserException(String message) {
        super(message);
    }
    public int getUserID() {
        return this.userID;
    }
}
