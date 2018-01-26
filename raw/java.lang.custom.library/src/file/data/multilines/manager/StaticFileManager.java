/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.data.multilines.manager;


//import java.io.*;
import java.io.File;
//import java.util.*;// Formatter
import java.util.Formatter;

/**
 *
 * @author zick
 */
public class StaticFileManager {
    
    String filename;
    // Constructor 
 public   StaticFileManager (String filename){
       this.filename = filename;
    }
    
   public void createFile() {
//               createFile("datatext.txt"); // default name
      
       
       final Formatter formFile;
       File fileRoot = new File(filename);
            if (!fileRoot.exists()) 
                    try{
                        formFile = new Formatter(filename);
                        System.out.println("File '"+filename+"' was created successfully.");
                    }
                    catch (Exception e) {
                        System.out.println("Got error while creating '"+filename+" file.");
                    }
            else 
                System.out.println("File "+filename+" was created before.");
        
   }
   
   
   public void eraseFileContent() {
//               createFile("datatext.txt"); // default name
      
       
       final Formatter formFile;
       
            
                    try{
                        formFile = new Formatter(filename);
                        System.out.println("File '"+filename+"' was erased all content successfully.");
                    }
                    catch (Exception e) {
                        System.out.println("Got error while erasing '"+filename+" file.");
                    }
           
        
   }
   
}
