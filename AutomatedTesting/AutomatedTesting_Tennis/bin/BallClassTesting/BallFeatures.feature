Feature: Testing Ball Class

	Scenario: Ball Constructor test
		Given I call the Ball constructor
		Then class variables for Ball are correctly initialized
		
	Scenario: Test Ball tick method, ball hits left wall
		Given ball hits left wall (ball x coordinate less than or equal to zero)
		And Ball tick method is called
		Then velocityX speed of ball is positive
		And computer player score increments
		
	Scenario: Test Ball tick method, ball hits right wall
		Given ball hits right wall
		And Ball tick method is called
		Then velocityX speed of ball is negative
		And player score increments
		
	Scenario: Test Ball tick method, ball hits top wall
		Given ball hits top wall
		And Ball tick method is called
		Then velocityY speed of ball is positive
		
	Scenario: Test Ball tick method, ball hits bottom wall
		Given ball hits bottom wall
		And Ball tick method is called
		Then velocityY speed of ball is negative
		