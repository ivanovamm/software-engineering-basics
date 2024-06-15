Feature: End-to-End Testing of Login and Redirect
  Background:
    * configure driver = {type: 'chrome', executable: 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe', addOptions: ["--remote-allow-origins=*"]}

  Scenario: Login and verify redirect of existent user
    Given driver "http://localhost:8082/realms/myrealm/protocol/openid-connect/auth?response_type=code&client_id=myrealm-client&state=fhihfi2312&scope=profile&redirect_uri=http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp"
    And input('input[name=username]', 'maria123')
    And input('input[name=password]', 'itmo123')
 #   And submit()
    And click('#kc-form-buttons')
#    And waitForUrl('http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp')
   * java.lang.Thread.sleep(5000)


  Scenario: Filling out the form and sending it
    Given driver "http://localhost:8080/lab2_4-1.0-SNAPSHOT/index.jsp"
    And waitFor("#x_value")
    And input("#x_value", "2")
    * java.lang.Thread.sleep(1000)
    And click("input[name='optionY'][value='0']")
    * java.lang.Thread.sleep(1000)
    And click("input[name='optionR'][value='3']")
    * java.lang.Thread.sleep(1000)
    When click("#check")
    * java.lang.Thread.sleep(1000)

