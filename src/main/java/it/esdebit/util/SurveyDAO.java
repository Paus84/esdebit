package it.esdebit.util;

import it.esdebit.bean.Survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyDAO {
    
    public Survey getSurvey(String id) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/esdebit";
        String dbUser = "admin";
        String dbPassword = "admin";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM surveys WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        
        ResultSet result = statement.executeQuery();
        
        Survey survey = null;
        
        if (result.next()) {
            survey = new Survey(Integer.parseInt(result.getString("id")));
            survey.setTitle(result.getString("title"));
            survey.setDescription(result.getString("description"));
        }
        
        return survey;
    }
}