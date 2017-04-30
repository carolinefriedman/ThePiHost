package ComputerPaddleTesting;

import pongCode.ComputerPaddle;
import pongCode.Tennis;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StepDefinitionsComputerPaddle {
	
	Tennis game = new Tennis();
	ComputerPaddle cpaddle = new ComputerPaddle(100, 100);
	
	@Given("^I call the ComputerPaddle constructor$")
	public void i_call_the_ComputerPaddle_constructor() throws Throwable {
	    //ComputerPaddle constructor called above, globally
	}

	@Then("^class variables for ComputerPaddle are correctly initialized$")
	public void class_variables_for_ComputerPaddle_are_correctly_initialized() throws Throwable {
		assertEquals(cpaddle.x, 100);
		assertEquals(cpaddle.y, 100);
		assertEquals(cpaddle.width, 5);
		assertEquals(cpaddle.height, 40);
		assertEquals(cpaddle.paddleSpeed, 3);
		assertFalse(cpaddle.goingUp);
		assertFalse(cpaddle.goingDown);
	}

	@Given("^ball is moving up on screen$")
	public void ball_is_moving_up_on_screen() throws Throwable {
	    game.ball.y = 10;
	}

	@Given("^y coordinate of paddle is greater than or equal to zero$")
	public void y_coordinate_of_paddle_is_greater_than_or_equal_to_zero() throws Throwable {
	    cpaddle.y = 100;
	}

	@Given("^ComputerPaddle tick method is called$")
	public void computerpaddle_tick_method_is_called() throws Throwable {
	    cpaddle.tick(game);
	}

	@Then("^y coordinate of paddle decreases by paddleSpeed$")
	public void y_coordinate_of_paddle_decreases_by_paddleSpeed() throws Throwable {
	    assertEquals(cpaddle.y, 97);
	}

	@Given("^ball is moving down on screen$")
	public void ball_is_moving_down_on_screen() throws Throwable {
	    cpaddle.y = 100;
	    game.ball.y = 110;
	}

	@Given("^y coordinate of paddle is within lower bound of screen$")
	public void y_coordinate_of_paddle_is_within_lower_bound_of_screen() throws Throwable {
	    cpaddle.y = 100;
	}

	@Then("^y coordinate of paddle increases by paddleSpeed$")
	public void y_coordinate_of_paddle_increases_by_paddleSpeed() throws Throwable {
		assertEquals(cpaddle.y, 103);
	}
}
