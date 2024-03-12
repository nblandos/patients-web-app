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

@WebServlet("/patientPage.html")
public class ViewPatientPageServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        System.out.println("Patient Name received: ");

        String patientName = request.getParameter("patient");

        System.out.println("Patient Name received: " + patientName);


        Model model = ModelFactory.getModel();
        Map<String, String> patientDetails = model.getPatientDetails(patientName);

        System.out.println("Patient details from Model: " + patientDetails);


        request.setAttribute("patientDetails", patientDetails);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientPage.jsp");
        dispatch.forward(request, response);
    }
}
