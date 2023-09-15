package com.Splitwise.expense;

import com.Splitwise.split.Split;
import com.Splitwise.exception.IllegalSplitException;
import com.Splitwise.split.PercentSplit;
import com.Splitwise.User;

import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(Double totalAmount, User paidBy, User addedBy, List<Split> splitList, String expenseName) throws IllegalSplitException {
        super(totalAmount, ExpenseType.PERCENT, paidBy, addedBy, splitList, expenseName);
    }

    @Override
    void validateExpense(List<Split> splitList) throws IllegalSplitException {
        double totalPercent = 0;
        for(Split s: splitList){
            totalPercent = totalPercent + s.getShare();
        }
        if(!Utils.isApproxEqual(totalPercent , 100.0)){
            throw new IllegalSplitException("Incorrect Percentage Split" + totalPercent);
        }
    }

    @Override
    void validateSplit(List<Split> splitList) throws IllegalSplitException {
        for(Split s: splitList){
            if(!(s instanceof PercentSplit)){
                throw new IllegalSplitException("Wrong Split! Must be Percent");
            }
        }
    }
}
