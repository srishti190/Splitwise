package com.Splitwise.split;

import com.Splitwise.User;

public abstract class Split {
    private double amount;
    private User user;
    private long uID;
    private static long NEW_UID = 0;
    final private SplitType splitType;

    protected Split(double amount, User user, SplitType splitType) {
        this.uID=NEW_UID++;
        this.amount = amount;
        this.user = user;
        this.splitType = splitType;
    }

    public long getuID(){
        return uID;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    abstract public double getShare();

    @Override
    public String toString(){
        return "Split{" +
                "id=" + getuID() +
                ", splitType=" + splitType +
                ", user=" + user.getName() +
                ", amount=" + amount +
                '}';
    }
}
