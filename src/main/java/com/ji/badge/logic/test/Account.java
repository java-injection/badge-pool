package com.ji.badge.logic.test;

import java.util.List;

public class Account {

    private int id;
    private String username;
    private String password;
    private int money;
    private List<Game> games;

    public Account(){

    }

    public Account(int id, String username, String password, int money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public Account(String username, String password, int money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.money = money;
    }


    public Account(String username, String password, int money, List<Game> games) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.money = money;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money){
        this.money+=money;
    }
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game){
        this.games.add(game);
    }
}
