package com.Splitwise.commands;

import com.Splitwise.BookKeeper;
import com.Splitwise.User;
import com.Splitwise.exception.BadCommandException;
import com.Splitwise.exception.IllegalExpenseType;
import com.Splitwise.exception.IllegalSplitException;
import com.Splitwise.exception.IllegalUserId;
import com.Splitwise.expense.*;
import com.Splitwise.split.EqualSplit;
import com.Splitwise.split.ExactSplit;
import com.Splitwise.split.PercentSplit;
import com.Splitwise.split.Split;

import java.util.ArrayList;
import java.util.List;

public class AddExpenseCommand implements  ICommand {


    private static AddExpenseCommand addExpenseCommandInstance;
    BookKeeper bookKeeper;
    private AddExpenseCommand() {
        bookKeeper = BookKeeper.getInstance();
    }

    public static synchronized AddExpenseCommand getInstance() {
        if (addExpenseCommandInstance == null) {
            addExpenseCommandInstance = new AddExpenseCommand();
        }
        return addExpenseCommandInstance;
    }

    @Override
    public void executeCommand(String[] cmd) throws BadCommandException, IllegalUserId, IllegalExpenseType, IllegalSplitException {
        String expenseName = cmd[1];
        Double totalAmount = Double.valueOf(cmd[2]);
        User paidBy = bookKeeper.getUser(Long.valueOf(cmd[3]));
        User createdBy = bookKeeper.getUser(Long.valueOf(cmd[4]));
        Long totalUsers = Long.valueOf(cmd[5]);
        List<Split> splitList = new ArrayList<>();
        ExpenseType expenseType = Utils.expenseTypeFromString(cmd[6]);

        if(expenseType == ExpenseType.EQUAL){
            Double getEachShare = totalAmount/totalUsers;
            for(int userCount = 0;userCount < totalUsers ; userCount++){
                Long userId = Long.valueOf(cmd[7+userCount]);
                User user = bookKeeper.getUser(userId);
                splitList.add(new EqualSplit(user,getEachShare));
            }
            Expense newExpenseCreated = new EqualExpense(totalAmount,paidBy,createdBy,splitList, expenseName);
            for(Split s: newExpenseCreated.getSplitList()){
                s.getUser().addBalance(s.getAmount());
                s.getUser().addExpense(newExpenseCreated);
            }

        }
        else if(expenseType == ExpenseType.EXACT){
            for(int userCount = 0;userCount < totalUsers ; userCount++){
                Long userId = Long.valueOf(cmd[7+userCount]);
                User user = bookKeeper.getUser(userId);
                Double getUserShare = Double.valueOf(cmd[Math.toIntExact(7 + totalUsers + userCount)]);
                splitList.add(new ExactSplit(user,getUserShare));
            }
            Expense newExpenseCreated = new ExactExpense(totalAmount,paidBy,createdBy,splitList, expenseName);
            for(Split s: newExpenseCreated.getSplitList()){
                s.getUser().addBalance(s.getAmount());
                s.getUser().addExpense(newExpenseCreated);
            }
        }
        else if(expenseType == ExpenseType.PERCENT){
            for(int userCount = 0;userCount < totalUsers;userCount++){
                Long userId = Long.valueOf(cmd[7+userCount]);
                User user = bookKeeper.getUser(userId);
                Double getPercent = Double.valueOf(cmd[Math.toIntExact(7 + totalUsers + userCount)]);
                Double userShare = Utils.getAmountFromPercent(totalAmount,getPercent);
                splitList.add(new PercentSplit(user,userShare,getPercent));
            }
            Expense newExpenseCreated = new PercentExpense(totalAmount,paidBy,createdBy,splitList, expenseName);
            for(Split s: newExpenseCreated.getSplitList()){
                s.getUser().addBalance(s.getAmount());
                s.getUser().addExpense(newExpenseCreated);
            }
        }
        System.out.println("Expense Successfully Added");

    }
}