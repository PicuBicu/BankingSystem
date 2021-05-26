package sample;

import java.io.Serializable;

public class Transaction implements Serializable {

    private int id;
    private int senderID;
    private int receiverID;
    private double money;

    static final long serialVersionUID = -7588980448633010399L;

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderID(String senderID) throws BadDataFormatException {
        try {
            this.senderID = Integer.parseInt(senderID);
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - setting sender id");
        }
    }

    public void setReceiverID(String receiverID) throws BadDataFormatException {
        try {
            this.receiverID = Integer.parseInt(receiverID);
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - setting receiver id");
        }
    }

    public void setMoney(String money) throws BadDataFormatException {
        try {
            this.money = (double)Math.round(Double.parseDouble(money) * 100) / 100;
        } catch (NumberFormatException e) {
            throw new BadDataFormatException("Error while - setting money");
        }
    }

    public Transaction() {
        this.id = 0;
        this.senderID = 0;
        this.receiverID = 0;
        this.money = 0;
    }

    public Transaction(int id, int senderID, int receiverID, double money) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.money = (double)Math.round(money * 100) / 100;
    }

    public int getId() {
        return id;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public double getMoney() {
        return money;
    }

}
