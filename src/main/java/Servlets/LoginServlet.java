package Servlets;

import Classes.BadLoginException;
import Classes.Dao;
import Classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlets.LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PrintWriter pw = response.getWriter();

        if (email.equals("stupidAdmin@gmail.com") || email.equals("stupidAdmin@mail.ru") || email.equals("stupidAdmin@astanait.edu.kz")) {
            try {
                throw new BadLoginException("Don't say bad words to admin, please((");
            } catch (BadLoginException e) {
                pw.println("This is a custom exception: " + e.getClass());
                pw.println(e.getMessage());
            }
        } else {
            Dao dao = new Dao();

            if (dao.checkLogin(email, password)) {
                HttpSession session = request.getSession();
                User user = new User();

               // Cookie emailCookie = new Cookie(User.EMAIL, email);
               // emailCookie.setMaxAge(60 * 30);
                // response.addCookie(emailCookie);

                user.setEmail(email);
                user.setPassword(password);

                user = dao.setUserValues(user);
                setUserSessions(user, session);

                request.setAttribute("user", user);

                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/id-check?id=" + getServletContext().getInitParameter("correctId"));
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // We use cookies in order to check if session was already set
        HttpSession session = request.getSession();
//        Cookie[] cookies = request.getCookies();
//        for (Cookie emailCookie : cookies) {
//            if (emailCookie.getName() != null) {
//                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                response.sendRedirect(request.getContextPath());
//            }
//        }
        if (session.getAttribute("email") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    private void setUserSessions(User user, HttpSession session) {
        session.setAttribute("id", user.getId());
        session.setAttribute("first_name", user.getFirstName());
        session.setAttribute("last_name", user.getLastName());
        session.setAttribute("job", user.getJob());
        session.setAttribute("email", user.getEmail());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Browser thinks you're on " + getServletName() + " page");
    }
}
