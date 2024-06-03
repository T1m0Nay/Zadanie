import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Получаем данные
        String name = "Иван";
        int age = 30;

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Данные из Java:</h1>");
        out.println("<p>Имя: " + name + "</p>");
        out.println("<p>Возраст: " + age + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
