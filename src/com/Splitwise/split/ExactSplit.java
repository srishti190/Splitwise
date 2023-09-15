package com.Splitwise.split;
import com.Splitwise.User;

public class ExactSplit extends Split{

    public ExactSplit(User user, double amount) {
        super(amount, user, SplitType.EXACT);
    }

    @Override
    public double getShare() {
        return getAmount();
    }
}
