Feature: Keycloak Authentication Test

  Background:

    * url 'http://localhost:8082'

  Scenario: Login and verify redirect of existent user
    Given path 'realms/myrealm/protocol/openid-connect/auth'
    And param response_type = 'code'
    And param client_id = 'myrealm-client'
    And param state = 'fhihfi2312'
    And param scope = 'profile'
    And param redirect_uri = 'http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp'
    When method get
    Then status 200

    Given url 'http://localhost:8082/realms/myrealm/protocol/openid-connect/token'
    And form field client_id = 'myrealm-client'
    And form field username = 'maria123'
    And form field password = 'itmo123'
    And form field grant_type = 'password'
    And form field client_secret = 'mV1jXsFqk0XtbQky2oLCLTEdmHQHl3k6'
    When method post
    Then status 200


  Scenario: Login of non-existent user
    Given url 'http://localhost:8082'
    And path 'realms/myrealm/protocol/openid-connect/token'
    And param response_type = 'code'
    And param client_id = 'myrealm-client'
    And param state = 'fhihfi2312'
    And param scope = 'profile'
    And param redirect_uri = 'http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp'
    And form field username = 'new_user'
    And form field password = 'user123'
    And form field grant_type = 'password'
    When method post

    Then status 400





