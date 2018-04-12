/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package globalkeylistenerexample;




import java.util.logging.Level;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.logging.Logger;

public class GlobalKeyListenerExample implements NativeKeyListener {
    
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
	}
        
       // Where capture keys 
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                
                if (NativeKeyEvent.getKeyText(e.getKeyCode() ) == "F2"  )
                {
                    System.out.println("F2 Success");
                }
	}
// Where capture keys
	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                
                
	}

	public static void main(String[] args) throws NativeHookException {
		
        	GlobalScreen.registerNativeHook();
                    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                    logger.setLevel(Level.OFF); // to throw nothing in the console, other options is :Level.WARNING,Level.ALL 
                

		GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample()); // Reginser object to listener routine loop
                
	}
}