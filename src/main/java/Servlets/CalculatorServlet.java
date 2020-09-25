package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlets.CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        int number1 = Integer.parseInt(request.getParameter("number1"));
        int number2 = Integer.parseInt(request.getParameter("number2"));

        String operator = request.getParameter("operator");

        switch (operator) {
            case "+":
                pw.println("answer is " + (number1 + number2));
                break;
            case "-":
                pw.println("answer is " + (number1 - number2));
                break;
            case "*":
                pw.println("answer is " + (number1 * number2));
                break;
            case "/":
                try {
                    pw.println("answer is " + (number1 / number2));
                } catch (ArithmeticException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            default:
                pw.println("Not supported operator");
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Browser thinks you're on " + getServletName() + " page");
    }
}
