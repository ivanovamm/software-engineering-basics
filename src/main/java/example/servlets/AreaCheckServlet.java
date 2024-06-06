package example.servlets;

import example.models.Point;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkArea")
public class AreaCheckServlet extends HttpServlet {
    private static final int UNPROCESSABLE_ENTITY = 422;
    private static final String SUBMIT = "submitForm";

    private static final String ACTION = "action";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String RADIUS = "r";

    private static final String RESULT = "result";
    private static final String POINTS = "points";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double x = Double.parseDouble(request.getParameter(X));
            double y = Double.parseDouble(request.getParameter(Y));
            double r = Double.parseDouble(request.getParameter(RADIUS));
            Point point = new Point(x, y, r);

            List<Point> points = getPoints(request.getServletContext());
            points.add(point);

            String action = request.getParameter(ACTION);
            if (SUBMIT.equals(action)) {
                getResult(request, response, x, y, r, point);
            }

        } catch (NumberFormatException | IOException e) {
            response.getWriter().write(UNPROCESSABLE_ENTITY);
        }
    }

    public List<Point> getPoints(ServletContext context) {
        List<Point> points = (List<Point>) context.getAttribute(POINTS);
        if (points == null) {
            points = new ArrayList<>();
            // TODO: 09.01.2024 излишнее поведение метода  
            context.setAttribute(POINTS, points);
        }
        return points;
    }

    private void getResult(HttpServletRequest request, HttpServletResponse response, double x, double y, double r, Point point)
            throws ServletException, IOException {
        request.setAttribute(X, x);
        request.setAttribute(Y, y);
        request.setAttribute(RADIUS, r);
        request.setAttribute(RESULT, point.isInArea());
        request.getRequestDispatcher("./result.jsp").forward(request, response);
    }

}

