package AlienBombTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import AlienTesting.Alien;
import AlienTesting.spaceDefender;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinitionsAlienBomb {
	
	Alien alien = new Alien(100, 100);
	spaceDefender game = new spaceDefender();
	
	@Given("^I call constructor, make sure variables are initialized correctly$")
	public void i_call_constructor_make_sure_variables_are_initialized_correctly() throws Throwable {
		assertEquals(alien.bomb.x, 100+(alien.width/2));
		assertEquals(alien.bomb.y, 100);
		assertEquals(alien.bomb.width, 3);
		assertEquals(alien.bomb.height, 6);
		assertEquals(alien.bomb.moveSpeed, 1);
		assertEquals(alien.bomb.bombSpeed, 1);
		assertFalse(alien.bomb.isShooting);
		assertFalse(alien.bomb.moveLeft);
		assertFalse(alien.bomb.moveRight);
		assertFalse(alien.bomb.collision);
	}

	@Given("^Player isnt shooting,$")
	public void player_isnt_shooting() throws Throwable {
		alien.bomb.isShooting = false;
	}

	@Then("^alien\\.y and alien\\.bomb\\.y should be equal$")
	public void alien_y_and_alien_bomb_y_should_be_equal() throws Throwable {
		alien.y = 10;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.y, 10);
	}

	@Given("^bomb is above the shooter$")
	public void bomb_is_above_the_shooter() throws Throwable {
		alien.bomb.y = game.player.y+1;
		alien.y = 13;
	}

	@Then("^the x and y coords of the bomb and the alien should be equal$")
	public void the_x_and_y_coords_of_the_bomb_and_the_alien_should_be_equal() throws Throwable {
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.x, alien.x + (alien.width / 2));
		assertEquals(alien.bomb.y, 13);
	}

	@Given("^Player isnt shooting$")
	public void player_isnt_shooting1() throws Throwable {
		alien.bomb.y = 10;
		alien.bomb.x = 10;
		alien.bomb.isShooting = true;
	}

	@Then("^the y coord of the bomb should increase by one when tick\\(\\) is called$")
	public void the_y_coord_of_the_bomb_should_increase_by_one_when_tick_is_called() throws Throwable {
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.y, 11);
		alien.bomb.isShooting = false;
	}

	@Given("^Alien not shooting$")
	public void alien_not_shooting() throws Throwable {
		alien.bomb.isShooting = false;
	}

	@Then("^the x coord of the alienBomb should follow the x coord of the alien$")
	public void the_x_coord_of_the_alienBomb_should_follw_the_x_coord_of_the_alien() throws Throwable {
		int xPosition = alien.x + alien.width/2;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.x, xPosition);
	}

	@Given("^a collision is detected between shooter and bomb$")
	public void a_collision_is_detected_between_shooter_and_bomb() throws Throwable {
		game.player.health = 100;
		alien.bomb.collision = true;
	}

	@Then("^it must decrease player health by one$")
	public void it_must_decrease_player_health_by_one() throws Throwable {
		alien.bomb.tick(game, alien);
		assertEquals(game.player.health, 99);
		assertFalse(alien.bomb.collision);
	}

}
