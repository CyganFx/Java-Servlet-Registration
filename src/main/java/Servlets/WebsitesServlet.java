package Servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Servlets.WebsitesServlet")
public class WebsitesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        switch ((String) session.getAttribute("job")) {
            case "programmer":
                response.sendRedirect("https://www.siliconrepublic.com/advice/better-programmer-6-steps");
                break;
            case "economist":
                response.sendRedirect("https://www.quora.com/How-does-one-become-an-economist");
                break;
            case "philosopher":
                response.sendRedirect("https://www.youtube.com/channel/UCJ10M7ftQN7ylM6NaPiEB6w");
                break;
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Browser thinks you're on " + getServletName() + " page");
    }
}