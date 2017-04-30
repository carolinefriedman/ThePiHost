Feature: Testing ComputerPaddle Class

	Scenario: ComputerPaddle Constructor test
		Given I call the ComputerPaddle constructor
		Then class variables for ComputerPaddle are correctly initialized
		
	Scenario: Test ComputerPaddle tick method, move paddle up on scren
		Given ball is moving up on screen
		And y coordinate of paddle is greater than or equal to zero
		And ComputerPaddle tick method is called
		Then y coordinate of paddle decreases by paddleSpeed
		
	Scenario: Test ComputerPaddle tick method, move paddle down on scren
		Given ball is moving down on screen
		And y coordinate of paddle is within lower bound of screen
		And ComputerPaddle tick method is called
		Then y coordinate of paddle increases by paddleSpeed