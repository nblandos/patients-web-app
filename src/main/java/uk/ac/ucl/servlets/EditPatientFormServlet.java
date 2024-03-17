package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/editPatientForm.html")
public class EditPatientFormServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("patientId");
        Model model = ModelFactory.getModel();
        Map<String, String> patientData = model.getPatientDetailsFromID(patientId);

        if (patientData != null) {
            for (Map.Entry<String, String> entry : patientData.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editPatientForm.jsp");
        dispatch.forward(request, response);
    }
}