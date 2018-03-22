package dkeep.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import dkeep.logic.Game;

public class Panel extends JPanel implements KeyListener{
	Game game;
	
	
	public Panel(Game a)
	{
		addKeyListener(this);
		game = a;
	}
		
		
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: game.update(Game.Direction.UP);break;
		case KeyEvent.VK_A: game.update(Game.Direction.LEFT);break;
		case KeyEvent.VK_S: game.update(Game.Direction.DOWN);break;
		case KeyEvent.VK_D: game.update(Game.Direction.RIGHT);break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
