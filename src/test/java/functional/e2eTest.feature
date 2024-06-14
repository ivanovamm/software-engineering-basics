Feature: End-to-End Testing of Login and Redirect
  Background:
    * configure driver = {type: 'chrome', executable: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe', addOptions: ["--remote-allow-origins=*"]}

  Scenario: Login and verify redirect of existent user
    Given driver "http://localhost:8082/realms/myrealm/protocol/openid-connect/auth?response_type=code&client_id=myrealm-client&state=fhihfi2312&scope=profile&redirect_uri=http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp"
    And input('input[name=username]', 'maria123')
    And input('input[name=password]', 'itmo123')
    And submit()
    * java.lang.Thread.sleep(5000)



  Scenario: Login and verify redirect of non-existent user
    Given driver "http://localhost:8082/realms/myrealm/protocol/openid-connect/auth"
    And form field client_id = 'myrealm-client'
    And form field username = 'new_user'
    And form field password = 'user123'
    And form field grant_type = 'password'
    And form field client_secret = 'mV1jXsFqk0XtbQky2oLCLTEdmHQHl3k6'
    * java.lang.Thread.sleep(5000)