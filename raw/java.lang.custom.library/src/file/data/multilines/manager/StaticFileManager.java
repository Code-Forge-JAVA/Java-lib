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
import java.io.IOException;
import java.util.ArrayList;

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
    // store new data in string format
    final private ArrayList<String> dataStringHolder;
    public ArrayList<ArrayList<String>> dataOut = new ArrayList<ArrayList<String>>();
    public ArrayList<String> dataIns = new ArrayList<>();  // 'dataIns'ide goes inside into 'dataOut'side and work like sub array
    
    
    
    // Constructor 
 public   StaticFileManager (String filename){
        this.dataStringHolder = new ArrayList<>();
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
   public void  resolveDataStructure () {
//            int line=0;
            
           
//            dataOut.add(dataIns); // add first list
            
             
//                  dataIns.clear();
//                  dataOut.clear();
                  
            

             
             for (int line=0; dataStringHolder.size() > line;line++ ) // 0 is equal that no text in file 
            {
                    
                    // if outside array is smaller then curently file data lines ammount then add new array block. Equlize  each RAR and EEPROM side of data. 
//                    if (dataStringHolder.size()> dataOut.size()){
                        dataOut.add(dataIns); //add new array structure
                        dataIns = new ArrayList<>(); // create a new inner list that has the same content as 
//                    }
                        System.out.println("size: "+dataOut.size()  );
                    
                    System.out.println("Break Line: "+line);
                     
                    //Declarate each time new variables and values
                        int separatorIndexStart =0;// rezoved begining of the text position    
                        int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
                        int textIndex = 0;  // count each index of text between separators
                        String SeparatedText = "";
                     
                     
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd)+"], text-index counted: "+(textIndex));
                                  
                                      dataIns.add( dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd) ); // only job is to get text from bouth separators
                                       
                                        ++textIndex; // last text was counted
                            }
                            
                       
                             
                    }
            }
             
             System.out.println("OuterArraiySize: "+dataOut.size());
             System.out.println("Output: "+dataOut);
   }
   
  public String getDataFromVectorLineIndex (int line, int index){
      
      
      if (dataStringHolder.size()> dataOut.size() | true) // minimum inspection that memory log doesn't not constructed yet
          resolveDataStructure ();
          
      
      System.out.println("Size of structure array: "+dataOut.size());
      System.out.println("Pointer size: "+dataOut.get(line).size() );
      
      
      if (dataOut.size() > line && dataOut.get(line).size() > index | true) // protect from outranged values from user input
          return dataOut.get(line).get(index);
      else
          return "-1";
  }
   
// Directly get single, separated text from selected line from RAM dataStringHolder
public String  getDataFromLineIndex (int line, int index) { 
//          
            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            String SeparatedText="";
            
            if (dataStringHolder.size() > 0 ) // 0 is equal that no text in file 
            {
                    
                    
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd)+"], text-index counted: "+(textIndex)+ ", user-index:"+index+",user-found = "+(textIndex == index ? "yes":"no"));
                                  
                                  if (textIndex == index){ // only save text when needet
                                       SeparatedText = dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd); // only job is to get text from bouth separators
                                        return SeparatedText; // break and return if find what user want. 
                                  }
                                 ++textIndex; // last text was counted
                            }
                            
                         
                                
                           
                             
                            
                            
                             
                    }
            }
        return SeparatedText;
   }


// get lenght only from RAM but not directly from file.txt , to do that need use updeter first.
public int getLineIndexLenght(int line) {

            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            
            
            if (dataStringHolder.size() > 0 ) // 0 is equal that no text in file 
            {
                 
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                                 ++textIndex; // last text was counted
                            }
                         
                             
                    }
            }

    System.out.println("function: getLineIndexLenght(int line):  line: "+line+", lenght:"+(textIndex == 0 ? "-1":textIndex) );        
       if (textIndex == 0) return -1; else return textIndex; // if fail to find separator, fix value from zero to minus one,else return finded indexes
}


 // get lenght only from RAM but not directly from file.txt , to do that need use updeter first.
public int getLinesLenght() {
    
     return dataStringHolder.size();
     
}






















}

