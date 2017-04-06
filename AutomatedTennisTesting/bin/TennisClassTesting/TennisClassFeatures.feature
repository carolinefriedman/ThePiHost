Feature: Testing Tennis Class

	Scenario: Tennis Constructor test
		Given I call the Tennis constructor
		Then class variables are correctly initialized
		And class instances within packages are properly initiated
		
	Scenario: Test Tennis start() method
		Given start method is called by thread
		Then gameRunning variable assigned to true
