package com.myapplication.ds;

public class Folder {
    private int id;
    private String folderName;

    public Folder(int id, String folderName) {
        this.id = id;
        this.folderName = folderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return id +
                " : " + folderName;
    }
}
