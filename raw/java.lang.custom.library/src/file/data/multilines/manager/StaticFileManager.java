/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.data.multilines.manager;


//import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Vector;



/**
 *
 * @author zick
 */
public class StaticFileManager {
    
    private String filename;
    private String separator;
    private File fileSystem; // file check
    private FileWriter outputStream; 
    private BufferedWriter bufferWrite;
    private Vector<String> dataStringHolder = new Vector<String>(); // store new data in string format
    
    
    
    // Constructor 
 public   StaticFileManager (String filename){
       this.filename = filename;
       fileSystem = new File(filename);
       separator = "~"; // sentence point separator 
       
       
    }
    
public void writeText (String text) {
        try {
             outputStream = new FileWriter(filename);                      
  

             outputStream.write(text);
             outputStream.close();
             
        }catch (Exception e) {
            System.out.println("Error with PrintWriter:"+filename);
        }
};

public void writeTextAppend (String text) {
     
//      BufferedWriter bw = null;
     
       try {
//             bufferWrite = new   BufferedWriter(new FileWriter(new File(filename),true));// 

             outputStream = new FileWriter(filename,true);                      
             outputStream.write(text+"\n");
             outputStream.close();
             
        }catch (Exception e) {
            System.out.println("Error with BufferedWriter:"+filename);
        }
};

 public boolean fileExist() {
     if (fileSystem.exists())
         System.out.println("File '"+filename+"' exist.");
     else
         System.out.println("Unable to find file '"+filename+"' existence.");
     return fileSystem.exists();
 }
 
    
 
 
 
//   public Vector<String> gestLineVetor (int line) {
//    
//       return dataStringHolder;
//   };
  
  
   public String getLineVector (int line) {
    
       if (dataStringHolder.size() > line)
           return dataStringHolder.get(line);
        else
           return "-1";
   };
 
   public void readLineIn() {

                try(BufferedReader br = new BufferedReader(new FileReader(filename))) 
                {    
                    int lines = 0;
                    dataStringHolder.clear(); // clear that old data
                    
                    for(String line; (line = br.readLine()) != null; ) {
//                         System.out.println(line+"\n");
                            
                                                 
                         dataStringHolder.add(line); // store new data
                     }
                     
                     lines++;
                }        
             catch (Exception e)
                     {
                          System.out.println("ERROR_ReadLineMethod");
                     }
             // line is not visible here.
     }

   
   // Rezolve string that get from file into separate
   public String  resolveDataStructure () {
            int line=0;
            
            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            String SeparatedText = "";
            
            if (dataStringHolder.size() > 0 ) // 0 is equal that no text in file 
            {
                    
               
                    
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                        
                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                 SeparatedText = dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd); // only job is to get text from bouth separators
                                 ++textIndex; // add text was counted
                            }
                             System.out.println("Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" text index counted: "+textIndex+ ", output:["+SeparatedText+"]");
                             
                            
                            
                             
                    }
            }
        return SeparatedText;
   }
   
  
// Directly get single, separated text from selected line
public String  getDataFromLineIndex (int line, int index) { 
//          
            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            String SeparatedText = "";
            
            if (dataStringHolder.size() > 0 ) // 0 is equal that no text in file 
            {
                    
               
                    
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                        System.out.println("Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" text index counted: "+(textIndex+1)+ ", output:["+dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd)+"]");
                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                  if (textIndex == index){ // only save text when needet
                                       SeparatedText = dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd); // only job is to get text from bouth separators
                                       break; // break if find what user want. 
                                  }
                                 ++textIndex; // last text was counted
                            }
                            
                         
                                
                           
                             
                            
                            
                             
                    }
            }
        return SeparatedText;
   }





























}

