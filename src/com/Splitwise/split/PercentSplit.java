package com.Splitwise.split;
import com.Splitwise.User;

public class PercentSplit extends Split{
    private double percent;

    public PercentSplit(User user, double amount, double percent) {
        super(amount, user, SplitType.PERCENT);
        this.percent = percent;
    }

    @Override
    public double getShare() {
        return getPercent();
    }

    public double getPercent(){
        return percent;
    }
}
