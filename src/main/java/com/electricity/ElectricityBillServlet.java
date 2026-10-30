package com.electricity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/bill")
public class ElectricityBillServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        double units = Double.parseDouble(request.getParameter("units"));

        double bill;

        if (units <= 50) {
            bill = units * 3.50;
        } else if (units <= 150) {
            bill = (50 * 3.50) + ((units - 50) * 4.00);
        } else if (units <= 250) {
            bill = (50 * 3.50)
                    + (100 * 4.00)
                    + ((units - 150) * 5.20);
        } else {
            bill = (50 * 3.50)
                    + (100 * 4.00)
                    + (100 * 5.20)
                    + ((units - 250) * 6.50);
        }

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Electricity Bill</title>");

        out.println("<style>");
        out.println("* { box-sizing: border-box; margin: 0; padding: 0; }");

        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("min-height: 100vh;");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("align-items: center;");
        out.println("background: linear-gradient(135deg, #eef2ff, #dbeafe);");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".card {");
        out.println("width: 100%;");
        out.println("max-width: 500px;");
        out.println("background: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 15px 40px rgba(0,0,0,0.12);");
        out.println("}");

        out.println("h1 { text-align: center; color: #1e3a8a; margin-bottom: 10px; }");
        out.println(".subtitle { text-align: center; color: #64748b; margin-bottom: 25px; }");

        out.println(".result {");
        out.println("background: #eff6ff;");
        out.println("padding: 20px;");
        out.println("border-radius: 14px;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println(".row {");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("padding: 10px 0;");
        out.println("border-bottom: 1px solid #dbeafe;");
        out.println("}");

        out.println(".total {");
        out.println("font-size: 24px;");
        out.println("font-weight: bold;");
        out.println("color: #1d4ed8;");
        out.println("border: none;");
        out.println("margin-top: 10px;");
        out.println("}");

        out.println(".back {");
        out.println("display: block;");
        out.println("text-align: center;");
        out.println("text-decoration: none;");
        out.println("background: #2563eb;");
        out.println("color: white;");
        out.println("padding: 13px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");
        out.println("<div class='card'>");

        out.println("<h1>⚡ Electricity Bill</h1>");
        out.println("<p class='subtitle'>Bill calculation completed</p>");

        out.println("<div class='result'>");

        out.println("<div class='row'>");
        out.println("<span>Consumer Name</span>");
        out.println("<strong>" + name + "</strong>");
        out.println("</div>");

        out.println("<div class='row'>");
        out.println("<span>Units Consumed</span>");
        out.println("<strong>" + units + "</strong>");
        out.println("</div>");

        out.println("<div class='row total'>");
        out.println("<span>Total Bill</span>");
        out.println("<span>₹" + String.format("%.2f", bill) + "</span>");
        out.println("</div>");

        out.println("</div>");

        out.println("<a class='back' href='index.html'>Calculate Another Bill</a>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}