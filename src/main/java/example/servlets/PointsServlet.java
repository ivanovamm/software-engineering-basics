package example.servlets;

import  example.models.Point;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PointsServlet", value = "/getPoints")
public class PointsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Point> points = (ArrayList<Point>) request.getServletContext().getAttribute("points");
        StringBuilder sb = new StringBuilder();
        for(Point point: points){
            sb.append(point.getX()).append(" ");
            sb.append(point.getY()).append(" ");
            sb.append(point.isInArea()).append(" ");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(sb.substring(0, sb.length()-1));
    }
}
