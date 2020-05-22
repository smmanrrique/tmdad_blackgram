package com.tmdad.gateway.models;

import java.util.Map;

public class OutputMessage {

    private String type;
    private String data;
    private String time;

    public OutputMessage(String type, String data, String time) {
        this.type = type;
        this.data = data;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "OutputMessage{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
