package com.example.androidfinalproject;

public class Password {

   private String key;
    private  String  email;

    private String   app_user;
    private String user;
    private String password;
    private String webapp;
    private String photo;
    private boolean completed;


    public Password(String email, String user, String password, String webapp) {

        this.email = email;
        this.user = user;
        this.password = password;
        this.webapp = webapp;
    }

    public Password() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApp_user() {
        return app_user;
    }

    public void setApp_user(String app_user) {
        this.app_user = app_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebapp() {
        return webapp;
    }

    public void setWebapp(String webapp) {
        this.webapp = webapp;
    }

    public String getPhoto() {
        return photo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
