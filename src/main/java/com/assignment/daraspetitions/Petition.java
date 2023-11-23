package com.assignment.daraspetitions;

public class Petition {

    private int id;
    private int signatureCount;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSignatureCount() {
        return signatureCount;
    }

    public void setSignatureCount(int signatureCount) {
        this.signatureCount = signatureCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void incrementID(){
        this.setId(id);
        id++;
    }

    @Override
    public String toString() {
        return this.getId() + "Petition Title: " + this.getTitle() + "/nPetition Description: " + this.getDescription();
    }
}
