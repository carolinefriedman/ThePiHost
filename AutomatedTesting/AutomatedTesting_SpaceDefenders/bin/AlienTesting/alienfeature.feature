Feature: Testing Alien Class

	Scenario: Constructor
		Given Alien constructor is called
		Then all alien variabled properly initialized
		
	Scenario: Test Tick Method, basic
		Given alien tick method is called
		Then alien's x coord should increase by moveSpeed
	
	Scenario: Test tick method, moved off right of screen
		Given x coordinate of alien is greater than or equal to game.WIDTH - width
		Then moveSpeed should be negated (turning alien around)
		And x should be incremented by moveSpeed
		
	Scenario: Test tick method, moved off left of screen
		Given x coordinate of alien is less than or equal to zero
		Then moveSpeed should be negated (turning alien around)