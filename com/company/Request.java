package com.company;

public class Request {

    private String id;
    private String membership;
    private String name;

    public Request(String id, String membership, String name) {
        this.id = id;
        this.membership = membership;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getMembership() {
        return membership;
    }
    public String getName() {
        return name;
    }
}
