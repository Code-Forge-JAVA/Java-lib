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
import java.util.Iterator;

import java.util.Vector;




/**
 *Local file manipulation as data storage in  simple data.txt file.
 * 
 * @author Zilvinus Peciulis
 * @version 1.0 Build 8704 Jan 31 , 2018
 */
public class StaticFileManager {
    
    private String filename;
    private String separator;
    private File fileSystem; // file check
    private FileWriter outputStream; 
    private BufferedWriter bufferWrite;
    // store new data in string format
    final private Vector<String> dataStringHolder; 
   // Pattern example of dataStringHolder
    //1: John~Cruger~6849~   
    //2: Sethom~Colem~2597~ 
    //3: Rober~Salam~5764~   
    
//    public Vector<Vector<String>> dataOut = new Vector<Vector<String>>();
//    public Vector<String> dataIns = new Vector<>();  // 'dataIns'ide goes inside into 'dataOut'side and work like sub array
    
    
 /**
  * @param filename require to provide file name or full path of the file , also created a new file
  */
    
 public   StaticFileManager (String filename){
       this.dataStringHolder = new Vector<>();
       this.filename = filename;
       fileSystem = new File(filename);
       separator = "~"; // sentence point separator 
       
       
    }
    
 /**
  * 
  * @param text write text into file
  */
 
public void writeText (String text) {
        try {
             outputStream = new FileWriter(filename);                      
  

             outputStream.write(text);
             outputStream.close();
             
        }catch (Exception e) {
            System.out.println("Error with PrintWriter:"+filename);
        }
};


public void setLineRow (String text,  int line , int index){
    
    String combinedData=""; // buffer data~data2~
    
    if (dataStringHolder.isEmpty())
        {
                readInFileUpdater();
        }    
    
        Vector<String> dataVector = new Vector<>(); 
                   int dataVectorLenght =getLineRowComponentsLenght(line);
                      dataVector =  getLineVector(line);
                      
    
        if (dataStringHolder.size() > line) // combine in existing data
        {
            
            System.err.println("Line is okey");
                    for (int column = 0; column < (Integer.max(dataVectorLenght, index)); column++) // Generate separators and inject new data string into vector
                             {
                                    if (index == column) // Set new Value
                                    {
                                        combinedData+=text+separator;

                                        System.out.println("cath:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= dataVector.get(column)+separator;
                                          else
                                              combinedData+=separator;
                                        System.out.println("separator:"+combinedData);
                                    }
                             }
                        
        }else // if no data that exist and can be replased then generate empty lines until reach wanted line and set new data containing constructed string into it.
            {
                   
                System.err.println("Size: "+dataStringHolder.size()); 
                    for (int column = dataStringHolder.size()  ; column  <= line; column++) // generate emty string in this block
                        {
                              System.out.println("Generate: "+column);
                              dataStringHolder.add(""); // push emty string in while not right spot.
                              
                        }
                            //-------------//
                            
                                System.out.println("Set new data ");
                             
                             for (int column = 0; column < (Integer.max(dataVectorLenght, index)); column++) // Generate separators and inject new data string into vector
                             {
                                    if (index == column) // Set new Value
                                    {
                                        combinedData+=text+separator;

                                        System.out.println("cath:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= dataVector.get(column)+separator;
                                          else
                                              combinedData+=separator;
                                        System.out.println("separator:"+combinedData);
                                    }
                             }
            }
       
             dataStringHolder.set(line, combinedData); // replace constructed string into data storing vector
             System.out.println("Rezult "+dataStringHolder.get(line));
                      
//
////                     dataStringHolder.remove(0);
//                     dataStringHolder.set(0, "AHAHAHAHAHHAH");
//                     
//                     dataStringHolder.add("GOLVE JOKER");
//                     System.out.println("Remove initiated:----------------");
//                    for (int i = 0; i < dataStringHolder.size(); i++) 
//                        {
//                                System.out.println("Line: "+i+ " "+dataStringHolder.get(i));
//                        }  
                     
//    dataStringHolder.add("param1");
//    dataStringHolder.add("param2");
//    dataStringHolder.add("param3");
//     System.out.println("setLineRow: "+dataStringHolder);
//     dataStringHolder.add(0, "ButterFly");
//     System.out.println("setLineRow: "+dataStringHolder);
//     
//     System.err.println("custom : "+dataStringHolder.get(line));
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

 public boolean isFileExist() {
     if (fileSystem.exists())
         System.out.println("File '"+filename+"' exist.");
     else
         System.out.println("Unable to find file '"+filename+"' existence.");
     
     return fileSystem.exists();
 }
 
    
 
 
 /**
  *Main and only updater that job is to get all data from file and store into dataStringHolder vector to use as a buffer in this class
  * @since  //Store and update data in dataStringHolder vector
  */
   public void readInFileUpdater() {

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
                          System.err.println("readLineIn() ERROR_ReadLineMethod");
                     }
             // line is not visible here.
     }


   
   
   // return whole block from the raw line into separated units to use later
   /**
    * By the given line , detach data from the dataHolder(line) raw string and separate sub-data vector points. 
    * @param line Specify column from the data vector.
    * @return Contain vector with separated data.
    */
   public Vector getLineVector(int line) {
   
      int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            Vector<String> SeparatedTextVector = new Vector();
            
            if (dataStringHolder.size() > line ) // 0 is equal that no text in file 
            {
                    
                 
                  
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
//                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd)+"], text-index counted: "+(textIndex));
                                  
                                       SeparatedTextVector.add ( dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd) ); // only job is to get text from bouth separators
                                 ++textIndex; // last text was counted
                            }
                            
                             
                    }
            }else {
                 System.err.println("getLineVector line out of range ");
    //              throw new ArrayIndexOutOfBoundsException(line);
                 } 
            
        return SeparatedTextVector;
       
       
   }
   
   /**
    * By the given line , detach data from the dataHolder(line) raw string and separate sub-data vector points by given range. 
    * @param line Specify column from the data vector.
    * @param start Where start to catch data.
    * @param end   End of catched data.
    * @return  Provide vector with separated sub-data that was in specified range 'start->end' to do that.
    */
 public Vector getLineVectorSpecifiedSize(int line, int start , int end) {
   
      int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            Vector<String> SeparatedTextVector = new Vector();
            
            if (dataStringHolder.size() > line ) // 0 is equal that no text in file 
            {
                    
                    
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
//                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd)+"], text-index counted: "+(textIndex));
                                     if (start <= textIndex & textIndex < end ) // cath in data in specified range
                                     {
                                        SeparatedTextVector.add ( dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd) ); // only job is to get text from bouth separators
                                     }
                                   ++textIndex; // last text was counted
                            }
                            
                             
                    }
            }else {
                 System.err.println("getLineVectorSpecifiedSize line out of range ");
                //  throw new ArrayIndexOutOfBoundsException(line);
                 } 
            
        return SeparatedTextVector;
       
       
   }   

/**
 * By the given line , detach data from the dataHolder(line) raw string and separate into single string of sub-data. 
 * @param line Specify column from the data vector.
 * @param index Specify position in column row.
 * @return Separated single string of sub-data.
 */   
public String  getDataFromLineIndex (int line, int index) { 
//         
         
         
            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            String SeparatedText="";
            
            if (dataStringHolder.size() > line ) // 0 is equal that no text in file 
            {
                    
                    System.err.println("Raw data: "+dataStringHolder.get(line));
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd)+"], text-index counted: "+(textIndex)+ ", user-index:"+index+",user-found = "+(textIndex == index ? "yes":"no"));
                                  
                                  if (textIndex == index){ // only save text when needet
                                       SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd); // only job is to get text from bouth separators
                                        System.err.println("rezult data: "+SeparatedText);
                                        return SeparatedText; // break and return if find what user want. 
                                  }
                                 ++textIndex; // last text was counted
                            }
                            
                           
                    }
            } else {
                System.err.println("getDataFromLineIndex line out of range");   
            //     throw new ArrayIndexOutOfBoundsException(line);    
            }
            
                   
           
        return SeparatedText;
   }



/**
 * In specified column , count each sub-data points.
 * @param line Given argument are use to specific line. 
 * @return Contains length each sub-data points. If wail return -1
 */
public int getLineRowComponentsLenght(int line) {

            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            
            
            if (dataStringHolder.size() > line ) // 0 is equal that no text in file 
            {
                 
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                                 ++textIndex; // last text was counted
                            }
                         
                             
                    }
            }else {
                System.err.println("getLineIndexLenght line out of range");
//                 throw new ArrayIndexOutOfBoundsException(line);    
            }

       if (textIndex == 0) return -1; else return textIndex; // if fail to find separator, fix value from zero to minus one,else return finded indexes
}


 // get lenght only from RAM but not directly from file.txt , to do that need use updeter first.
/**
 * Get total lines stored in data as length.
 * @return  Provide total amount of the column
 */
public int getLinesLenght() {
    
     return dataStringHolder.size();
     
}


public void displayDataMap() 
{
    for (int line = 0; line < dataStringHolder.size(); line++) {
        System.out.println(line+" "+dataStringHolder.get(line) );
    }
}





















}

