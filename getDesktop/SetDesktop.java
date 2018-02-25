package getDesktop;

//for class Graphics 2D
import java.awt.*;
//for class JWindow
import javax.swing.*;
//for class ImageIO
import javax.imageio.*;
//for class BufferedImage
import java.awt.image.*;
//for class File
import java.io.*;

public class SetDesktop extends JWindow
{
	String imageName;
	public SetDesktop(String imageName)
	{
		this.imageName=imageName;
		try
		{
			/*
			//adding a button to a JWindow
			final JPanel glass = (JPanel)this.getGlassPane();

			glass.setVisible(true);
			glass.setLayout(new GridBagLayout());
			JButton glassButton = new JButton("Hello");
			glass.add(glassButton);
			*/			
		
			BufferedImage bi=ImageIO.read(new File(imageName));
			//int height=bi.getHeight(); 
			//int width=bi.getWidth();
			//setSize(1366, 768);
			Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
			int width = (int) d.getWidth();
        	int height = (int) d.getHeight();
			setSize(width,height);
			
			
			/*
			//edit image......draw graphics.......resize to w,h
			int type=bi.getType();
			
			BufferedImage tmp=new BufferedImage(w,h,type);
			//BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			//g2.setRenderingHint(RenderingHints.KEY_DITHERING, hint);
			g2.drawImage(b, 0, 0, w, h, null);
			g2.dispose();
			*/
			
			setContentPane(new JLabel(new ImageIcon(bi)));	
			
		}	
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		setAlwaysOnTop(true);
		setVisible(true);	
	}	

}