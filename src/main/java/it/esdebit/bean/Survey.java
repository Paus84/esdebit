package it.esdebit.bean;

import it.esdebit.util.QuestionDAO;

import java.util.ArrayList;

public class Survey {
    private final int id;
    private String title;
    private String description;
    private ArrayList<Question> questions = null;
    
    public Survey(int id) {
        this.id = id;
        this.questions = this.getQuestions();
    }
    
    public int getId() {
        return id;
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
    
    public void setDescription(String email) {
        this.description = email;
    }
    
    public ArrayList<Question> getQuestions() {
        if(questions == null){
            try {
                setQuestions(QuestionDAO.getQuestionsOfSurvey(""+this.id));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return questions;
    }
    
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
