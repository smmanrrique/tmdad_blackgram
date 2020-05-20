package com.tmdad.gateway.models;

public class InputAllMessage {
    private String type;
    private String ouser;
    private String text;

    public InputAllMessage(String type, String ouser, String text) {
        this.type = type;
        this.ouser = ouser;
        this.text = text;
    }

    public String getType() { return type; }

    public String getUserFrom() { return ouser; }

    public String getText() { return text; }
}
