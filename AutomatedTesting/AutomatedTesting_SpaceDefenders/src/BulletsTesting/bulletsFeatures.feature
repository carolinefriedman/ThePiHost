Feature: Testing Bullets Class constructor and methods

	Scenario: Bullets Constructor
		Given Bullets constructor is called
		Then make sure Bullets variables are initialized correctly
		
	Scenario: Test Bullets Tick Method, checking move left conditionals
		Given y coord is less than zero
		And collision is true
		Then check that bullet coordinates equal shooter coordinates
		And isShooting and collision booleans are set back to false
		
	Scenario: Test Bullets Tick Method, checking movement upon shooting
		Given isShooting is true (player presses spacebar)
		Then y coordinate of bullet increases by bulletSpeed
		
	Scenario: Test Bullets Tick Method, checking movement when not shooting and moving left
		Given user is pressing left arrow or spacebar
		And x coordinate of bullet is greater than zero
		Then x coordinate of bullet increases by moveSpeed
