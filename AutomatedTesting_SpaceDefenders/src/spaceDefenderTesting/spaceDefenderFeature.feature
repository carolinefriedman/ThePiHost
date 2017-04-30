Feature: Testing Shooter Class constructor and methods

	Scenario: spaceDefender Constructor
		Given spaceDefender constructor is called
		Then make sure variables are for spaceDefender are initialized correctly
		
	Scenario: spaceDefender start method
		Given spaceDefender start metod is called
		Then game is running

	Scenario: Test spaceDefender Tick Method, checking random alien shooting assignment
		Given counter does not equal zero
		And counter is not divisible by dropTimer
		Then x is assigned a variable zero through two
		And y is assigned a variable zero through nine
		
	Scenario: Test spaceDefender render Method, allDead is true -> level up
		Given allDead is true (all aliens killed)
		Then alienMatrix reassigned to false
