package com.example.ld.ds;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    private String fileLocation;
    LocalDate dateCreated;
    @ManyToOne
    private User creator;
    @ManyToOne
    private Folder parentFolder;

    public File(int id, String fileName, String fileLocation, LocalDate dateCreated, User creator) {
        this.id = id;
        this.fileName = fileName;
        this.fileLocation = fileLocation;
        this.dateCreated = dateCreated;
        this.creator = creator;
    }

    public File(String fileName, LocalDate dateCreated) {
        this.fileName = fileName;
        this.dateCreated = dateCreated;
    }

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
}
