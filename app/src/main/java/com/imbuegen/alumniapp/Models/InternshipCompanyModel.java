package com.imbuegen.alumniapp.Models;

public class InternshipCompanyModel {

    private String skills,description,name,url,code;

    public InternshipCompanyModel(){

    }

    public InternshipCompanyModel(String skills, String description, String name, String url,String code) {
        this.skills = skills;
        this.description = description;
        this.name = name;
        this.url = url;
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InternshipCompanyModel(String skills, String name) {
        this.skills = skills;
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
