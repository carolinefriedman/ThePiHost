Feature: Testing GameIO Class

	Scenario: GameIO Constructor
		Given GameIO constructor is called, make sure no errors are raised

	Scenario: Test keyPressed Method, user wants to move left
		Given user pressed left arrow
		Then player moves left
		And bullet moves left
		
	Scenario: Test keyPressed Method, user wants to move right
		Given user pressed right arrow
		Then player moves right
		And bullet moves right
		
	Scenario: Test keyPressed Method, user wants to move shoot bullet
		Given user pressed spacebar
		Then player bullet is shooting
		And spaceDefender.entered equals true
		
	Scenario: Test keyReleased method, user no longer wants to move left
		Given user releases left arrow
		Then player stops moving left
		And bullet stops moving left
		
	Scenario: Test keyReleased method, user no longer wants to move right
		Given user releases right arrow
		Then player stops moving right
		And bullet stops moving right
