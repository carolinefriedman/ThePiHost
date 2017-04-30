package PlayerPaddleTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import pongCode.PlayerPaddle;
import pongCode.Tennis;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinitionsPlayerPaddle {
	
	PlayerPaddle paddle = new PlayerPaddle(50, 10);
	Tennis game = new Tennis();
	
	@Given("^I call the PlayerPaddle constructor$")
	public void i_call_the_PlayerPaddle_constructor() throws Throwable {
	    //PlayerPaddle constructor called above, globally
	}

	@Then("^class variables for PlayerPaddle are correctly initialized$")
	public void class_variables_for_PlayerPaddle_are_correctly_initialized() throws Throwable {
	    assertEquals(paddle.x, 50);
	    assertEquals(paddle.y, 10);
	    assertEquals(paddle.width, 5);
	    assertEquals(paddle.height, 40);
	    assertEquals(paddle.paddleSpeed, 3);
	    assertFalse(paddle.moveUp);
	    assertFalse(paddle.moveDown);
	}

	@Given("^user presses w on keyboard$")
	public void user_presses_w_on_keyboard() throws Throwable {
		paddle.moveUp = true;
	}

	@Given("^y coordinate of paddle is greater zero$")
	public void y_coordinate_of_paddle_is_greater_zero() throws Throwable {
	    paddle.y = 10;
	}

	@Given("^PlayerPaddle tick method is called$")
	public void playerpaddle_tick_method_is_called() throws Throwable {
	    paddle.tick(game);
	}

	@Then("^y coordinate of player paddle decreases by paddleSpeed$")
	public void y_coordinate_of_player_paddle_decreases_by_paddleSpeed() throws Throwable {
	    assertEquals(paddle.y, 7);
	    paddle.moveUp = false;
	}

	@Given("^user presses s on keyboard$")
	public void user_presses_s_on_keyboard() throws Throwable {
		paddle.moveDown = true;
	}

	@Given("^y coordinate of paddle is bounded by lower edge of screen$")
	public void y_coordinate_of_paddle_is_bounded_by_lower_edge_of_screen() throws Throwable {
	    paddle.y = 10;
	}

	@Then("^y coordinate of player paddle increases by paddleSpeed$")
	public void y_coordinate_of_player_paddle_increases_by_paddleSpeed() throws Throwable {
		assertEquals(paddle.y, 13);
	    paddle.moveDown = false;
	}
}
