/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macros;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.jnativehook.GlobalScreen;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author zick
 */
public  class IOKeyListener   {
    
   
    public  IOKeyListener() {}
//    
//        // Clear previous logging configurations.
//        // Get the logger for "org.jnativehook" and set the level to warning.
//        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//        logger.setLevel(Level.WARNING);
//
//        // Don't forget to disable the parent handlers.
//        logger.setUseParentHandlers(false);
//    };
//       
//        
//    @Override
//   public void nativeKeyPressed(NativeKeyEvent e) {
//		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//
//		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//			try {
//				GlobalScreen.unregisterNativeHook();
//			} catch (NativeHookException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//	public void nativeKeyReleased(NativeKeyEvent e) {
//		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//	}
//
//	public void nativeKeyTyped(NativeKeyEvent e) {
//		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//	}
//
//	public static void main(String[] args) {
//		try {
//			GlobalScreen.registerNativeHook();
//	}
//		catch (NativeHookException ex) {
//			System.err.println("There was a problem registering the native hook.");
//			System.err.println(ex.getMessage());
//
//			System.exit(1);
//		}
//                System.out.println("What sup");
//		GlobalScreen.addNativeKeyListener(new IOKeyListener());
//	}
////   
    
}
