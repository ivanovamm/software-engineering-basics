package example.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ControllerServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_ValidParameters() throws Exception {
        when(request.getParameter("x")).thenReturn("1.0");
        when(request.getParameter("y")).thenReturn("2.0");
        when(request.getParameter("r")).thenReturn("1.5");
        when(request.getQueryString()).thenReturn("x=1.0&y=2.0&r=1.5");

        servlet.doGet(request, response);

        verify(response).sendRedirect("./checkArea?x=1.0&y=2.0&r=1.5");
    }

    @Test
    void testDoGet_InvalidXParameter() throws Exception {
        when(request.getParameter("x")).thenReturn("-6.0");
        when(request.getParameter("y")).thenReturn("2.0");
        when(request.getParameter("r")).thenReturn("1.5");

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    void testDoGet_InvalidYParameter() throws Exception {
        when(request.getParameter("x")).thenReturn("1.0");
        when(request.getParameter("y")).thenReturn("invalid");
        when(request.getParameter("r")).thenReturn("1.5");
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(writer).write(captor.capture());
        assertEquals(422, captor.getValue());
    }

    @Test
    void testDoGet_MissingParameters() throws Exception {
        when(request.getParameter("x")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response, never()).sendRedirect(anyString());
    }
}
