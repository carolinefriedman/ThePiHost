package BulletsTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import AlienTesting.Bullets;
import AlienTesting.Shooter;
import AlienTesting.spaceDefender;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class StepDefinitionsBullets {
	
	Shooter shooter = new Shooter(100, 100);
	Bullets bullet = new Bullets(shooter);
	spaceDefender game = new spaceDefender();
	
	@Given("^Bullets constructor is called$")
	public void bullets_constructor_is_called() throws Throwable {
	    // Bullets constructor called above
	}

	@Then("^make sure Bullets variables are initialized correctly$")
	public void make_sure_Bullets_variables_are_initialized_correctly() throws Throwable {
	    assertEquals(bullet.x, shooter.x + (shooter.width / 2));
	    assertEquals(bullet.y, shooter.y);
	    assertEquals(bullet.moveSpeed, shooter.moveSpeed);
	    assertTrue(bullet.inPlay);
	    
	    assertEquals(bullet.bulletSpeed, 10);
	    assertEquals(bullet.width, 3);
	    assertEquals(bullet.height, 3);
	    assertEquals(bullet.playerScore, 0);
	    assertFalse(bullet.moveLeft);
	    assertFalse(bullet.moveRight);
	    assertFalse(bullet.collision);
	}

	@Given("^y coord is less than zero$")
	public void y_coord_is_less_than_zero() throws Throwable {
	    bullet.y = -1;
	}

	@Given("^collision is true$")
	public void collision_is_true() throws Throwable {
	    bullet.collision = true;
	}

	@Then("^check that bullet coordinates equal shooter coordinates$")
	public void check_that_bullet_coordinates_equal_shooter_coordinates() throws Throwable {
	    bullet.tick(game);
	    assertEquals(bullet.x, game.player.x + (game.player.width / 2));
	    assertEquals(bullet.y, game.player.y);
	}

	@Then("^isShooting and collision booleans are set back to false$")
	public void isshooting_and_collision_booleans_are_set_back_to_false() throws Throwable {
	    assertFalse(bullet.isShooting);
	    assertFalse(bullet.collision);
	    //reset y coord back to 100
	    bullet.y = 100;
	}

	@Given("^isShooting is true \\(player presses spacebar\\)$")
	public void isshooting_is_true_player_presses_spacebar() throws Throwable {
	    bullet.isShooting = true;
	}

	@Then("^y coordinate of bullet increases by bulletSpeed$")
	public void y_coordinate_of_bullet_increases_by_bulletSpeed() throws Throwable {
		bullet.tick(game);
	    assertEquals(bullet.y, 100 - 10);
	}

	@Given("^user is pressing left arrow or spacebar$")
	public void user_isnt_pressing_left_arrow_or_spacebar() throws Throwable {
	    bullet.moveLeft = true;
	    bullet.isShooting = false;
	}

	@Given("^x coordinate of bullet is greater than zero$")
	public void x_coordinate_of_bullet_is_greater_than_zero() throws Throwable {
	    bullet.x = 100;
	}

	@Then("^x coordinate of bullet increases by moveSpeed$")
	public void x_coordinate_of_bullet_increases_by_moveSpeed() throws Throwable {
	    bullet.tick(game);
	    assertEquals(bullet.x, 100 - (shooter.moveSpeed));
	}
	
	/*
	 * 
	 * add test here for moveRight and isSHooting (see Bullets code)
	 * 
	 * 
	 */

}
