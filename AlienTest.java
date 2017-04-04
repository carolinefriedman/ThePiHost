package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlienTest {
	
	//test constructor
	@Test
	public void constructorTest() {
		Alien alien = new Alien(100, 50);
		assertEquals(alien.x, 100);
		assertEquals(alien.y, 50);
		assertEquals(alien.width, 15);
		assertEquals(alien.height, 20);
		assertEquals(alien.moveSpeed, 1);
		
		assertFalse(alien.isDead);
	}
	
	//test Alien tick method
	@Test
	public void tickTest(){
		Alien alien = new Alien(100, 50);
		spaceDefender game = new spaceDefender();
		
		// does movement increase x coord?
		alien.tick(game);
		assertEquals(alien.x, 101);
		
		//alien exceeded right screen bound,
		// does it turn around?
		alien.x = game.WIDTH;
		alien.tick(game);
		assertEquals(alien.moveSpeed, -1);
		
		// does negative movespeed 
		// (left movement) work?
		alien.x = 100;
		alien.tick(game);
		assertEquals(alien.x, 99);
		
		// does change from moving left
		// to moving right work?
		alien.x = 0;
		alien.tick(game);
		assertEquals(alien.moveSpeed, 1);
		
		/*

		check level up moveSpeed
		
		game.alienMatrix.isDead = true;
		game.render();
		assertEquals(alien.moveSpeed, 2);
		
		*/
	}

}

