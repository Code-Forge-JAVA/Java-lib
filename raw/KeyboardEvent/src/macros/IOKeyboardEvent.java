/*
 *javadoc https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html
 *        https://www.java-tips.org/java-se-tips-100019/21-java-awt/1758-how-to-use-robot-class-in-java.html
 */
package macros;






import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.awt.event.InputEvent;



// Get Clipboard data from  CONTROL + COPY
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;




/**
 *
 * @author zilvinus.peciulis@gmail.com
 */
public class IOKeyboardEvent extends IOKeyMap{
   Robot robot;
    
   public IOKeyboardEvent () {
    
     
       try {
            robot = new Robot();
             
        } catch (AWTException e) {
            e.printStackTrace();
        }


    }
   
   public void test () {
       System.out.println("Test Complete");
   }
   
   public  void virtualKeyPress (String text, int delaymlseconds) {

         
          for (int substring = 0; substring < text.length(); substring++) {
          
              // get single character at substring position and convert that into character
              char subtext = text.charAt(substring);
              String subtextStr = text.substring(substring, substring+1);
              
              int ascii_subtext = (int) subtext;
              
              int shift = isKeyExist(subtext);
              
        
//          
     
             
        if (shift > -1) // if case somewhere elevated in key where exist from simple icode
        {
            
            
          
            
            System.out.println("shift: "+subtext+", "+getValue(shift));

            
           robot.keyPress(KeyEvent.VK_SHIFT); 
                robot.keyPress(getValue(shift));
                robot.keyRelease(getValue(shift));
           robot.keyRelease(KeyEvent.VK_SHIFT);
              
          }
        
        else {
          // fix offset to lower case
          if (ascii_subtext > 96 && ascii_subtext < 123) ascii_subtext = ascii_subtext - 32;   
                
          System.out.println("else: "+substring+" : "+subtext + " ==  "+ascii_subtext); 
        
              robot.keyPress( ascii_subtext  );
              robot.keyRelease( ascii_subtext  );
         
        }
        
        robot.delay(delaymlseconds);
        
       }
   }
   
   
   
   /**
    * Give value from cliboard as [CTRL + V] option
    * @return String full of copied value
    * @throws UnsupportedFlavorException
    * @throws IOException 
    */
   public String getControlPaste() throws UnsupportedFlavorException, IOException{
       
       
       
       Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
       String paste = (String) c.getContents(null).getTransferData(DataFlavor.stringFlavor);
   
       System.out.println("CTRL + V: control paste: "+paste);
       
       return paste;
   }
   
   
   
   public void delay(int miliseconds) {
   
       robot.delay(miliseconds);
   }
    
}
