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
	private static final long serialVersionUID = 1L;

	private static final int SIZE = 50; //Image size for drawImage
	private Game game;

	private BufferedImage hero; //TODO add others later

	public GameGraphics(Game g) 
	{
		super();
		game = g;
		initialise();
	}

	private void initialise()
	{
		update();
		loadImages();
	}

	protected void update() 
	{
		this.repaint();
		this.requestFocusInWindow();
	}

	private void loadImages()
	{
		try{
			hero = ImageIO.read(new File("src/Images/hero.png"));
		}catch(IOException e)
		{
			System.out.println("An error occured while loading Images.");
			e.printStackTrace();
		}
	}

	@Override 
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		char[][] map = this.game.getMap().charMap();

		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
			{
				switch(map[i][j])
				{
				case 'H':{//Hero
					g.drawImage(hero, i*SIZE, j*SIZE, SIZE, SIZE, null);			
					break;
				}
				/*	case 'X':{//Wall

					break;
				}
				case 'I':{//Locked Door

					break;
				}
				case 'S':{//Unlocked Door

					break;
				}

				case 'A':{//Armed Hero

					break;
				}
				case 'G':{//Guard

					break;
				}
				case 'O':{//Ogre

					break;
				}
				case '*':{//Ogre Club
					break;
				}
				case 'k':{//key/lever

					break;
				}
				 */
				default:{//White Space
					g.setColor(Color.BLACK);
				}
				}
			}
	}
}
