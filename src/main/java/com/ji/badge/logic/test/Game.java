package com.ji.badge.logic.test;

public class Game {

    private int id;
    private String name;
    private int price;
    private boolean multiplayer;

    public Game(String name, int price, boolean multiplayer) {
        this.name = name;
        this.price = price;
        this.multiplayer = multiplayer;
    }

    public Game(int id, String name, int price, boolean multiplayer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.multiplayer = multiplayer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }
}
