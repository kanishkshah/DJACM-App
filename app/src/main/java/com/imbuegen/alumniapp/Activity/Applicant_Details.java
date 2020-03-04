package com.imbuegen.alumniapp.Activity;

import android.net.Uri;

public class Applicant_Details {

    private String name, email, department, year,sap;
    private int amount,r_count,g_count,b_count,saving;

    public Applicant_Details() {

    }

    public Applicant_Details(String name, String email, String department, String year, String sap, int amount, int r_count, int g_count, int b_count, int saving) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.year = year;
        this.sap = sap;
        this.amount = amount;
        this.r_count = r_count;
        this.g_count = g_count;
        this.b_count = b_count;
        this.saving = saving;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSap(String sap) {
        this.sap = sap;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setR_count(int r_count) {
        this.r_count = r_count;
    }

    public void setG_count(int g_count) {
        this.g_count = g_count;
    }

    public void setB_count(int b_count) {
        this.b_count = b_count;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getYear() {
        return year;
    }

    public String getSap() {
        return sap;
    }

    public int getAmount() {
        return amount;
    }

    public int getR_count() {
        return r_count;
    }

    public int getG_count() {
        return g_count;
    }

    public int getB_count() {
        return b_count;
    }

    public int getSaving() {
        return saving;
    }
}

