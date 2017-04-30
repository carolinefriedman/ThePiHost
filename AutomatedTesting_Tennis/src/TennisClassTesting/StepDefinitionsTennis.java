package TennisClassTesting;

import pongCode.Tennis;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitionsTennis {
	
	Tennis game = new Tennis();
	
	@Given("^I call the Tennis constructor$")
	public void i_call_the_Tennis_constructor() throws Throwable {
	    //constructor called above, globally
	}

	@Then("^class variables are correctly initialized$")
	public void class_variables_are_correctly_initialized() throws Throwable {
	    assertEquals(game.WIDTH, 400);
	    assertEquals(game.HEIGHT, 225);
	    assertEquals(game.playerScore, 0);
	    assertEquals(game.compScore, 0);
	}

	@Then("^class instances within packages are properly initiated$")
	public void class_instances_within_packages_are_properly_initiated() throws Throwable {
	    assertEquals(game.player.x, 10);
	    assertEquals(game.player.y, 60);
	    
	    assertEquals(game.compplayer.x, game.WIDTH - 25);
	    assertEquals(game.compplayer.y, 60);
	    
	    assertEquals(game.ball.x, (game.WIDTH/2));
	    assertEquals(game.ball.y, (game.HEIGHT/2));
	}

	@Given("^start method is called by thread$")
	public void start_method_is_called_by_thread() throws Throwable {
	    game.start();
	}

	@Then("^gameRunning variable assigned to true$")
	public void gamerunning_variable_assigned_to_true() throws Throwable {
	    assertTrue(game.gameRunning);
	}

}
