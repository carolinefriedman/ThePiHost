package GameIOTesting;

import java.awt.event.KeyEvent;

import AlienTesting.GameIO;
import AlienTesting.spaceDefender;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitionsGameIO {
	
	spaceDefender game = new spaceDefender();
	GameIO io;
	int event;
	
	@Given("^GameIO constructor is called, make sure no errors are raised$")
	public void gameio_constructor_is_called_ame_sure_no_errors_are_raised() throws Throwable {
	    io = new GameIO(game);
	}

	@Given("^user pressed left arrow$")
	public void user_pressed_left_arrow() throws Throwable {
	    event = KeyEvent.VK_LEFT;
	}

	@Then("^player moves left$")
	public void player_moves_left() throws Throwable {
	    io.key_Pressed(event);
	    //assertTrue(spaceDefender.player.moveLeft);
	}

	@Then("^bullet moves left$")
	public void bullet_moves_left() throws Throwable {
		assertTrue(game.player.bullet.moveLeft);
	}

	@Given("^user pressed right arrow$")
	public void user_pressed_right_arrow() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^player moves right$")
	public void player_moves_right() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^bullet moves right$")
	public void bullet_moves_right() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^user pressed spacebar$")
	public void user_pressed_spacebar() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^player bullet is shooting$")
	public void player_bullet_is_shooting() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^spaceDefender\\.entered equals true$")
	public void spacedefender_entered_equals_true() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^user releases left arrow$")
	public void user_releases_left_arrow() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^player stops moving left$")
	public void player_stops_moving_left() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^bullet stops moving left$")
	public void bullet_stops_moving_left() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^user releases right arrow$")
	public void user_releases_right_arrow() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^player stops moving right$")
	public void player_stops_moving_right() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^bullet stops moving right$")
	public void bullet_stops_moving_right() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
