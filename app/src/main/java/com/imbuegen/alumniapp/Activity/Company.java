package com.imbuegen.alumniapp.Activity;

public class Company {

    private String name,skills,description,url;

    public Company() {
    }

    public Company(String name, String skills, String description, String url) {
        this.name = name;
        this.skills = skills;
        this.description = description;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getSkills() {
        return skills;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
