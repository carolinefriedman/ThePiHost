package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienBombTesting {

	// testing constructor
	@Test
	public void constructorTest() {
		Alien alien = new Alien(100, 100);
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
	
	//testing AlienBomb tick() method
	@Test
	public void tickTest(){
		// if the player isnt shooting, alien.y and alien.bomb.y should be equal
		spaceDefender game = new spaceDefender();
		Alien alien = new Alien(100, 100);
		alien.y = 10;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.y, 10);
		
		// if bomb is above the shooter, the x and y coords
		// of the bomb and the alien should be equal
		alien.bomb.y = game.player.y+1;
		alien.y = 13;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.x, alien.x + (alien.width / 2));
		assertEquals(alien.bomb.y, 13);
		
		// when the player is shooting, the y coord
		// of the bomb should increase by one each time tick() is called
		alien.bomb.y = 10;
		alien.bomb.x = 10;
		alien.bomb.isShooting = true;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.y, 11);
		
		// if alien isnt shooting, the x coord of the alienBomb
		// should follw the x coord of the alien
		alien.bomb.isShooting = false;
		int xPosition = alien.x + alien.width/2;
		alien.bomb.tick(game, alien);
		assertEquals(alien.bomb.x, xPosition);
		
		//testing private collide() method from tick()
		// if a collision is detected, it must decrease player health
		game.player.health = 100;
		alien.bomb.collision = true;
		alien.bomb.tick(game, alien);
		assertEquals(game.player.health, 99);
		assertFalse(alien.bomb.collision);
	}
}

