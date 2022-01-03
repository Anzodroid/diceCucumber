Feature: Roomload 
  What to expect when a page loads

  Scenario: diceButtons appear on the page 
    Given I open a room page
    And the page loads 
		Then all blue buttons appear
		Then a text box is available for me to enter my username
		Then the brower should close
		
  Scenario: user changes username and rolls a dice
    Given I open a room page
    And the page loads 
    When I change my username 
		And select and roll a D20
		Then the dice result should appear under my new username
		Then the brower should close
		
