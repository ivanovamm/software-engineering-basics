package example.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    private static final String X = "x";
    private static final String Y = "y";
    private static final String RADIUS = "r";

    private static final int  UNPROCESSABLE_ENTITY = 422;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!checkRequest(request)) {
            return;
        }

        try {
            if (
                    Double.parseDouble(request.getParameter(X)) < -5
                            || Double.parseDouble(request.getParameter(X)) > 3
            ) {
                return;
            }
            Double.parseDouble(request.getParameter(Y));
            Double.parseDouble(request.getParameter(RADIUS));
            response.sendRedirect("./checkArea?" + request.getQueryString());
        } catch (Exception e) {
            response.getWriter().write(UNPROCESSABLE_ENTITY);
        }
    }

    private boolean checkRequest(HttpServletRequest request) {
        return checkValues(request, X) &&
                checkValues(request, Y) &&
                checkValues(request, RADIUS);
    }

    private boolean checkValues(HttpServletRequest request, String value) {
        String valueName = request.getParameter(value);
        return valueName != null && !valueName.isEmpty();
    }


}

