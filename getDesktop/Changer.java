package getDesktop;

import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

public class Changer 
{
   public static void changeWallpaper(String path) 
   {
      //supply your own path instead of using this one
      //String path = "newWallpaper.png";

      SPI.INSTANCE.SystemParametersInfo(
          new UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
          new UINT_PTR(0), 
          path, 
          new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
   }

   public interface SPI extends StdCallLibrary 
   {

      //from MSDN article
      long SPI_SETDESKWALLPAPER = 20;
      long SPIF_UPDATEINIFILE = 0x01;
      long SPIF_SENDWININICHANGE = 0x02;

      SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
         {
            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
         }
      });

      boolean SystemParametersInfo(
          UINT_PTR uiAction,
          UINT_PTR uiParam,
          String pvParam,
          UINT_PTR fWinIni
        );
  }
}















/*
import java.util.*;

public class Changer
{
    //public static native int SystemParametersInfo(int uiAction,int uiParam,String pvParam,int fWinIni);
	public static extern boolean SystemParametersInfo(Int32 uiAction, Int32 uiParam, String pvParam, UInt32 fWinIni);
	//const uint SPI_SETDESKWALLPAPER = 20;
    //const int SPIF_UPDATEINIFILE = 0x01;
    //const int SPIF_SENDWININICHANGE = 0x02;
	
	private static Int32 SPI_SETDESKWALLPAPER = 20;
    private static UInt32 SPIF_UPDATEINIFILE = 0x01;
	private static UInt32 SPIF_SENDWININICHANGE = 0x02;
	static
    {
		// DLL Import
    	//[DllImport("user32.dll", CharSet = CharSet.Auto)];
		//Runtime.getRuntime().loadLibrary("user32");
        System.loadLibrary("user32");
    }

    public boolean Change(String path)
    {
       //return SystemParametersInfo(20, 0, path, 0);
	   SystemParametersInfo(SPI_SETDESKWALLPAPER, 0, path , SPIF_UPDATEINIFILE | SPIF_SENDWININICHANGE);
    }

    public static void main(String args[])
    {
        String wallpaper_file = "F:\1.jpg";
		System.out.println("changing wallpaper");
        Changer mychanger = new Changer();
        mychanger.Change(wallpaper_file);
    }

}
*/