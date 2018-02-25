import getDesktop.*;
import java.net.*;
import java.nio.file.*;

class DesktopMain
{
	public static void main(String... args) throws Exception
	{
		//get the path to the current desktop wallppaper
		CurrentWallpaper cw=new CurrentWallpaper();
		cw.getWallpaperPath();
		System.out.println("CW : " + cw.wallpaperPath);
		
		//create a plain white image to be set as the new backgroung
		int r,g,b;
		r=255;
		g=255;
		b=255;
		CreateImage ci=new CreateImage(r,g,b);
		ci.generateImage();
		
		//change the wallpapert to the generated image
		Changer change=new Changer();
		System.out.println("CI.imageName="+ci.imageName);
		change.changeWallpaper(ci.imageName);
		
		//get a screenshot of the desktop screen
		ScreenCapture sc=new ScreenCapture();
		try
		{
			sc.captureScreen();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		//set the original wallpaper back.
		change.changeWallpaper(cw.wallpaperPath);
		
		//process the captured screenshot to identify the icons
		ProcessImage pi=new ProcessImage(sc.imageName,r,g,b);
		
		/*
		set the desktopwallpaper as JWindow Background 
		display the JWindow
		*/
		SetDesktop sd=new SetDesktop(cw.wallpaperPath);
		
		/*
		add the icons to the JWindow
		*/
		AddIcons ai=new AddIcons(sd , pi.bi , pi.x1 , pi.y1 , r , g , b);
	
	}	
}`	