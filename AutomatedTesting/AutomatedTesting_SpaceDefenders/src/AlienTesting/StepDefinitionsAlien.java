package AlienTesting;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



public class StepDefinitionsAlien {

	Alien alien = new Alien(100, 50);
	spaceDefender game = new spaceDefender();
	
	@Given("^Alien constructor is called$")
	public void alien_constructor_is_called() throws Throwable {
	    //constructor called above, globally
	}

	@Then("^all alien variabled properly initialized$")
	public void all_alien_variabled_properly_initialized() throws Throwable {
		assertEquals(alien.x, 100);
		assertEquals(alien.y, 50);
		assertEquals(alien.width, 15);
		assertEquals(alien.height, 20);
		assertEquals(alien.moveSpeed, 1);
		assertFalse(alien.isDead);
	}

	@Given("^alien tick method is called$")
	public void alien_tick_method_is_called() throws Throwable {
		// does movement increase x coord?
		alien.tick(game);
	}

	@Then("^alien's x coord should increase by moveSpeed$")
	public void alien_s_x_coord_should_increase_by_moveSpeed() throws Throwable {
		assertEquals(alien.x, 101);
	}
	
	@Given("^x coordinate of alien is greater than or equal to game\\.WIDTH - width$")
	public void x_coordinate_of_alien_is_greater_than_or_equal_to_game_WIDTH_width() throws Throwable {
		alien.x = game.WIDTH;
	}

	@Then("^moveSpeed should be negated \\(turning alien around\\)$")
	public void movespeed_should_be_negated_turning_alien_around() throws Throwable {
		alien.tick(game);
		assertEquals(alien.moveSpeed, -1);
	}

	@Then("^x should be incremented by moveSpeed$")
	public void x_should_be_incremented_by_moveSpeed() throws Throwable {
		alien.x = 100;
		alien.tick(game);
		assertEquals(alien.x, 99);
	}

	@Given("^x coordinate of alien is less than or equal to zero$")
	public void x_coordinate_of_alien_is_less_than_or_equal_to_zero() throws Throwable {
	    alien.x = -1;
	    alien.moveSpeed = -1;
		alien.tick(game);
		assertEquals(alien.moveSpeed, 1);
	    
	}
}
