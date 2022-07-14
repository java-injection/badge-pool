package com.ji.badge.logic.test;

import com.ji.badge.cli.CliColors;
import com.ji.badge.cli.CliLogger;
import com.ji.badge.logic.test.exceptions.AttemptSecondLoginException;
import com.ji.badge.logic.test.exceptions.IdNotExistingException;
import com.ji.badge.logic.test.exceptions.UsernameAlreadyExisting;
import com.ji.badge.logic.test.exceptions.UsernameNotExistingException;

import java.util.*;
import java.util.stream.Collectors;

public class FakeDB {

    private static FakeDB _instance = null;
    private Map<String, Account> userTable = new HashMap<>();
    private List<String> onlineAccount = new LinkedList<>(); //usernames
    private int progressiveID = 1;



    private FakeDB(){

    }

    public static FakeDB getInstance(){
        if(_instance == null){
            _instance = new FakeDB();
        }
        return _instance;
    }

    public boolean login(String username) throws UsernameNotExistingException, AttemptSecondLoginException {
        if(!this.userTable.containsKey(username)){
            throw new UsernameNotExistingException(username);
        }
        if(!this.onlineAccount.contains(username)){
            throw new AttemptSecondLoginException(username);
        }
        this.onlineAccount.add(username);
        return true;
    }

    public int register(String username, String password) throws UsernameAlreadyExisting {
        if(this.userTable.containsKey(username)){
            throw new UsernameAlreadyExisting(username);
        }

        Account account = new Account(progressiveID, username, password,100);
        progressiveID++;
        this.userTable.put(username, account);
        return account.getId();
    }

    public Account getAccountByID(int id) throws IdNotExistingException {
        return this.userTable.values().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IdNotExistingException(id));
    }

    public void addMoneyToAccount(int id, int money) throws IdNotExistingException {
        this.userTable.values().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IdNotExistingException(id)).addMoney(money);
    }

    public Collection<Account> getAccounts(){
        return this.userTable.values();
    }

    public List<Account> getOnlineAccounts(){
        return this.userTable.values().stream()
                .filter(a -> this.onlineAccount.contains(a.getUsername()))
                .collect(Collectors.toList());
    }



    public void initWithFakeData(){
        try {
            CliLogger.info("adding 4 fake accounts.. ");
            this.register("luca.coraci","123");
            this.register("amicone","1234");
            this.register("capuozzo","111123");
            this.register("tossico","0000");
            CliLogger.info("fake accounts have been added.");
        }
        catch (UsernameAlreadyExisting e) {
            throw new RuntimeException(e);
        }
    }




}
