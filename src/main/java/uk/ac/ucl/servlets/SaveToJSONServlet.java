package uk.ac.ucl.servlets;

import uk.ac.ucl.model.DataFrame;
import uk.ac.ucl.model.JSONWriter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveToJSON.html")
public class SaveToJSONServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Model model = ModelFactory.getModel();
        DataFrame dataFrame = model.getDataFrame();
        JSONWriter jsonWriter = new JSONWriter(dataFrame, ModelFactory.CSV_FILE_PATH);
        jsonWriter.writeDataToJSON();
        response.sendRedirect("index.html");
    }
}