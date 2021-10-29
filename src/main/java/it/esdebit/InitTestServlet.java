package it.esdebit;

import com.google.gson.Gson;
import it.esdebit.bean.Survey;
import it.esdebit.util.SurveyDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class InitTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public InitTestServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String surveyId = request.getParameter("surveyId");
        ServletContext sc = this.getServletContext();
        
        SurveyDAO surveyDao = new SurveyDAO();
        
        try {
            Survey survey = surveyDao.getSurvey(surveyId);
            String jsonSurvey = new Gson().toJson(survey);
            
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonSurvey);
            out.flush();
        } catch (Throwable ex) {
            throw new ServletException(ex);
        }
    }
}