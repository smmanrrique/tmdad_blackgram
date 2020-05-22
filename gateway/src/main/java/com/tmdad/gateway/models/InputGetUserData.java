package com.tmdad.gateway.models;

public class InputGetUserData {
    private String action;
    private String user;

    public InputGetUserData(String type, String user) {
        this.action = type;
        this.user   = user;
    }

    public String getAction() { return action; }

    public String getUser() {
        return user;
    }

    public String toString() {
        return "Action: " + action + " User: " + user;
    }
}
