# Screen-Saver
A desktop screen saver that uses current desktop icons as floating objects.<br/>
<br/>
About:<br/>
This a desktop java application, that uses java.awt.Window and Jpanels to create an interactive Screen Saver.
The screen saver background is the users' current desktop background and the (floating) icons are the icons on users' desktop. The program does the following steps in Sequence:<br/>
1.Get current wallpaper path<br/>
2.Create a new plain white image for background<br/>
3.Set the new plain image as desktop wallpaper<br/>
4.Take a screenshot of the desktop and save it.<br/>
5.Restore the desktop wallpaper.(Using java-native-acces library https://github.com/java-native-access/jna)<br/>
6.Process the screen shot to identify desktop icons.<br/>
7.add icons to the java.awt.window as jpanels.<br/>
