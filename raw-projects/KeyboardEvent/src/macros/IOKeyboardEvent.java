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
   
   
   /**
    *  Output text using virtual keys by given text
    * @param text Text keyChar with keyboard pressing event.
    * @param delaymlseconds Set time delay for each character that prints.
    */
   public  void virtualKeyPress (String text, int delaymlseconds) {

         
          for (int substring = 0; substring < text.length(); substring++) {
          
              // get single character at substring position and convert that into character
              char subtext = text.charAt(substring);
              String subtextStr = text.substring(substring, substring+1);
              
              int ascii_subtext = (int) subtext;
              
              int keyChar = isKeyExist(subtext);
              boolean isShift = getShift(keyChar);
              
        
//          
     
             
        if (keyChar > -1 && isShift) // if case somewhere elevated in key where exist from simple icode
        {
            
//            System.out.println("shift: "+subtext+", "+getValue(keyChar));

            
           robot.keyPress(KeyEvent.VK_SHIFT); 
                robot.keyPress(getValue(keyChar));
                robot.keyRelease(getValue(keyChar));
           robot.keyRelease(KeyEvent.VK_SHIFT);
              
          }
         // Compare if lower Case
       
        
        else if (isShift == false && !subtextStr.equals(" ") && keyChar > -1   ) { // ignore space key
//            System.out.println("NO shift: "+subtext+", "+getValue(keyChar));
                 robot.keyPress(getValue(keyChar));
                 robot.keyRelease(getValue(keyChar));
        }
        else {
          // fix offset to lower case
          if (ascii_subtext > 96 && ascii_subtext < 123) ascii_subtext = ascii_subtext - 32;   
                
//          System.out.println("else: "+substring+" : "+subtext + " ==  "+ascii_subtext); 
        
              robot.keyPress( ascii_subtext  );
              robot.keyRelease( ascii_subtext  );
         
        }
        
        robot.delay(delaymlseconds);
        
       }
   }
   
   
   
   /**
    * Give keyChar from clipboard as [CTRL + V] option
    * @return String full of copied keyChar
    * @throws UnsupportedFlavorException
    * @throws IOException 
    */
   public String getControlPaste() throws UnsupportedFlavorException, IOException{
       
       
       
       Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
       String paste = (String) c.getContents(null).getTransferData(DataFlavor.stringFlavor);
   
       System.out.println("CTRL + V: control paste: "+paste);
       
       return paste;
   }
   
   /**
    * Output keyChar with key pressing event taking data from clipboard (Control + V or Control Paste).
    * @param delaymlseconds Set time delay for each character that prints.
    * @throws UnsupportedFlavorException
    * @throws IOException 
    */
   public void virtualKeyPressControlPaste (int delaymlseconds) throws UnsupportedFlavorException, IOException {
   
       String text = getControlPaste();
       virtualKeyPress(text, delaymlseconds);
       
   
   }
   
   /**
    * Output keyChar with key pressing event taking data from clipboard (Control + V or Control Paste).
    * @param delaymlseconds Set time delay for each character that prints.
    * @param  maxCharactersPasteLenght Set maximum length of characters from clipboard (Control + V or Control Paste).
    * @throws UnsupportedFlavorException
    * @throws IOException 
    */
   public void virtualKeyPressControlPaste (int delaymlseconds, int maxCharactersPasteLenght) throws UnsupportedFlavorException, IOException {
   
       String text = getControlPaste();
             text = text.substring(0, (maxCharactersPasteLenght<=text.length()?maxCharactersPasteLenght:text.length())); // set maximum range that acual exist
       virtualKeyPress(text, delaymlseconds);
       
   
   }
   /**
    * Set delay routine to threads.
    * @param miliseconds Delay of element.
    */
   public void delay(int miliseconds) {
   
       robot.delay(miliseconds);
   }
    
}
