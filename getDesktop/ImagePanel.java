package getDesktop;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Random;

public class ImagePanel extends JPanel implements Runnable
{

	private BufferedImage image;
	Thread t;
	JPanel glass;
	int x,y,dx,dy,height,width;
	boolean state;

    public ImagePanel(BufferedImage image,JPanel glass,int w,int h) 
	{
		addMouseListener(new MyMouseAdapter(this));
		/*
		addMouseListener(new MouseAdapter() 
		{
    		@Override
    		public void mouseClicked(MouseEvent e) {
        	System.out.println("Hello");
    	}
		});
		*/
		this.image=image; 
		this.glass=glass;
		state=true;
		width=w;
		height=h;
		setVisible(true);
		x=((new Random()).nextInt(10000)+10000)%(width-50);
		y=height-50;
		dy=(2 + (new Random()).nextInt(2) % 3);
		glass.add(this);
		t=new Thread(this);   
		t.start();
    }

    protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);          
    }
	
	public void run()
	{
		
		while(state)
		{
				try
				{	
					dx=0;
					//ip[i].dx=(new Random()).nextInt(5);
					//dy=(2 + (new Random()).nextInt() % 3);
					if(y>20)
					{
						y= y - dy;
						setLocation(x,y);
						//glass.add(this);
					}
					else
					{
						setVisible(false);
						glass.remove(this);
						this.setSize(0,0);
						System.out.println("Removed");
						validate();
						repaint();
						state=false;
						//System.exit(0);
					}
					//ip[i].setLocation( ip[i].x,ip[i].y - ip[i].dy);
					//ip[i].setLocation(200,200);
					
					Thread.sleep(40);
					//System.out.println(i + " x:" + ip[i].x + " y[i]:" + ip[i].y);
				}
				catch(Exception e)
				{
					e.printStackTrace();		
				}
		}
		
	}

}


class MyMouseAdapter extends MouseAdapter 
{
	ImagePanel ip;
	public MyMouseAdapter(ImagePanel ip) 
	{
		this.ip = ip;
	}
	// Handle mouse clicked.
	public void mouseClicked(MouseEvent me) 
	{
		//System.out.println("Mouse Clicked : " + me.getSource().toString()+ me.getX() );
		ip.setVisible(false);
		ip.glass.remove(ip);
		//ip.setSize(0,0);
		//System.out.println("Removed");
		//ip.validate();
		//ip.repaint();
		ip.state=false;
	}
}