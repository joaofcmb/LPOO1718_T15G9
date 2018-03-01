package dkeep.logic;

import java.util.Random;

public class Ogre extends Enemy {
	Random random = new Random();
	//club
	public Ogre() {
		super();
	}

	public Ogre(int x, int y, char symbol) {
		super(x, y, symbol);
	}

	public void move() {
		boolean impossibleMovement = true;
		do
		{
			int r = this.random.nextInt(4);
			if(r == 0 && this.xPos !=1) {
				this.xPos--;
				impossibleMovement = false;
			}
			else if(r == 1 && this.yPos !=1) {
				this.yPos--;
				impossibleMovement = false;
			}
			else if(r == 2 && this.xPos !=6) {
				this.xPos++;
				impossibleMovement = false;
			}
			else if(this.xPos !=6) {
				this.yPos++;
				impossibleMovement = false;
			}
		}
		while(impossibleMovement);
	}
}