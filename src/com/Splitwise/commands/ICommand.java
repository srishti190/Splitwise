package com.Splitwise.commands;

import com.Splitwise.exception.BadCommandException;
import com.Splitwise.exception.IllegalExpenseType;
import com.Splitwise.exception.IllegalSplitException;
import com.Splitwise.exception.IllegalUserId;

public interface ICommand {
    public void executeCommand(String cmd[]) throws BadCommandException,IllegalUserId, IllegalExpenseType, IllegalSplitException;
}
