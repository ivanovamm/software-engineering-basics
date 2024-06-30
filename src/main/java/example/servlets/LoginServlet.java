package example.servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("http://localhost:8082/realms/myrealm/protocol/openid-connect/auth?response_type=code&client_id=myrealm-client&state=fhihfi2312&scope=profile&redirect_uri=http://localhost:8080/opi_lab3-1.0-SNAPSHOT/index.jsp");
    }

}
