package com.chat.app.models;

public class Message {
    String message;
    int sent_by;

    public Message()
    {

    }
    public Message(String message, int sent_by) {
        this.message = message;
        this.sent_by = sent_by;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSent_by() {
        return sent_by;
    }

    public void setSent_by(int sent_by) {
        this.sent_by = sent_by;
    }
}
