/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macros;


import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author zilvinus.peciulis@gmail.com
 */



public class IOMouseEvent {
    
    public IOMouseEvent () {
                  
    }
    
    
     public static void leftClick (Robot robot) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
     
      public static void doubleLeftClick (Robot robot) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    
}
