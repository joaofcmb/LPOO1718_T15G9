package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GameGraphics extends JPanel {
	private static final long serialVersionUID = 7707825193902717088L;

	private static final int SIZE = 50; //Image size for drawImage
	private Game game;

	private HashMap<String, BufferedImage> imageMap;

	public GameGraphics(Game g) 
	{
		game = g;
		initialise();
	}

	private void initialise()
	{
		updateGame();
		loadImages();
	}

	private void updateGame() 
	{
		this.repaint();
		this.requestFocusInWindow();
	}

	private void loadImages()
	{
		imageMap = new HashMap<String, BufferedImage>();
		System.out.println("Loading Images...");
		try {
			imageMap.put("P", ImageIO.read(new File("src/Images/Hero.png")));
			imageMap.put("X", ImageIO.read(new File("src/Images/Wall.png")));
			imageMap.put("I", ImageIO.read(new File("src/Images/LockedDoor.png")));
			imageMap.put("S", ImageIO.read(new File("src/Images/UnlockedDoor.png")));
			imageMap.put("A", ImageIO.read(new File("src/Images/ArmedHero.png")));
			imageMap.put("K", ImageIO.read(new File("src/Images/ArmedKeyHero.png")));
			imageMap.put("k", ImageIO.read(new File("src/Images/Key.png")));
			imageMap.put("G", ImageIO.read(new File("src/Images/Guard.png")));
			imageMap.put("O", ImageIO.read(new File("src/Images/Ogre.png")));
			imageMap.put("*", ImageIO.read(new File("src/Images/OgreClub.png")));
		} catch(IOException e)
		{
			System.out.println("An error occured while loading Images.");
			e.printStackTrace();
		}
		System.out.println("Images Loaded.");
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		char[][] map = game.getMap().charMap();
		for(int j = 0; j < map.length; j++) {
			for(int i = 0; i < map[j].length; i++)
			{
				g.fillRect(i*SIZE, j*SIZE, SIZE, SIZE);
				g.drawImage(imageMap.get(Character.toString(map[j][i])), i*SIZE, j*SIZE, SIZE, SIZE, null);
			}
		}
		updateGame();
	}
}
