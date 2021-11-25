package com.myapplication.ds;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

public class Company extends User implements Serializable {
    private String companyName;
    private String representative;

    public Company(String username, String password, boolean isActive, LocalDate dateCreated, LocalDate dateModified, String companyName, String representative) {
        super(username, password, isActive, dateCreated, dateModified);
        this.companyName = companyName;
        this.representative = representative;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Company(String username, String password, String companyName, String representative) {
        super(username, password);
        this.companyName = companyName;
        this.representative = representative;
    }

    public Company() {
    }

    @Override
    public String toString() {
        return "Kompanija{" + super.toString() +
                "pavadinimas='" + companyName + '\'' +
                ", atstovas='" + representative + '\'' +
                '}'+ '\n';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }
}
