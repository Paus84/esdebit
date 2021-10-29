package it.esdebit.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
    private final int id;
    private Survey survey;
    private User user;
    private String creationDate;
    private String completationDate;
    private String result;
    
    public static final SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    Result (int id, Survey survey, User user){
        this.id = id;
        this.survey = survey;
        this.user = user;
    }
    
    public int getId() {
        return id;
    }
    
    public Survey getSurvey() {
        return survey;
    }
    
    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getCreationDate() {
        return dateFormat.format(creationDate);
    }
    
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public String getCompletationDate() {
        return dateFormat.format(completationDate);
    }
    
    public void setCompletationDate(String completationDate) {
        this.completationDate = completationDate;
    }
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
}
