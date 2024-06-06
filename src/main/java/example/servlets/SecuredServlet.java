package example.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/secured")
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        if (response != null) {
            if (request != null && request.getUserPrincipal() != null) {
                response.getWriter().write("Hello, " + request.getUserPrincipal().getName() + "!");
            } else {
                response.sendRedirect("/login");

            }
        }
    }
}

