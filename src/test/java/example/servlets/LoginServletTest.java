package example.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;

class LoginServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LoginServlet servlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_RedirectsToLogin() throws IOException {
        servlet.doGet(request, response);

        String expectedRedirectUrl = "http://localhost:8082/realms/myrealm/protocol/openid-connect/auth?response_type=code&client_id=myrealm-client&state=fhihfi2312&scope=profile&redirect_uri=http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp";
        verify(response).sendRedirect(expectedRedirectUrl);
    }
}
