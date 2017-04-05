package spaceDefenderTesting;

import AlienTesting.spaceDefender;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class StepDefinitionsSpaceDefender {
	
	spaceDefender game = new spaceDefender();
	
	@Given("^spaceDefender constructor is called$")
	public void spacedefender_constructor_is_called() throws Throwable {
	    //called above
	}

	@Then("^make sure variables are for spaceDefender are initialized correctly$")
	public void make_sure_variables_are_for_spaceDefender_are_initialized_correctly() throws Throwable {
		assertFalse(game.gameRunning);
		assertEquals(game.level, 1);
		assertEquals(game.dropTimer, 100);
		assertEquals(game.WIDTH, 600);
		assertEquals(game.HEIGHT, 358);
		assertFalse(game.entered);
	}
	
	@Given("^spaceDefender start metod is called$")
	public void spacedefender_start_metod_is_called() throws Throwable {
	    game.start();
	}

	@Then("^game is running$")
	public void game_is_running() throws Throwable {
	    assertTrue(game.gameRunning);
	}

	@Given("^counter does not equal zero$")
	public void counter_does_not_equal_zero() throws Throwable {
	    game.cnt = 13;
	}

	@Given("^counter is not divisible by dropTimer$")
	public void counter_is_not_divisible_by_dropTimer() throws Throwable {
	    game.dropTimer = 10;
	    game.tick();
	}

	@Then("^x is assigned a variable zero through two$")
	public void x_is_assigned_a_variable_zero_through_two() throws Throwable {
	    assertTrue((game.x < 3));
	    assertTrue((game.x >= 0));
	}

	@Then("^y is assigned a variable zero through nine$")
	public void y_is_assigned_a_variable_zero_through_nine() throws Throwable {
		assertTrue((game.y < 9));
		assertTrue((game.y >= 0));
	}

	@Given("^allDead is true \\(all aliens killed\\)$")
	public void alldead_is_true_all_aliens_killed() throws Throwable {
		game.entered = true;
		game.allDead2 = true;
	    game.render();
	}

	@Then("^alienMatrix reassigned to false$")
	public void alienmatrix_reassigned_to_false() throws Throwable {
		for (int i = 0; i < 3; i ++){
	          for (int j = 0; j < 10; j++){
	            assertFalse(game.alienMatrix[i][j].isDead);
	          }
	    }
	}
}