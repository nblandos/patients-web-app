// AddPatientServlet.java
package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/editPatient.html")
public class EditPatientServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> patientData = new HashMap<>();
        patientData.put("ID", request.getParameter("id"));
        patientData.put("BIRTHDATE", request.getParameter("birthdate"));
        patientData.put("DEATHDATE", request.getParameter("deathdate"));
        patientData.put("SSN", request.getParameter("ssn"));
        patientData.put("DRIVERS", request.getParameter("drivers"));
        patientData.put("PASSPORT", request.getParameter("passport"));
        patientData.put("PREFIX", request.getParameter("prefix"));
        patientData.put("FIRST", request.getParameter("first"));
        patientData.put("LAST", request.getParameter("last"));
        patientData.put("SUFFIX", request.getParameter("suffix"));
        patientData.put("MAIDEN", request.getParameter("maiden"));
        patientData.put("MARITAL", request.getParameter("marital"));
        patientData.put("RACE", request.getParameter("race"));
        patientData.put("ETHNICITY", request.getParameter("ethnicity"));
        patientData.put("GENDER", request.getParameter("gender"));
        patientData.put("BIRTHPLACE", request.getParameter("birthplace"));
        patientData.put("ADDRESS", request.getParameter("address"));
        patientData.put("CITY", request.getParameter("city"));
        patientData.put("STATE", request.getParameter("state"));
        patientData.put("ZIP", request.getParameter("zip"));

        Model model = ModelFactory.getModel();
        model.editPatient(patientData);

        response.sendRedirect("patientList.html");
    }
}