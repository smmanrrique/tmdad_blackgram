package com.tmdad.gateway.models;

public class InputGroupMessage {
    private String type;
    private String ouser;
    private String group;
    private String text;

    public InputGroupMessage(String type, String ouser, String group, String text) {
        this.type = type;
        this.ouser = ouser;
        this.group = group;
        this.text = text;
    }

    public String getType() { return type; }

    public String getUserFrom() { return ouser; }

    public String getGroup() { return group; }

    public String getText() { return text; }
}
