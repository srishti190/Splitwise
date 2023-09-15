package com.Splitwise.commands;

import java.util.HashMap;
import java.util.Map;

import com.Splitwise.exception.BadCommandException;
import com.Splitwise.exception.IllegalExpenseType;
import com.Splitwise.exception.IllegalSplitException;
import com.Splitwise.exception.IllegalUserId;

public class CommandFactory implements ICommand{

    private static CommandFactory commandFactoryInstance;
    private Map<String,ICommand> commandMap;

    private CommandFactory() {
        commandMap=new HashMap<>();
        commandMap.put("Add_User", AddUserCommand.getInstance());
        commandMap.put("Add_Expense", AddExpenseCommand.getInstance());
        commandMap.put("Show_User_Data", ShowUserInfoCommand.getInstance());
        commandMap.put("Show_All_User_Balance", ShowAllUserBalance.getInstance());
        commandMap.put("Show_User_Expense", ShowUserExpense.getInstance());
    }

    public Map<String, ICommand> getCommandMap() {
        return commandMap;
    }

    public static synchronized CommandFactory getInstance() {
        if (commandFactoryInstance == null) {
            commandFactoryInstance = new CommandFactory();
        }
        return commandFactoryInstance;
    }

    @Override
    public void executeCommand(String[] cmd) throws BadCommandException, IllegalUserId, IllegalExpenseType, IllegalSplitException {
        if(!commandMap.containsKey(cmd[0])) {
            throw new BadCommandException("InCorrect/Illegal Command ->" + cmd[0]);
        }
        getCommandMap().get(cmd[0]).executeCommand(cmd);

    }
}
