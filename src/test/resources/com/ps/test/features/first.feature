Feature: Amazon Search Page Scenarios
Background:
  * configure driver = { type: 'chrome', headless: true, addOptions: ["--remote-allow-origins=*"] }
#  * configure driver = { type: 'msedge', webDriverSession: { capabilities: { browserName: 'edge' } } }
#  * configure driver = { type: 'safaridriver', showDriverLog: true }
  * url amazonUrlBase
  * url githubUrlBase
  * def amazonLocators =  read('classpath:com/ps/test/locators/locators.json')


  Scenario: Customer Search for project in the site
    Given driver amazonUrlBase
    And input(amazonLocators.AllPage.searchBoxInput, 'PS5')
    When submit().click(amazonLocators.AllPage.searchBoxBtn)
    * screenshot()
    And driver quit


    @ignore
    Scenario: Customer trying to login to github
      Given driver githubUrlBase
      And input('#login_field', 'dummy')
      And input('#password', 'world')
      When submit().click("input[name=commit]")
      * screenshot()
      Then match html('.flash-error') contains 'Incorrect username or password.'

  @ignore
  Scenario: Customer trying to launch Marcel site
    Given driver "https://psinnersource.lioncloud.net/"
    And click('{}Login')
    And click('{}Remember me')
    And click('{} Publicis Groupe Login')
    And waitUntil('.alert-message', "_.innerHTML.includes('Some Text')")
    * screenshot()
    And input('#password', 'world')


    Then match html('.flash-error') contains 'Incorrect username or password.'
