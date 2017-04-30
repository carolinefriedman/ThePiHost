Feature: Testing Shooter Class constructor and methods

	Scenario: Shooter Constructor
		Given Shooter constructor is called
		Then make sure variables are initialized correctly
		
	Scenario: Test Tick Method, checking move left conditionals
		Given User presses left arrow
		And x coordinate of shooter is greater than 0
		Then shooter x coordinate should decrement by 'moveSpeed'
		
	Scenario: Test Tick Method, checking move left conditionals with x < 0
		Given User presses left arrow
		And x coordinate of shooter is less than 0
		Then shooter x coordinate should not change
		
	Scenario: Test Tick Method, checking move right conditionals
		Given User presses right arrow
		And x coordinate of shooter is less than screenwidth - width of shooter graphic
		Then shooter x coordinate should increment by 'moveSpeed'
		
	Scenario: Test Tick Method, checking move right conditionals with x > screenwidth - width of shooter graphic
		Given User presses right arrow
		And x coordinate of shooter is greater than screenwidth - width of shooter graphic
		Then shooter x coordinate should not move right
