package com.tmdad.gateway.models;

public class InputDirectMessage {
    private String type;
    private String ouser;
    private String duser;
    private String text;

    public InputDirectMessage(String type, String ouser, String duser, String text) {
        this.type = type;
        this.ouser = ouser;
        this.duser = duser;
        this.text = text;
    }

    public String getType() { return type; }

    public String getUserFrom() { return ouser; }

    public String getUserTo() { return duser; }

    public String getText() { return text; }
}
