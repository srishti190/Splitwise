package com.Splitwise.split;

import com.Splitwise.User;

public class EqualSplit extends Split{

    public EqualSplit(User user, double amount) {
        super(amount, user, SplitType.EQUAL);
    }
    @Override
    public double getShare() {
        return getAmount();
    }
}
