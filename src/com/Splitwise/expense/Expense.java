package com.Splitwise.expense;

import com.Splitwise.User;
import com.Splitwise.split.Split;
import com.Splitwise.exception.IllegalSplitException;
import jdk.jshell.EvalException;

import java.util.List;

public abstract class Expense {
    private String expenseName;
    private User paidBy;
    private User addedBy;
    private List<Split> splitList;
    private double totalAmount;
    final private ExpenseType expenseType;
    private long uID;
    private static long NEW_UID = 0;

    public Expense(Double totalAmount, ExpenseType expenseType, User paidBy, User createdBy, List<Split> splitList, String expenseName) throws IllegalSplitException {
        this.uID=NEW_UID++;
        this.expenseName = expenseName;
        this.setTotalAmount(totalAmount);
        this.expenseType = expenseType;
        this.setPaidBy(paidBy);
        this.setAddedBy(createdBy);
        this.setSplitList(splitList);
    }

    public long getuID(){
        return uID;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public List<Split> getSplitList() {
        return splitList;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public void setSplitList(List<Split> splitList) throws IllegalSplitException{
        validateSplit(splitList);
        validateExpense(splitList);
        this.splitList = splitList;
    }

    abstract void validateExpense(List<Split> splitList) throws IllegalSplitException;
    abstract void validateSplit(List<Split> splitList) throws IllegalSplitException;

    @Override
    public String toString() {
        return "Expense{"+
                "totalAmount"+totalAmount+
                ",expenseType"+expenseType+
                ", paidBy=" + paidBy.getName() +
                ", createdBy=" + addedBy.getName() +
                ", splitList=" + splitList +
                ", expenseName='" + expenseName + '\'' +
                '}';
    }
}
