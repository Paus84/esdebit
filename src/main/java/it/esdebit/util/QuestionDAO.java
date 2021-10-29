package it.esdebit.util;

import it.esdebit.bean.Exit;
import it.esdebit.bean.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO {
    
    public Question getQuestion(String id) throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/esdebit";
        String dbUser = "admin";
        String dbPassword = "admin";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM questions WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        
        ResultSet result = statement.executeQuery();
        
        Question question = null;
        
        if (result.next()) {
            question = new Question(Integer.parseInt(result.getString("id")), result.getString("step"));
            question.setTitolo(result.getString("titolo"));
            question.setTesto(result.getString("testo"));
        }
        
        return question;
    }
    
    public static ArrayList<Question> getQuestionsOfSurvey(String surveyId) throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/esdebit";
        String dbUser = "admin";
        String dbPassword = "admin";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM questions WHERE survey = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, surveyId);
        
        ResultSet result = statement.executeQuery();
        
        ArrayList<Question> questions = new ArrayList<>();
        
        while (result.next()) {
            Question q = new Question(Integer.parseInt(result.getString("id")), result.getString("step"));
            q.setTitolo(result.getString("titolo"));
            q.setTesto(result.getString("testo"));
            q.setType(result.getString("type"));
            
            String sql2 = "SELECT * FROM exits WHERE parentQuestion = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, result.getString("id"));
    
            ResultSet result2 = statement2.executeQuery();
    
            ArrayList<Exit> exits = new ArrayList<>();
    
            while (result2.next()) {
                Exit e = new Exit(Integer.parseInt(result2.getString("id")), q);
                e.setResponse(result2.getString("response"));
                e.setExitQuestion(result2.getString("exitQuestion"));
                e.setExit(result2.getString("exit"));
                exits.add(e);
            }
            q.setExits(exits.toArray(new Exit[0]));
            
            questions.add(q);
        }
        
        return questions;
    }
}