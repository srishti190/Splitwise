package com.Splitwise.expense;

import com.Splitwise.split.Split;
import com.Splitwise.User;
import com.Splitwise.split.EqualSplit;
import com.Splitwise.exception.IllegalSplitException;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(Double totalAmount, User paidBy, User addedBy, List<Split> splitList, String expenseName) throws IllegalSplitException {
        super(totalAmount, ExpenseType.EQUAL, paidBy, addedBy, splitList, expenseName);
    }

    @Override
    void validateExpense(List<Split> splitList) throws IllegalSplitException{
        Double totalAmount = getTotalAmount();
        Double currentAmount = 0.0;
        for(Split s: splitList){
            double share = Utils.roundOff(s.getShare());
            s.setAmount(share);
            currentAmount += share;
        }
        if(!Utils.isApproxEqual(currentAmount,totalAmount)){
            double remaining = totalAmount - currentAmount;
            for(Split s: splitList){
                if(s.getUser().getuID() == this.getPaidBy().getuID()){
                    s.setAmount(Utils.roundOff(s.getAmount()+remaining));
                }
            }
        }
    }

    @Override
    void validateSplit(List<Split> splitList) throws IllegalSplitException {
        for(Split s: splitList){
            if(!(s instanceof EqualSplit)){
                throw new IllegalSplitException("Wrong Split! Must be Equal");
            }
        }
    }
}
