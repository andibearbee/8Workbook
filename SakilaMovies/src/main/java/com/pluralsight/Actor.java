package com.pluralsight;

public class Actor {
    private String firstName;
    private String lastName;
    private int actorID;

    public Actor() {
    }

    public Actor(String firstName, String lastName, int actorID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.actorID = actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }
}