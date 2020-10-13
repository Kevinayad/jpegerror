package com.company;

public class Request { private String id;
    private  String membership;

    public Request(String id, String membership) {
        this.id = id;
        this.membership = membership;
    }



    public String getId() {
        return id;
    }
    public String getMembership() {
        return membership;
    }
}
