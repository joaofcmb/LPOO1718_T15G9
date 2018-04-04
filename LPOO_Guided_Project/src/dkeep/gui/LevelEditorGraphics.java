package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LevelEditorGraphics extends JPanel {
	private static final long serialVersionUID = 7707825193902717088L;

	static final int SIZE = 50; //Image size for drawImage
	private LevelEditor lvlEditor;

	private BufferedImage hero, wall, lDoor, uDoor, armedHero, akHero, key, ogre, guard, ogreClub;

	private int width, height;
	public LevelEditorGraphics(int w, int h, LevelEditor le) 
	{
		width = w;
		height = h;
		lvlEditor = le;
		initialise();
	}

	private void initialise()
	{
		initMouse();
		updateGraphics();
		loadImages();
	}

	private void updateGraphics() 
	{
		this.repaint();
		this.requestFocusInWindow();
	}

	private void loadImages()
	{
		System.out.println("Loading Images...");
		try{

			wall = ImageIO.read(new File("src/Images/Wall.png"));
			lDoor = ImageIO.read(new File("src/Images/LockedDoor.png"));
			armedHero = ImageIO.read(new File("src/Images/ArmedHero.png"));
			key = ImageIO.read(new File("src/Images/Key.png"));
			ogre = ImageIO.read(new File("src/Images/Ogre.png"));

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
		for(int j = 0; j < lvlEditor.getMap().length; j++) {
			for(int i = 0; i < lvlEditor.getMap()[j].length; i++)
			{
				switch(lvlEditor.getMap()[j][i])
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
		updateGraphics();
	}
	private void initMouse()
	{
		this.addMouseListener(
				new MouseListener() 
				{
					@Override
					public void mouseClicked(MouseEvent e) {
						double xCoord = getMousePosition().getY()/LevelEditorGraphics.SIZE;
						double yCoord = getMousePosition().getX()/LevelEditorGraphics.SIZE;

						for (int i = 1; i <= width;i++)
							for (int j = 1 ; j <= height; j++){
								if (xCoord >= (i-1) && xCoord < i && yCoord >=(j-1) && yCoord < j)
								{
									if (lvlEditor.getMap()[i-1][j-1] == ' ' && lvlEditor.getSelection() != ' ') //if cell is empty
									{
										lvlEditor.getMap()[i-1][j-1] = lvlEditor.getSelection();
										lvlEditor.checkElement(i-1,j-1, 1);
									}
									else if (lvlEditor.getMap()[i-1][j-1]  == lvlEditor.getSelection()) // if you select the wrong cell
									{
										lvlEditor.checkElement(i-1,j-1, -1);
										lvlEditor.getMap()[i-1][j-1] = ' ';

									}
									else if (lvlEditor.getMap()[i-1][j-1]  != ' ') //if you want to replace a cell
									{
										lvlEditor.checkElement(i-1,j-1, -1);
										lvlEditor.getMap()[i-1][j-1] = lvlEditor.getSelection();	
										lvlEditor.checkElement(i-1,j-1,1);
									}
								}
							}
					}
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
				});
	}
}
