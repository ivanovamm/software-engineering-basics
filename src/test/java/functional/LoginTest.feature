Feature: Testing LoginServlet

#  works when the app is running
  Scenario: Redirect to OpenID Connect Authentication

    Given url 'http://localhost:8080/opi_lab3-1.0-SNAPSHOT/login'
    When method get
    Then status 200
    * print responseHeaders
