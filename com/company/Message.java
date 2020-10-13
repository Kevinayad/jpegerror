package com.company;

public class Message {

    private String sender;
    private String receiver;
    private String content;
    private boolean isReaded;

    public Message(String sender, String receiver){
        this.sender = sender;
        this.receiver = receiver;
        this.isReaded = false;

    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean getReadedStatus() {
        return isReaded;
    }

    public String getContent() {
        return content;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setReadedStatus(boolean readed) {
        isReaded = readed;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
