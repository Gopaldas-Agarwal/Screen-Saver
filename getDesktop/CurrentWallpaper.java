package getDesktop;

import java.io.*;
import javax.imageio.*;

public class CurrentWallpaper
{
		public String wallpaperPath;
		public String path;
		public void getWallpaperPath()
		{
			final String cmdCommand="REG QUERY \"HKCU\\Control Panel\\Desktop\" /v Wallpaper";
			
			String path="";
			//path=Runtime.getRuntime().exec(cmdCommand);	
			try
			{
				Process process = Runtime.getRuntime().exec(cmdCommand);
				StreamReader reader = new StreamReader(process.getInputStream());				
				reader.start();
				process.waitFor();
				reader.join();
			
				path = reader.getResult();
				System.out.println("Path : " + path);
				//System.out.println("Path : " + path + " " + (path.indexOf(":\\")-1));
				//System.out.println(path  +" "+  	path.length());
				
				String temp=path.substring(path.indexOf(".")+1,path.length());
				int i;
				for(i=0;i<temp.length();++i)
				{
					if(!Character.isLetter(temp.charAt(i)))
					{
						break;
					}	
				}
				++i;
				System.out.println("i: " + i);
				
				path=path.substring(path.indexOf(":\\")-1,path.indexOf(".") + i);
			
				//System.out.println("Path : " + path);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//System.out.println("Current wallpaper location:\n" + path);
			wallpaperPath=path;
		}
		
		static class StreamReader extends Thread 
		{
			private InputStream is;
			private StringWriter sw;
		
			StreamReader(InputStream is)
			{
				this.is = is;
			  	sw = new StringWriter();
			}
		
			public void run() 
			{
				try 
				{
					int c;
					while ((c = is.read()) != -1)
					  sw.write(c);
				}
			catch (IOException e) { ; }
		}
		
		String getResult() 
		{
			  return sw.toString();
		}
	}
}