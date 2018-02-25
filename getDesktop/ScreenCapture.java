package getDesktop;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import java.net.*;
//import java.applet.*;
 
import javax.imageio.ImageIO;
 
public class ScreenCapture 
{
	
	public String imageName;
	public int imageWidth,imageHeight; 
	public void captureScreen() throws Exception 
	{
 
        /**
         * This class (Robot.java) is used to generate native system input events for the
         * purposes of test automation, self-running demos, and other
         * applications where control of the mouse and keyboard is needed.
         * The primary purpose of Robot is to facilitate automated testing
         * of Java platform implementations.
         */
        Robot robot = new Robot();
         
        /**
         * Get the current screen dimensions.
         */
		 
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        imageWidth = (int) d.getWidth();
        imageHeight = (int) d.getHeight();
         
         
        /**
         * delay the robot by 1s and switch to desktop
         */
		
        robot.delay(1000);
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.delay(300);
		
		/*
		Select all icons on desktop
		
		*/
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(200);
        
        /**
         * Create a screen capture of the active window and then create a buffered image
         * to be saved to disk.
         */
        Image image = robot.createScreenCapture(new Rectangle(0, 0, imageWidth, imageHeight));
 
        BufferedImage bi = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
 
        /**
         * Filename where to save the file to.
         * I am appending formatted timestamp to the filename.
         */
		
		
		String fileNameToSaveTo = "" + createTimeStampStr() + ".PNG";		
		
		/*
		Retrun to window
		*/
		robot.delay(100);
		//robot.mouseMove(imageWidth/2,1);
		robot.mousePress(MouseEvent.BUTTON1_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_MASK);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		
        /**
         * Write the captured image to a file.
         * I am using PNG format. You can choose PNG, JPG, GIF.
         */
        
		//writeImage(bi, fileNameToSaveTo, "PNG");
		//imageName=fileNameToSaveTo;
		imageName="" + "screenshot.PNG";
		writeImage(bi, imageName, "PNG");
 		
		
        System.out.println("Screen Captured Successfully and Saved to:\n"+imageName);
 
    }
 
    /**
     * This method writes a buffered image to a file
     *
     * @param img -- > BufferedImage
     * @param fileLocation --> e.g. "C:/testImage.jpg"
     * @param extension --> e.g. "jpg","gif","png"
     */
    public static void writeImage(BufferedImage img, String fileLocation, String extension) 
	{
        try 
		{
            BufferedImage bi = img;
			System.out.println("File location : " + fileLocation );
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
    }
 
    /**
     *
     * @return String representation of timestamp
     * in the format of yyyyMMdd_hhmmss (e.g. 20100426_111612)
     * @throws Exception
     */
    public static String createTimeStampStr() throws Exception 
	{
        Calendar mycalendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String timeStamp = formatter.format(mycalendar.getTime());
 
        return timeStamp;
    }
}