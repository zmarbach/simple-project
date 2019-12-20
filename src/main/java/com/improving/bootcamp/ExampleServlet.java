package com.improving.bootcamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Example</title>");
        out.println("<style>");
        out.println("h1 {");
        out.println("color:blue;");
        out.println("}");
        out.println("p {");
        out.println("color:red;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Example Text</h1>");
        out.println("<p>This is a sample paragraph</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
