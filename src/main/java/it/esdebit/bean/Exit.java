package it.esdebit.bean;

public class Exit {
    private final int id;
    private int parentQuestion;
    private String response;
    private int exitQuestion;
    private int exit;
    
    public Exit(int id, Question parentQuestion) {
        this.id = id;
        setParentQuestion(parentQuestion);
    }
    
    public int getId() {
        return id;
    }
    
    public int getParentQuestion() {
        return parentQuestion;
    }
    
    public void setParentQuestion(Question parentQuestion) {
        this.parentQuestion = parentQuestion.getId();
    }
    
    public String getResponse() {
        return response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
    
    public int getExitQuestion() {
        return exitQuestion;
    }
    
    public void setExitQuestion(String exitQuestion) {
        if (exitQuestion != null) {
            this.exitQuestion = Integer.parseInt(exitQuestion);
        } else {
            this.exitQuestion = 0;
        }
    }
    
    public int getExit() {
        return exit;
    }
    
    public void setExit(String exit) {
        if (exit != null) {
            this.exit = Integer.parseInt(exit);
        } else {
            this.exit = 0;
        }
    }
    
}
