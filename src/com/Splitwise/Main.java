package com.Splitwise;

import com.Splitwise.commands.CommandFactory;
import com.Splitwise.exception.IllegalExpenseType;
import com.Splitwise.exception.IllegalSplitException;
import com.Splitwise.exception.IllegalUserId;
import com.Splitwise.exception.BadCommandException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalSplitException, IllegalUserId, IllegalExpenseType {

        BookKeeper bookKeeper=BookKeeper.getInstance();
        User u1 = new User("mickey","mickey@mickey.com","111");
        User u2 = new User("mini","mini@mini.com","222");
        User u3 = new User("pluto","pluto@pluto.com","333");

        bookKeeper.addUser(u1);
        bookKeeper.addUser(u2);
        bookKeeper.addUser(u3);

        System.out.println(u1.toString());
        System.out.println(u2.toString());
        System.out.println(u3.toString());

        Scanner sc= new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            String[] cmd = sc.nextLine().split(" ");
            try {
                CommandFactory.getInstance().executeCommand(cmd);
            } catch (BadCommandException b){
                System.out.println("BadCommand Exception due to ==>" + b.getCause());
            }
        }
    }
}
