package example.servlets;
import example.models.Point;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AreaCheckServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private AreaCheckServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_ValidParameters() throws Exception {
        when(request.getParameter("x")).thenReturn("1.0");
        when(request.getParameter("y")).thenReturn("2.0");
        when(request.getParameter("r")).thenReturn("1.5");
        when(request.getParameter("action")).thenReturn("submitForm");
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher("./result.jsp")).thenReturn(dispatcher);

        List<Point> points = new ArrayList<>();
        when(context.getAttribute("points")).thenReturn(points);

        servlet.doGet(request, response);

        assertEquals(1, points.size());
        verify(request).setAttribute("x", 1.0);
        verify(request).setAttribute("y", 2.0);
        verify(request).setAttribute("r", 1.5);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGet_InvalidParameters() throws Exception {
        when(request.getParameter("x")).thenReturn("invalid");
        when(request.getParameter("y")).thenReturn("2.0");
        when(request.getParameter("r")).thenReturn("1.5");
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(writer).write(captor.capture());
        assertEquals(422, captor.getValue());
    }


    @Test
    void testGetPoints_WithExistingPoints() {
        List<Point> points = new ArrayList<>();
        when(context.getAttribute("points")).thenReturn(points);

        List<Point> result = servlet.getPoints(context);

        assertSame(points, result);
    }

    @Test
    void testGetPoints_WithoutExistingPoints() {
        when(context.getAttribute("points")).thenReturn(null);

        List<Point> result = servlet.getPoints(context);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(context).setAttribute("points", result);
    }
}
