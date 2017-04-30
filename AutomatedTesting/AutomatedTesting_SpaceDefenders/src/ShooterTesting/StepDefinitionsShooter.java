package ShooterTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import AlienTesting.Shooter;
import AlienTesting.spaceDefender;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinitionsShooter {
	
	Shooter shooter = new Shooter(100, 100);
	spaceDefender game = new spaceDefender();
	
	@Given("^Shooter constructor is called$")
	public void shooter_constructor_is_called() throws Throwable {
		//constructor called as global variable above
	}

	@Then("^make sure variables are initialized correctly$")
	public void make_sure_variables_are_initialized_correctly() throws Throwable {
		assertEquals(shooter.x, 100);
		assertEquals(shooter.y, 100);
		assertEquals(shooter.health, 100);
		assertEquals(shooter.width, 30);
		assertEquals(shooter.height, 10);
		assertEquals(shooter.moveSpeed, 3);
		
		assertFalse(shooter.moveLeft);
		assertFalse(shooter.moveRight);
	}

	@Given("^User presses left arrow$")
	public void user_presses_left_arrow() throws Throwable {
		shooter.moveLeft = true;
	}

	@Given("^x coordinate of shooter is greater than (\\d+)$")
	public void x_coordinate_of_shooter_is_greater_than(int arg1) throws Throwable {
		assertEquals(shooter.x, 100);
	}

	@Then("^shooter x coordinate should decrement by 'moveSpeed'$")
	public void shooter_x_coordinate_should_decrement_by_moveSpeed() throws Throwable {
		shooter.tick(game);
		assertEquals(shooter.x, 97);
		shooter.moveLeft = false;
	}

	@Given("^x coordinate of shooter is less than (\\d+)$")
	public void x_coordinate_of_shooter_is_less_than(int arg1) throws Throwable {
		shooter.x = -1;
	}

	@Then("^shooter x coordinate should not change$")
	public void shooter_x_coordinate_should_not_change() throws Throwable {
		shooter.tick(game);
		assertEquals(shooter.x, -1);
		shooter.moveLeft = false;
	}

	@Given("^User presses right arrow$")
	public void user_presses_right_arrow() throws Throwable {
		shooter.moveRight = true;
	}

	@Given("^x coordinate of shooter is less than screenwidth - width of shooter graphic$")
	public void x_coordinate_of_shooter_is_less_than_screenwidth_width_of_shooter_graphic() throws Throwable {
		shooter.x = 100;
	}

	@Then("^shooter x coordinate should increment by 'moveSpeed'$")
	public void shooter_x_coordinate_should_increment_by_moveSpeed() throws Throwable {
		shooter.tick(game);
		assertEquals(shooter.x, 103);
		shooter.moveRight = false;
	}

	@Given("^x coordinate of shooter is greater than screenwidth - width of shooter graphic$")
	public void x_coordinate_of_shooter_is_greater_than_screenwidth_width_of_shooter_graphic() throws Throwable {
		shooter.x = game.WIDTH;
	}
	
	@Then("^shooter x coordinate should not move right$")
	public void shooter_x_coordinate_should_not_move_right() throws Throwable {
		shooter.tick(game);
		assertEquals(shooter.x, game.WIDTH);
		shooter.moveRight = false;
	}
}
