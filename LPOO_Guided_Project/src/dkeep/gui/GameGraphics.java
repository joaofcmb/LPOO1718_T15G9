package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GameGraphics extends JPanel {
	private static final long serialVersionUID = 7707825193902717088L;

	private static final int SIZE = 50; //Image size for drawImage
	private Game game;

	private BufferedImage hero, wall, lDoor, uDoor, armedHero, akHero, key, ogre, guard, ogreClub; //TODO add others later

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
		System.out.println("Loading Images...");
		try{

			hero = ImageIO.read(new File("src/Images/Hero.png"));
			wall = ImageIO.read(new File("src/Images/Wall.png"));
			lDoor = ImageIO.read(new File("src/Images/LockedDoor.png"));
			uDoor = ImageIO.read(new File("src/Images/UnlockedDoor.png"));
			armedHero = ImageIO.read(new File("src/Images/ArmedHero.png"));
			akHero = ImageIO.read(new File("src/Images/ArmedKeyHero.png"));
			key = ImageIO.read(new File("src/Images/Key.png"));
			guard = ImageIO.read(new File("src/Images/Guard.png"));
			ogre = ImageIO.read(new File("src/Images/Ogre.png"));
			ogreClub = ImageIO.read(new File("src/Images/OgreClub.png"));


		}catch(IOException e)
		{
			System.out.println("An error occured while loading Images.");
			e.printStackTrace();
		}
		System.out.println("Images Loaded.");
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		char[][] map = game.getMap().charMap();
		for(int j = 0; j < map.length; j++) {
			for(int i = 0; i < map[j].length; i++)
			{
				switch(map[j][i])
				{
				case 'P':{//Hero
					g.drawImage(hero, i*SIZE, j*SIZE, SIZE, SIZE, null);	
					break;
				}
				case 'X':{//Wall
					g.drawImage(wall, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case 'I':{//Locked Door
					g.drawImage(lDoor, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case 'S':{//Unlocked Door
					g.drawImage(uDoor, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case 'A':{//Armed Hero
					g.drawImage(armedHero, i*SIZE, j*SIZE, SIZE, SIZE, null);	
					break;
				}
				case 'K':{//Armed Hero
					g.drawImage(akHero, i*SIZE, j*SIZE, SIZE, SIZE, null);	
					break;
				}
				case 'G':{//Guard
					g.drawImage(guard, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case 'O':{//Ogre
					g.drawImage(ogre, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case '*':{//Ogre Club
					g.drawImage(ogreClub, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				case 'k':{//key/lever
					g.drawImage(key, i*SIZE, j*SIZE, SIZE, SIZE, null);
					break;
				}
				default:{//White Space
					g.setColor(Color.BLACK);
				}
				}
			}
		}
		updateGame();
	}
}
