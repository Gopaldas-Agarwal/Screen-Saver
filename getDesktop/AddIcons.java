package getDesktop;

//for class BufferedImage
import java.awt.image.*;
//for class JPanel
import javax.swing.*; 
//for class Graphics
import java.awt.*;
//(new Random()).nextInt(n)
import java.util.Random;

public class AddIcons
{
	BufferedImage bi;
	int size=120;
	int[] x;
	int[] y;
	int a,r,g,b;
	int width,height;
	int R,G,B;
	int cnt;
	ImagePanel[] ip=new ImagePanel[50];
	JPanel glass;
	
	public AddIcons(SetDesktop sd,BufferedImage bi,int[] x,int [] y,int R,int G,int B)	
	{
		cnt=0;
		this.x=x;
		this.y=y;
		height=bi.getHeight(); 
		width=bi.getWidth();
		copyImage(bi);
		this.R=R;
		this.G=G;
		this.B=B;
		glass = (JPanel)sd.getGlassPane();
		glass.setVisible(true);
		glass.setLayout(null);
		//start();
		createImagePanels();
		
		
	}
	
	public void createImagePanels()
	{
		
		for(int i=0 ; i < size; ++i)
		{
			if(x[i]!=0)
			for(int j=0 ; j < size ; ++j)
			{
				if(y[i]!=0)
				{
					int pixel = bi.getRGB(x[i]+5, y[j]+5);
					pixelARGB(pixel);
					if(!(r==255 && g==255 && b==255))
					{
						int w,h;
						w=x[i+1]-x[i];
						h=y[j+1]-y[j];
						ip[cnt]=new ImagePanel(bi.getSubimage(x[i],y[j], w, h),glass,width,height);
						
						ip[cnt].setSize(w,h);
						cnt++;
						if(cnt%5==0)
						{
							try
							{
								//add icons to the JWindow with a delay of 100ms 
								Thread.sleep(2000);	
							}	
							catch(Exception e)
							{
								e.printStackTrace();	
							}
						}
						System.out.println(cnt +" w:" + w + " h:" + h);
						//ip[cnt].setSize(x[i+1]-x[i], y[i+1]-y[i]);
						//if(h>20)
						
					}
				}
			}
		}
	}
	
	/*
	
	public void run()
	{
		
		while(true)
		{
			int i=0;
			for(i=0;i<cnt;++i)
			{
				try
				{
					//glass.remove(ip[i]);
					ip[i].dx=0;
					//ip[i].dx=(new Random()).nextInt(5);
					ip[i].dy=(2 + (new Random()).nextInt() % 3);
					if(!(ip[i].y<0))
						ip[i].y= ip[i].y - ip[i].dy;
					else
						System.exit(0);
					//ip[i].setLocation( ip[i].x,ip[i].y - ip[i].dy);
					//ip[i].setLocation(200,200);
					ip[i].setLocation(x,y);
					ip[i].setVisible(true);
					glass.add(ip[i]);
					//Thread.sleep(20);
					//System.out.println(i + " x:" + ip[i].x + " y[i]:" + ip[i].y);
				}
				catch(Exception e)
				{
					e.printStackTrace();		
				}
				
			}
			try
			{
				Thread.sleep(50);
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
		}
		
	}
	
	*/
	
	public void copyImage(BufferedImage source)
	{
		this.bi = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = bi.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
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