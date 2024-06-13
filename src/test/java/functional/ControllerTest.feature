Feature: Тестирование сервлета ControllerServlet

  Background:
    * url 'http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp'
#    * path 'lab2_4-1.0-SNAPSHOT/index.jsp'
#    * param state = 'fhihfi2312'
#    * param session_state = '523423d3-b9cb-429c-88cd-11e54a046d90'
#    * param iss = 'http%3A%2F%2Flocalhost%3A8082%2Frealms%2Fmyrealm'
#    * param code = '9fa1d7b9-1400-4262-9f7e-6b35a78f33b7.523423d3-b9cb-429c-88cd-11e54a046d90.aff862f1-d8b5-405f-a14d-a513fbae73f6'
#    * param r = 4
#    * param x = 10
#    * param y = 3

  Scenario: Проверка валидного запроса
    Given path '/controller'
    And params { x: 2, y: 3, r: 4 }
    When method get
    Then status 200
#    And match header Location == '#regex:.*checkArea.*'

  Scenario: Проверка недопустимого значения параметра X
    Given url 'http://localhost:8080'
    And path 'lab2_4-1.0-SNAPSHOT/index.jsp'
    And params { state: 'fhihfi2312', session_state: '523423d3-b9cb-429c-88cd-11e54a046d90', iss: 'http%3A%2F%2Flocalhost%3A8082%2Frealms%2Fmyrealm', code: '9fa1d7b9-1400-4262-9f7e-6b35a78f33b7.523423d3-b9cb-429c-88cd-11e54a046d90.aff862f1-d8b5-405f-a14d-a513fbae73f6', r: 4, x: 10, y: 3 }
    When method get
    Then status 422
