package com.Splitwise;

import com.Splitwise.exception.IllegalUserId;

import java.util.HashMap;
import java.util.Map;

public class BookKeeper {
    private Map<Long,User> userList;
    private static BookKeeper BookKeeperInstance;

    private BookKeeper(){
       userList=new HashMap<>();
    }

    public static synchronized BookKeeper getInstance(){
       if(BookKeeperInstance==null){
           return BookKeeperInstance=new BookKeeper();
       }
       return BookKeeperInstance;
    }

    public void addUser(User user){
        userList.put(user.getuID(),user);
        System.out.println("New user has been Successfully added ->"+ user.toString());
    }

    public User getUser(Long id) throws IllegalUserId{
        if(!userList.containsKey(id)){
            throw new IllegalUserId("Incorrect user id");
        }
        return userList.get(id);
    }

    public void printAllUsers(){
        System.out.println("Displaying All User with Non-Zero Balance");
        for(Map.Entry<Long,User> users: userList.entrySet()){
            if(users.getValue().getTotalBalance()>0){
                System.out.println(users.getValue().toString());
            }
        }
    }

    public void displayUserData(Long userID) throws IllegalUserId{
        if (!userList.containsKey(userID)){
            throw new IllegalUserId("Users Doesn't Exist");
        }
        System.out.println(userList.get(userID).toString());
    }
}
