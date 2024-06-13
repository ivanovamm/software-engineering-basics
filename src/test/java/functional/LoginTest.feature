Feature: Testing LoginServlet

  Scenario: Redirect to OpenID Connect Authentication

    Given url 'http://localhost:8080/lab2_4-1.0-SNAPSHOT/login'
    When method get
    Then status 200
    * print responseHeaders
