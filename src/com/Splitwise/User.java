package com.Splitwise;

import com.Splitwise.expense.Expense;
import com.Splitwise.expense.Utils;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private double totalBalance;
    private List<Expense> expenseList;
    private long uID;
    private static long NEW_UID = 0;

    public User(String name, String email, String phoneNumber){
        this.uID=NEW_UID++;
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.expenseList=new ArrayList<>();
        this.totalBalance=0;
    }
    public long getuID(){
        return uID;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense){
        expenseList.add(expense);
    }

    public void addBalance(double balance){
        this.totalBalance = Utils.roundOff(this.totalBalance + balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getuID()+ '\''+
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }

    public String showUserExpense(){
        return expenseList.toString();
    }
}
