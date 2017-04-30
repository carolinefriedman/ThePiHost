Feature: Testing PlayerPaddle Class

	Scenario: PlayerPaddle Constructor test
		Given I call the PlayerPaddle constructor
		Then class variables for PlayerPaddle are correctly initialized
		
	Scenario: Test PlayerPaddle tick method, move paddle up on screen
		Given user presses w on keyboard
		And y coordinate of paddle is greater zero
		And PlayerPaddle tick method is called
		Then y coordinate of player paddle decreases by paddleSpeed
		
	Scenario: Test PlayerPaddle tick method, move paddle down on screen
		Given user presses s on keyboard
		And y coordinate of paddle is bounded by lower edge of screen
		And PlayerPaddle tick method is called
		Then y coordinate of player paddle increases by paddleSpeed