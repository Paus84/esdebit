package it.esdebit.bean;

public class Question {
    private final int id;
    private String step;
    private String titolo;
    private String testo;
    private String type;
    private Survey survey;
    private Exit[] exits;
    
    public Question(int id, String step){
        this.id = id;
        this.setStep(step);
    }
    
    public int getId() {
        return id;
    }
    
    public String getStep() {
        return step;
    }
    
    public void setStep(String step) {
        this.step = step;
    }
    
    public String getTitolo() {
        return titolo;
    }
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    
    public String getTesto() {
        return testo;
    }
    
    public void setTesto(String testo) {
        this.testo = testo;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Survey getSurvey() {
        return survey;
    }
    
    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
    
    public Exit[] getExits() {
        return exits;
    }
    
    public void setExits(Exit[] exits) {
        this.exits = exits;
    }
}
