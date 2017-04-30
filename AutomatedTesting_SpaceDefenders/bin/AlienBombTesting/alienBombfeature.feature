Feature: Testing Alien Class

	Scenario: Constructor
		Given I call constructor, make sure variables are initialized correctly
		
	Scenario: Test Tick Method, player not shooting
		Given Player isnt shooting, 
		Then alien.y and alien.bomb.y should be equal
		
	Scenario: Test Tick Method, bomb is above shooter
		Given bomb is above the shooter
		Then the x and y coords of the bomb and the alien should be equal
		
	Scenario: Test Tick Method, player is shooting
		Given Player isnt shooting
		Then the y coord of the bomb should increase by one when tick() is called
		
	Scenario: Test Tick Method, alien not shooting
		Given Alien not shooting
		Then the x coord of the alienBomb should follow the x coord of the alien
		
	Scenario: Test Tick Method, testing collide()
		Given a collision is detected between shooter and bomb
		Then it must decrease player health by one