package getDesktop;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class CreateImage
{
	public static int r,g,b;
	public static String imageName;
	public CreateImage(int r,int g,int b)
	{
		this.r=r;
		this.g=g;
		this.b=b;	
		imageName="";
	}
	public static void generateImage()
	{
		/**
         * Get the current screen dimensions.
         */
		 
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
		
		//BufferedImage object
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//File object
		File f=null;
		
		//generate pixel colors	
		//p = (a<<24) | (r<<16) | (g<<8) | b ;
		int p=r;
		p=(p<<8)+g;
		p=(p<<8)+b;
		for(int x=0 ; x<width ; ++x)
		for(int y=0 ; y<height ; ++y)
		{
			image.setRGB(x,y,p);
		}
		
		//write image to disk
		try
		{
			String temp=new File(".").getAbsolutePath();
			temp.replaceAll("%20"," ");
			temp=temp.substring(0,temp.length()-1);
			imageName=temp+"newWallpaper.png";
			f=new File(imageName);
			ImageIO.write(image,"png",f);	
			System.out.println("Image Created");
		}
		catch(IOException e)
		{
			System.out.println("Exception : " + e);	
		}
	}	
	
}	