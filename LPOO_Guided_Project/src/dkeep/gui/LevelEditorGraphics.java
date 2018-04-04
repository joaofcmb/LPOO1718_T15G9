package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LevelEditorGraphics extends JPanel {
	private static final long serialVersionUID = 7707825193902717088L;

	static final int SIZE = 50; //Image size for drawImage
	private LevelEditor lvlEditor;

	private HashMap<String, BufferedImage> imageMap;

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
		imageMap = new HashMap<String, BufferedImage>();
		System.out.println("Loading Images...");
		try {
			imageMap.put("X", ImageIO.read(new File("src/Images/Wall.png")));
			imageMap.put("I", ImageIO.read(new File("src/Images/LockedDoor.png")));
			imageMap.put("A", ImageIO.read(new File("src/Images/ArmedHero.png")));
			imageMap.put("k", ImageIO.read(new File("src/Images/Key.png")));
			imageMap.put("O", ImageIO.read(new File("src/Images/Ogre.png")));
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
		for(int j = 0; j < lvlEditor.getMap().length; j++) { 
			for(int i = 0; i < lvlEditor.getMap()[j].length; i++) 
			{
				g.fillRect(i*SIZE, j*SIZE, SIZE, SIZE);
				g.drawImage(imageMap.get(Character.toString(lvlEditor.getMap()[j][i])), i*SIZE, j*SIZE, SIZE, SIZE, null);
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
									lvlEditor.mouseHandler(i, j);
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
