package com.ji.badge.logic;

public class Badge {
    private String subject;
    private String color;
    private String status;
    private String icon;

    public Badge() {
    }

    public Badge(String subject, String color, String status, String icon) {
        this.subject = subject;
        this.color = color;
        this.status = status;
        this.icon = icon;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
