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

public class ProcessImage extends JWindow
{
	final int size=120;
	String imageName;
	public int[] x1,x2;
	public int[] y1,y2;
	int R,G,B;
	int a,r,g,b,w,h;
	int taskbarHeight;
	public BufferedImage bi;
	boolean search=false;
	public ProcessImage(String imageName , int R , int G , int B)
	{
		this.imageName=imageName;
		try
		{
			this.R=R;
			this.G=G;
			this.B=B;
			bi=ImageIO.read(new File(imageName));	
			setSize(bi.getWidth(),bi.getHeight());
			w = bi.getWidth();
		    h = bi.getHeight();
			x1=new int[size];
			y1=new int[size];
			x2=new int[size];
			y2=new int[size];
		    System.out.println("width, height: " + w + ", " + h);
			
			
			
			//calculate taskbarHeight
			int k;
			for(k=h-1;k>0;--k)
			{
				int pixel = bi.getRGB(0,k);
				pixelARGB(pixel);
				if(r==R && g== G && b== B)	
					break;
			}
			taskbarHeight=h-k;	
			h=h-taskbarHeight;	
			//column wise search
			/*
			x will store the distance of an icon from left side of the image
			*/
			int n=0;
			x1[n]=0;
			x2[n]=0;
			int dist=15;
			n++;
			for (int i = 0; i < w; i++) 
			{
				for (int j = 0; j < h; j++) 
				{
					int pixel = bi.getRGB(i, j);
					pixelARGB(pixel);
					if(r!=R || g!= G || b!= B)
					{
						//System.out.print(i+" " +j+" "+ r + " "+ g + " " + b+ ".");
						x1[n]=i++;
						x2[n]=j;
						n++;
						if(x1[n-1]-x1[n-2]<dist)
							n--;
						while(i <w)
						{
							pixel = bi.getRGB(i,j);
							pixelARGB(pixel);
							//System.out.print(i+" " +j+" "+ r + " "+ g + " " + b+ ".");
							if(r==R && g==G && b==B)
								break;	
							i++;
						}						
						x1[n]=i;
						x2[n]=j;
						n++;
						if(x1[n-1]-x1[n-2]<dist)
							n--;
						break;
					}
				}
			}
			/*
			y will store the distance of an icon from top
			*/
			//row wise search
			n=0;
			y1[n]=0;
			y2[n]=0;
			
			n++;
			for (int i = 0; i < h; i++) 
			{
				for (int j = 0; j < w; j++) 
				{
					int pixel = bi.getRGB(j, i);
					pixelARGB(pixel);
					if(r!=R || g!= G || b!= B)
					{
						y1[n]=i;
						y2[n]=j;
						n++;
						if(y1[n-1]-y1[n-2]<dist)
							n--;
						while(i<h)
						{
							++i;
							pixel = bi.getRGB(j, i);
							pixelARGB(pixel);
							if(r==R && g==G && b==B)
								break;	
						}
						y1[n]=i;
						y2[n]=j;
						n++;
						if(y1[n-1]-y1[n-2]<dist)
							n--;
						break;
					}
				}
			}
			
			
			/*
			for (int i = 0; i < h; i++) 
			{
		      for (int j = 0; j < w; j++) 
			  {
        		//System.out.print("x,y: " + j + ", " + i + "   ");
		        int pixel = bi.getRGB(j, i);
				pixelARGB(pixel,j,i);
		      }
		    }
			*/
		}	
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		/*
		make grids
		and set image as window background
		
		*/
		h=h+taskbarHeight;
		int type=bi.getType();
			
		BufferedImage tmp=new BufferedImage(bi.getWidth(),bi.getHeight(),type);
		//BufferedImage tmp = new BufferedImage(w, h, type);
		Graphics2D g2 = tmp.createGraphics();
		//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
		//g2.setRenderingHint(RenderingHints.KEY_DITHERING, hint);
		g2.drawImage(bi, 0, 0, w, h, null);
		g2.setColor(Color.BLACK);
		for(int i=0;i<size;++i)
		{
			if(x1[i]!=0)
			{
				g2.drawLine(x1[i],0,x1[i],h);
			}	
		}
		
		for(int i=0;i<size;++i)
		{
			if(y1[i]!=0)
			{
				g2.drawLine(0,y1[i],w,y1[i]);
			}	
		}
		
		g2.dispose();
		
		//setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("1.jpg")))));
		setContentPane(new JLabel(new ImageIcon(tmp)));	
		
		setAlwaysOnTop(true);
		setVisible(true);
		for(int i=0;i<size;++i)
		{
			if(x1[i]!=0 || y1[i]!=0)
			System.out.println("x1["+i+"] = " + x1[i] + "\tx2["+i+"] = " + x2[i] + 
							   "\ty1["+i+"] = " + y1[i] + "\ty2["+i+"] = " + y2[i]);
		}
		
	}	
	public void pixelARGB(int pixel) 
	{
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		a=alpha; r=red; g=green; b=blue;
  	}
}