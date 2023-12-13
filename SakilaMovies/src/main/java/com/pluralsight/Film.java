package com.pluralsight;
import java.sql.Clob;

public class Film {
    private int filmID, releaseYear, length;
    private String firstName, lastName, filmTitle, description;
    private Clob descriptionClob;
    public Film() {
    }

    public Film(int filmID, int releaseYear, int length, String firstName, String lastName, String filmTitle, Clob descriptionClob, String description) {
        this.filmID = filmID;
        this.releaseYear = releaseYear;
        this.length = length;
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmTitle = filmTitle;
        this.descriptionClob = descriptionClob;
        this.description = description;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public Clob getDescriptionClob() {
        return descriptionClob;
    }

    public void setDescriptionClob(Clob descriptionClob) {
        this.descriptionClob = descriptionClob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
