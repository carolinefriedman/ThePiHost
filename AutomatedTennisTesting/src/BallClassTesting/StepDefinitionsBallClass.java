package BallClassTesting;

import pongCode.Ball;
import pongCode.Tennis;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;

public class StepDefinitionsBallClass {
	
	Tennis game = new Tennis();
	Ball ball = new Ball(50, 50);
	
	@Given("^I call the Ball constructor$")
	public void i_call_the_Ball_constructor() throws Throwable {
	    //ball constructor called above, globally
	}

	@Then("^class variables for Ball are correctly initialized$")
	public void class_variables_for_Ball_are_correctly_initialized() throws Throwable {
	    assertEquals(ball.x, 50);
	    assertEquals(ball.y, 50);
	    assertEquals(ball.size, 10);
	    assertEquals(ball.speed, 2);
	    assertEquals(ball.velocityX, 2);
	    assertEquals(ball.velocityY, 2);
	}

	@Given("^ball hits left wall \\(ball x coordinate less than or equal to zero\\)$")
	public void ball_hits_left_wall_ball_x_coordinate_less_than_or_equal_to_zero() throws Throwable {
	    ball.x = 0;
	}

	@Given("^Ball tick method is called$")
	public void ball_tick_method_is_called() throws Throwable {
	    ball.tick(game);
	}

	@Then("^velocityX speed of ball is positive$")
	public void velocityx_speed_of_ball_is_positive() throws Throwable {
	    assertEquals(ball.velocityX, 2);
	}

	@Then("^computer player score increments$")
	public void computer_player_score_increments() throws Throwable {
	    assertEquals(game.compScore, 1);
	}

	@Given("^ball hits right wall$")
	public void ball_hits_right_wall() throws Throwable {
		ball.x = game.WIDTH;
	}

	@Then("^velocityX speed of ball is negative$")
	public void velocityx_speed_of_ball_is_negative() throws Throwable {
		assertEquals(ball.velocityX, -2);
	}

	@Then("^player score increments$")
	public void player_score_increments() throws Throwable {
		assertEquals(game.playerScore, 1);
	}

	@Given("^ball hits top wall$")
	public void ball_hits_top_wall() throws Throwable {
	    ball.y = 0;
	}

	@Then("^velocityY speed of ball is positive$")
	public void velocityy_speed_of_ball_is_positive() throws Throwable {
	    assertEquals(ball.velocityY, 2);
	}

	@Given("^ball hits bottom wall$")
	public void ball_hits_bottom_wall() throws Throwable {
		ball.y = game.HEIGHT;
	}

	@Then("^velocityY speed of ball is negative$")
	public void velocityy_speed_of_ball_is_negative() throws Throwable {
		assertEquals(ball.velocityY, -2);
	}
}
