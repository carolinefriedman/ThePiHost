package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShooterTest {

	// testing Shooter constructor
	@Test
	public void constructorTest() {
		Shooter shooter = new Shooter(50, 60);
		assertEquals(shooter.x, 50);
		assertEquals(shooter.y, 60);
		assertEquals(shooter.health, 100);
		assertEquals(shooter.width, 30);
		assertEquals(shooter.height, 10);
		assertEquals(shooter.moveSpeed, 3);
		
		assertFalse(shooter.moveLeft);
		assertFalse(shooter.moveRight);
	}
	
	// testing Shooter tick method
	@Test
	public void tickTest(){
		Shooter shooter2 = new Shooter(100, 100);
		spaceDefender game = new spaceDefender();
		
		// checking that conditionals for leftword movement behave as intended
		shooter2.moveLeft = true;
		shooter2.tick(game);
		assertEquals(shooter2.x, 97);
		shooter2.x = -1;
		shooter2.tick(game);
		assertEquals(shooter2.x, -1);
		shooter2.moveLeft = false;
		
		// checking that conditionals for rightword movement behave as intended
		shooter2.x = 100;
		shooter2.moveRight = true;
		shooter2.tick(game);
		assertEquals(shooter2.x, 103);
		shooter2.x = game.WIDTH;
		shooter2.tick(game);
		assertEquals(shooter2.x, game.WIDTH);
		shooter2.moveRight = false;
	}

}

