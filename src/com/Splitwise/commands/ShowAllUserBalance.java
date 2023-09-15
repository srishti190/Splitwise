package com.Splitwise.commands;

import com.Splitwise.BookKeeper;
import com.Splitwise.exception.BadCommandException;

public class ShowAllUserBalance implements ICommand {
    private static ShowAllUserBalance showAllUserBalanceInstance;
    BookKeeper bookKeeper;
    private ShowAllUserBalance() {
        bookKeeper = BookKeeper.getInstance();
    }

    public static synchronized ShowAllUserBalance getInstance() {
        if (showAllUserBalanceInstance == null) {
            showAllUserBalanceInstance = new ShowAllUserBalance();
        }
        return showAllUserBalanceInstance;
    }

    @Override
    public void executeCommand(String[] cmd) throws BadCommandException {
        bookKeeper.printAllUsers();
    }
}