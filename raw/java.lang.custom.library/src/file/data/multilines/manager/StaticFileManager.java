/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/* Exsample How To Use It
//1)
StaticFileManager fileManages = new StaticFileManager("databases.txt"); // create or read from file
                fileManages.setRowCollumn(3, 1, "Hello","World"); //set new value in class buffer also suport vector and arrayList
                fileManages.uploadInFile(); // upload from class buffer into file
System.out.println("Rezult: "+ fileManages.getLineVectorSpecifiedSize(3, 0, 5) ); // get data array and display rezult.

note:
reupload into class buffer use: fileManages.readInFileUpdater();

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
import sun.security.util.Length;



//
///**
// *Local file manipulation as data storage in data.txt file.
// * 
// * @author Zilvinus Peciulis
// * @version 1.6.1 Build 8704 Jan 31 , 2018
// */


// import statements

/**
 * @author      Firstname <@ zilvinus.peciulis@gmail.com>
 
 */
public class StaticFileManager {
    
    private final String filename;
    private final static String separator ="~";//simbol of pointing separator 
     private final static String zeroseparator ="_";//zero point separator use if no value is in get(0) position.
    private final File fileSystem; // file check
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
  * @throws Throws about unable to create a file
  */
    
 public   StaticFileManager (String filename){
       this.dataStringHolder = new Vector<>();
       this.filename = filename;
       fileSystem = new File(filename); // Create new file by path
       
       try {
          if ( fileSystem.createNewFile());
           System.out.println("Clas StaticFileManager: File '"+filename+"' exist: "+fileSystem.getAbsolutePath());
       }catch (Exception e){
           System.out.println("Clas StaticFileManager: Unable to create file at: "+fileSystem.getAbsolutePath());
       }
      
       
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
                          System.err.println("Class: StaticFileManager, method: readInFileUpdater() ERROR_ReadLineMethod path:"+fileSystem.getAbsolutePath());
                     }

       readInFileUpdater();// Rist time when program starts , store data in data buffer.
       
       
    }
    

 /**
  * 
  * @param text write text into file
  */
 
private void writeText (String text) {
        try {
             outputStream = new FileWriter(filename);                      
  

             outputStream.write(text);
             outputStream.close();
             
        }catch (Exception e) {
            System.out.println("Error with PrintWriter:"+filename);
        }
};

/**
 * Set data in specific row and in specific column.
 * Must be use uploadInFile() method to take effect globally.
 * @param line Where row would be placed 
 * @param index Where column would be placed
 * @param text Put data.
 */
/*
public void setRowCollumn (  int line , int index,String text){
    
    String combinedData=""; // buffer data~data2~
    
        Vector<String> dataVector = new Vector<>(); 
        int dataVectorLenght =getLineColumnComponentsLenght(line);
           dataVector =  getLineVector(line);
                    
           //System.out.println("Vector: "+dataVector);   
                        
        if (dataStringHolder.size() > line) // combine in existing data
        {
            
//            System.err.println("Line is okey");
                    for (int column = 0; column < (Integer.max(dataVectorLenght, index+1)); column++) // if line is contains some data then try to not disturbe that until reach destination column
                             {
                                    
//                                   System.out.println("Data Lines: "+(index ) +", column:"+column+ ", index: "+index );  
                                    if (index == column) 
                                            // Set new Value
                                    {       // value that must be change ignoring old one.
                                               if (text.length() > 0)
                                                 combinedData+=text+separator;
                                               else
                                                 combinedData+="_"+separator;  
//                                            System.out.println("cath:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          if (dataVectorLenght != -1 & dataVectorLenght > column )
                                          { // not disturbe old data but copie.
                                              combinedData+= dataVector.get(column)+separator;
//                                              System.out.println("dataVector.get(column) column: "+column+ " value: "+ dataVector.get(column)+"m from: "+dataVector);
                                          }
                                             else
                                             // put in end last separator.
                                              combinedData+=separator;
//                                        System.out.println("separator:"+combinedData);
                                    }
                             }
                        
        }else // if no data  exist and can be replased then generate empty rows until reach wanted line and set new data containing constructed string into it.
            {
                           //-------Jump through lines-----//
//                System.out.println("Size: "+dataStringHolder.size()); 
                    for (int row = dataStringHolder.size()  ; row  <= line; row++) // jump through lines
                        {
//                              System.out.println("Generate: "+row);
                              dataStringHolder.add(zeroseparator+separator); // _~ push emty string in while not right spot.
                              
                        }
                            //-------Write In Collumn------//
                            
//                                System.out.println("Set new data ");
                             
                             for (int column = 0; column <= ( index); column++) // in column firstly generate separators then put new data
                             {
//                                  System.out.println("Jump Lines: "+ (index ) +", column:"+column+ ", index: "+index );   
                                 
                                    if (index == column) // Set new Value
                                    {
                                        combinedData+=text+separator;

                                        System.out.println("cath Empty:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= text+separator;
                                          else
                                              if (column == 0) // zero point in column
                                                  combinedData+="_"+separator; // if no spacer in zero spot , that can crash order of column
                                              else
                                                  combinedData+=separator; // everything after zero point
//                                        System.out.println("separator Empty:"+combinedData);
                                    }
                             }
            }
       
             dataStringHolder.set(line, combinedData); // replace constructed string into data storing vector
//             System.out.println("Rezult "+dataStringHolder.get(line));
                      

};
*/

/**
 * Set data in specific row and in specific column.
 * Must be use uploadInFile() method to take effect globally.
 * Can handle multiple strings of sub-data in same column.
 * @param line Where row would be placed 
 * @param index Where column would be start to be placed
 * @param multitext Put multiple data.
 */
public void setRowCollumn (  int line , int index ,String ...multitext){
    
    String combinedData=""; // buffer data~data2~
    
        Vector<String> dataVector = new Vector<>(); 
        int dataVectorLenght =getLineColumnComponentsLenght(line);
           dataVector =  getLineVector(line);
        int multtextcountPos = 0;
//           System.out.println("multtextcountPos Len : "+multtextcountPos);   
                        
        if (dataStringHolder.size() > line) // combine in existing data
        {
            
//            System.err.println("Line is okey");
                    for (int column = 0; column < (Integer.max(dataVectorLenght, (index + multtextcountPos)+1)); column++) // if line is contains some data then try to not disturbe that until reach destination column
                             {
                                    
//                                   System.out.println("Data Lines: "+(index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                  
                                    
            
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.length) ) // keep tracking multi text arguments where must be replaced 
                                            // Set new Value
                                    {       // value that must be change ignoring old one.
                                                    if (column ==0 && multitext[multtextcountPos].length() <= 0  ) // Protect from empty/void string
                                                        combinedData+=zeroseparator+separator;
                                                    else  // imput data 
                                                        combinedData+=multitext[multtextcountPos]+separator;
                                                        
                                                  multtextcountPos++;
//                                            System.out.println("TRUE cath:"+combinedData + ", multtextcountPos: "+multtextcountPos + ", column: "+column);
                                    }else // generate separators until new value
                                    {
                                        
  //*************************************************************************************************************  
                                         // in row and in 0 column position, set zeropoint 
                                         // Fix zeropoint problem when need
                                          if (column == 0 && (dataVector.size() == 0 || dataVector.get(0).length() == 0 ) ){
                                              combinedData+=zeroseparator; 
//                                              System.out.println("Set Zero Point at "+column+":");
                                          }
                                          
                                          
                                          
                                          if (dataVectorLenght != -1 & dataVectorLenght > column )
                                          { // not disturbe old data but copie.
                                              combinedData+= dataVector.get(column)+separator;
//                                              System.out.println("dataVector.get(column) column: "+column+ " value: "+ dataVector.get(column)+" from: "+dataVector);
                                          }
                                             else
                                             // put in end last separator.
                                              if ((index + multtextcountPos)  != column) {
                                                  combinedData+=separator;
//                                                  System.err.println(" Seperated "+column);
                                              }
                                          
                                           
//                                        System.out.println("separator:"+combinedData);
//                                         System.out.println("FALSE cath:"+combinedData + ", multtextcountPos: "+multtextcountPos + ", column: "+column);
                                    }
                             }
                        
        }else // if no data  exist and can be replased then generate empty rows until reach wanted line and set new data containing constructed string into it.
            {
                           //-------Jump through lines-----//
//                System.out.println("Size: "+dataStringHolder.size()); 
                    for (int row = dataStringHolder.size()  ; row  <= line; row++) // jump through lines
                        {
//                              System.out.println("Generate: "+row);
                              dataStringHolder.add(zeroseparator+separator); // _~ push emty string in while not right spot.
                              
                        }
                            //-------Write In Collumn------//
                            
//                                System.out.println("Set new data ");
                             
                             for (int column = 0; column <= (index + multtextcountPos) ; column++) // in column firstly generate separators then put new data
                             {
//                                   System.out.println("Jump Lines: "+ (index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.length) ) // Set new value and keep tracking multi text arguments where must be replaced 
                                    {
                                                if (multitext[multtextcountPos].length() <= 0 && index ==0 && multtextcountPos == 0) // Protect from empty/void string
                                                               combinedData+=zeroseparator+separator;
                                                 else  // imput data 
                                                               combinedData+=multitext[multtextcountPos]+separator;
                                         
                                         multtextcountPos++;;

//                                        System.out.println("cath Empty:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= " "+separator;
                                          else
                                              if (column == 0) // zero point in column
                                                  combinedData+=zeroseparator+separator; // if no spacer in zero spot , that can crash order of column
                                              else
                                                   if ((index + multtextcountPos)  != column) 
                                                       combinedData+=separator;
                                    }
                             }
            }
       
             dataStringHolder.set(line, combinedData); // replace constructed string into data storing vector
//             System.out.println("Rezult "+dataStringHolder.get(line));
                      

};

/**
 * Set data in specific row and in specific column.
 * Must be use uploadInFile() method to take effect globally.
 * Can handle multiple strings of sub-data one layer vector in same column.
 * @param line Where row would be placed 
 * @param index Where column would be start to be placed
 * @param multitext Put multiple data using vector.
 */
public void setRowCollumn (  int line , int index ,Vector<String>multitext){
    
    String combinedData=""; // buffer data~data2~
    
        Vector<String> dataVector = new Vector<>(); 
        int dataVectorLenght =getLineColumnComponentsLenght(line);
           dataVector =  getLineVector(line);
        int multtextcountPos = 0;
//           System.out.println("multtextcountPos Len : "+multtextcountPos);   
                        
        if (dataStringHolder.size() > line) // combine in existing data
        {
            
//            System.err.println("Line is okey");
                    for (int column = 0; column < (Integer.max(dataVectorLenght, (index + multtextcountPos)+1)); column++) // if line is contains some data then try to not disturbe that until reach destination column
                             {
                                    
//                                   System.out.println("Data Lines: "+(index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                    
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.size()) ) // keep tracking multi text arguments where must be replaced 
                                            // Set new Value
                                    {       // value that must be change ignoring old one.
                                             
                                                  
                                                 
                                                    if ( column == 0 && multitext.get(multtextcountPos).length() <= 0 ) // Protect from empty/void string
                                                          combinedData+=zeroseparator+separator;
                                                    else  // imput data 
                                                          combinedData+=multitext.get(multtextcountPos)+separator;
                                                            
//                                                    
                                                        
                                                  multtextcountPos++;
//                                            System.out.println("cath:"+combinedData + ", multtextcountPos: "+multtextcountPos);
                                    }else // generate separators until new value
                                    {
                                        
                                        
//*************************************************************************************************************  
                                         // in row and in 0 column position, set zeropoint 
                                         // Fix zeropoint problem when need
                                          if (column == 0 && (dataVector.size() == 0 || dataVector.get(0).length() == 0 ) ){
                                              combinedData+=zeroseparator; 
//                                              System.out.println("Set Zero Point at "+column+":");
                                          }
                                          
                                          
                                          
                                          if (dataVectorLenght != -1 & dataVectorLenght > column )
                                          { // not disturbe old data but copie.
                                              combinedData+= dataVector.get(column)+separator;
//                                              System.out.println("dataVector.get(column) column: "+column+ " value: "+ dataVector.get(column)+"m from: "+dataVector);
                                          }
                                             else
                                             // put in end last separator.
                                              if ((index + multtextcountPos)  != column) 
                                                  combinedData+=separator;
//                                        System.out.println("separator:"+combinedData);
                                    }
                             }
                        
        }else // if no data  exist and can be replased then generate empty rows until reach wanted line and set new data containing constructed string into it.
            {
                           //-------Jump through lines-----//
//                System.out.println("Size: "+dataStringHolder.size()); 
                    for (int row = dataStringHolder.size()  ; row  <= line; row++) // jump through lines
                        {
//                              System.out.println("Generate: "+row);
                              dataStringHolder.add(zeroseparator+separator); // _~ push emty string in while not right spot.
                              
                        }
                            //-------Write In Collumn------//
                            
//                                System.out.println("Set new data ");
                             
                             for (int column = 0; column <= (index + multtextcountPos) ; column++) // in column firstly generate separators then put new data
                             {
//                                   System.out.println("Jump Lines: "+ (index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.size()) ) // Set new value and keep tracking multi text arguments where must be replaced 
                                    {
                                         
                                        
                                         if ( multtextcountPos == 0 & multitext.get(multtextcountPos).length() <= 0 ) // Protect from empty/void string
                                                          combinedData+=zeroseparator+separator;
                                          else  // imput data 
                                                          combinedData+=multitext.get(multtextcountPos)+separator;
                                          
                                          
                                         multtextcountPos++;;

//                                        System.out.println("cath Empty:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= " "+separator;
                                          else
                                              if (column == 0) // zero point in column
                                                  combinedData+=zeroseparator+separator; // if no spacer in zero spot , that can crash order of column
                                              else
                                                   if ((index + multtextcountPos)  != column) 
                                                       combinedData+=separator;
                                    }
                             }
            }
       
             dataStringHolder.set(line, combinedData); // replace constructed string into data storing vector
//             System.out.println("Rezult "+dataStringHolder.get(line));
                      

};
/**
 * Set data in specific row and in specific column.
 * Must be use uploadInFile() method to take effect globally.
 * Can handle multiple string of sub-data one layer arraylist in same column.
 * @param line Where row would be placed 
 * @param index Where column would be start to be placed
 * @param multitext Put multiple data using arraylist.
 */
public void setRowCollumn (  int line , int index ,ArrayList<String>multitext){
    
    String combinedData=""; // buffer data~data2~
    
        Vector<String> dataVector = new Vector<>(); 
        int dataVectorLenght =getLineColumnComponentsLenght(line);
           dataVector =  getLineVector(line);
        int multtextcountPos = 0;
//           System.out.println("multtextcountPos Len : "+multtextcountPos);   
                        
        if (dataStringHolder.size() > line) // combine in existing data
        {
            
//            System.err.println("Line is okey");
                    for (int column = 0; column < (Integer.max(dataVectorLenght, (index + multtextcountPos)+1)); column++) // if line is contains some data then try to not disturbe that until reach destination column
                             {
                                    
//                                   System.out.println("Data Lines: "+(index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                    
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.size()) ) // keep tracking multi text arguments where must be replaced 
                                            // Set new Value
                                    {       // value that must be change ignoring old one.
                                             
                                              
                                                  
                                               if ( column == 0 & multitext.get(multtextcountPos).length() <= 0 ) // Protect from empty/void string
                                                          combinedData+=zeroseparator+separator;
                                                    else  // imput data 
                                                          combinedData+=multitext.get(multtextcountPos)+separator;
                                                
                                                  multtextcountPos++;
//                                            System.out.println("cath:"+combinedData + ", multtextcountPos: "+multtextcountPos);
                                    }else // generate separators until new value
                                    {
                                       
//*************************************************************************************************************  
                                         // in row and in 0 column position, set zeropoint 
                                         // Fix zeropoint problem when need
                                          if (column == 0 && (dataVector.size() == 0 || dataVector.get(0).length() == 0 ) ){
                                              combinedData+=zeroseparator; 
//                                              System.out.println("Set Zero Point at "+column+":");
                                          }
                                          
                                                                   
                                        if (dataVectorLenght != -1 & dataVectorLenght > column )
                                          { // not disturbe old data but copie.
                                              combinedData+= dataVector.get(column)+separator;
//                                              System.out.println("dataVector.get(column) column: "+column+ " value: "+ dataVector.get(column)+"m from: "+dataVector);
                                          }
                                             else
                                             // put in end last separator.
                                              if ((index + multtextcountPos)  != column) 
                                                  combinedData+=separator;
//                                        System.out.println("separator:"+combinedData);
                                    }
                             }
                        
        }else // if no data  exist and can be replased then generate empty rows until reach wanted line and set new data containing constructed string into it.
            {
                           //-------Jump through lines-----//
//                System.out.println("Size: "+dataStringHolder.size()); 
                    for (int row = dataStringHolder.size()  ; row  <= line; row++) // jump through lines
                        {
//                              System.out.println("Generate: "+row);
                              dataStringHolder.add(zeroseparator+separator); // _~ push emty string in while not right spot.
                              
                        }
                            //-------Write In Collumn------//
                            
//                                System.out.println("Set new data ");
                             
                             for (int column = 0; column <= (index + multtextcountPos) ; column++) // in column firstly generate separators then put new data
                             {
//                                   System.out.println("Jump Lines: "+ (index + multtextcountPos) +", column:"+column+ ", index: "+index+", multilines:"+multtextcountPos );
                                    if ( (index + multtextcountPos)  ==  column & (index + multtextcountPos) < (index + multitext.size()) ) // Set new value and keep tracking multi text arguments where must be replaced 
                                    {
                                         
                                        
                                        if ( multtextcountPos == 0 & multitext.get(multtextcountPos).length() <= 0 ) // Protect from empty/void string
                                                          combinedData+=zeroseparator+separator;
                                                    else  // imput data 
                                                          combinedData+=multitext.get(multtextcountPos)+separator;
                                          
                                        
                                         multtextcountPos++;;

//                                        System.out.println("cath Empty:"+combinedData);
                                    }else // generate separators until new value
                                    {
                                          
                                          if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                              combinedData+= " "+separator;
                                          else
                                              if (column == 0) // zero point in column
                                                  combinedData+=zeroseparator+separator; // if no spacer in zero spot , that can crash order of column
                                              else
                                                   if ((index + multtextcountPos)  != column) 
                                                       combinedData+=separator;
                                    }
                             }
            }
       
             dataStringHolder.set(line, combinedData); // replace constructed string into data storing vector
//             System.out.println("Rezult "+dataStringHolder.get(line));
                      

};
/**
 * Outdated
 * @param text Text append to next one.
 */

/**
 * Append new values in last line.
 * No protection added.
 * @param multitext Used to upload data.
 */
public void setRowAppend (String ...multitext) {
     
   setRowCollumn ( getSize() , 0 ,multitext);
};


/**
 * Append new values in last line.
 * Index inspection is for to avoid same data that exist in memory buffer.
 * @param uniqueIndexCompareStart From multitext list , is for avoid directly pointing range starts.
 * @param uniqueIndexCompareEnd From multitext list , is for avoid directly pointing range ends.
 * @param multitext Used to upload data.
 * @return Give condition about successful uploading/pushing into buffer or file as boolean 
 */
public boolean setRowAppend (int uniqueIndexCompareStart,int uniqueIndexCompareEnd,boolean ignoreCaseSensitive,String ...multitext) {
    
    String[]  varargs = new String [uniqueIndexCompareEnd - uniqueIndexCompareStart];
    int        varargpos = -1;
    // Remap 
    for (int index = uniqueIndexCompareStart; index < uniqueIndexCompareEnd; index++) { // Remap content to start from zero for getDataRowPosition function
           if (multitext.length > index) { 
                 varargpos++;
                 varargs[varargpos] = multitext[index];
                 System.out.println(index+" :---> "+varargs[varargpos]);
                 
           }else
               System.out.println(index+" :++++> multitext lenght: "+multitext.length+" varargpos: "+varargpos );
    }
    
        
    System.err.println("sdize "+getLenght());
       
      if (varargpos > -1 & !isFileEmpty())
      if ( getDataRowPosition(uniqueIndexCompareStart, uniqueIndexCompareEnd,ignoreCaseSensitive, varargs) != -1)
         return false;
    
        setRowCollumn ( getSize() , 0 ,multitext); // update memory buffer for later upload
    
   return true;
};

public boolean setRowRemove (int row) {
   
//    System.err.println("Size:"+dataStringHolder.size() );
    if (dataStringHolder.size() > row){
        dataStringHolder.remove(row);
        return true;
    }
    
    return false;
}

/**
 * Remove existing values in buffer or file.
 * Index inspection is for to avoid same data that exist in memory buffer.
 * @param uniqueIndexCompareStart From multitext list , is for avoid range starts.
 * @param uniqueIndexCompareEnd From multitext list , is for avoid range ends.
 * @param multitext Used to find data.
 * @return Give condition about successful remove from buffer or file as boolean
 */
public boolean setRowRemove (int uniqueIndexCompareStart,int uniqueIndexCompareEnd,boolean ignoreCaseSensitive,String ...multitext) 
{
  
      int        rowPos =  -1;
       
      if ( !isFileEmpty())
          rowPos = getDataRowPosition(uniqueIndexCompareStart, uniqueIndexCompareEnd,ignoreCaseSensitive, multitext);
      
      if ( rowPos != -1) {
          dataStringHolder.remove(rowPos);
          return true;
      }
    
   return false;
}



//
///**
// * Replace existing values in buffer or file.
// * Index inspection is for to avoid same data that exist in memory buffer.
// * @param uniqueIndexCompareStart From multitext list , is for avoid range starts.
// * @param uniqueIndexCompareEnd From multitext list , is for avoid range ends.
// * @param multitext Used to find data.
// * @return Give condition about successful replace from buffer or file as boolean
// */
//public boolean setRowReplace (int uniqueIndexCompareStart,int uniqueIndexCompareEnd,boolean ignoreCaseSensitive,String ...multitext) 
//{
//  
//      int        rowPos =  -1;
//       
//      if ( !isFileEmpty())
//          rowPos = getDataRowPosition(uniqueIndexCompareStart, uniqueIndexCompareEnd,ignoreCaseSensitive, multitext);
//      
//      if ( rowPos != -1) {
//          dataStringHolder.remove(rowPos);
//          return true;
//      }
//    
//   return false;
//}


/**
 * By given path, get if file exist
 * @return return boolean condition if file exist.
 */
 private boolean isFileExist() {
//     if (fileSystem.exists())
//         System.out.println("File '"+filename+"' exist.");
//     else
//         System.out.println("Unable to find file '"+filename+"' existence.");
     
     return fileSystem.exists();
 }
 /**
  * NOT WORKING WARNING!
  * @param lineLenght NAN
  * @param indexLenght NAN
  */
 public void setWithSeparators (  int lineLenght , int indexLenght){
    
     String combinedData=""; // buffer data~data2~
     String text = "";
        Vector<String> dataVector = new Vector<>(); 
        int dataVectorLenght;
                    
           //System.out.println("Vector: "+dataVector);   
         
           
            for (int eachLine=0;eachLine < getLenght();eachLine++){
                     dataVector =  getLineVector(eachLine);
                     dataVectorLenght =getLineColumnComponentsLenght(eachLine);
                     
                    if (dataStringHolder.size() > lineLenght) // combine in existing data
                    {

            //            System.err.println("Line is okey");
                                for (int column = 0; column <  indexLenght+1; column++) // if line is contains some data then try to not disturbe that until reach destination column
                                         {

            //                                   System.out.println("Data Lines: "+(index ) +", column:"+column+ ", index: "+index );  
                                                if (indexLenght == column) 
                                                        // Set new Value
                                                {       // value that must be change ignoring old one.
                                                         combinedData+=text+separator;
            //                                            System.out.println("cath:"+combinedData);
                                                }else // generate separators until new value
                                                {
                                                      if (dataVectorLenght != -1 & dataVectorLenght > column )
                                                      { // not disturbe old data but copie.
                                                          combinedData+= dataVector.get(column)+separator;
            //                                              System.out.println("dataVector.get(column) column: "+column+ " value: "+ dataVector.get(column)+"m from: "+dataVector);
                                                      }
                                                         else
                                                         // put in end last separator.
                                                          combinedData+=separator;
            //                                        System.out.println("separator:"+combinedData);
                                                }
                                         }

                    }else // if no data  exist and can be replased then generate empty rows until reach wanted line and set new data containing constructed string into it.
                        {
                                       //-------Jump through lines-----//
            //                System.out.println("Size: "+dataStringHolder.size()); 
                                for (int row = dataStringHolder.size()  ; row  <= lineLenght; row++) // jump through lines
                                    {
            //                              System.out.println("Generate: "+row);
                                          dataStringHolder.add("_"); // push emty string in while not right spot.

                                    }
                                        //-------Write In Collumn------//

            //                                System.out.println("Set new data ");

                                         for (int column = 0; column <= ( indexLenght); column++) // in column firstly generate separators then put new data
                                         {
            //                                  System.out.println("Jump Lines: "+ (index ) +", column:"+column+ ", index: "+index );   

                                                if (indexLenght == column) // Set new Value
                                                {
                                                    combinedData+=text+separator;

                                                    System.out.println("cath Empty:"+combinedData);
                                                }else // generate separators until new value
                                                {
                                                      if (dataVectorLenght != -1 & dataVectorLenght > column ) // write not changed string
                                                          combinedData+= text+separator;
                                                      else
                                                          if (column == 0) // zero point in column
                                                              combinedData+="_"+separator; // if no spacer in zero spot , that can crash order of column
                                                          else
                                                              combinedData+=separator; // everything after zero point
            //                                        System.out.println("separator Empty:"+combinedData);
                                                }
                                         }
                        }

                }                              
                      
             dataStringHolder.set(lineLenght, combinedData); // replace constructed string into data storing vector
//             System.out.println("Rezult "+dataStringHolder.get(line));
                      

};

 /**
  * Inspect banned symbols in this text
  * @param text Provide text where could be banned symbols
  * @return True if any or anywhere symbol is found 
  */
 public boolean isSeparator(String text)
 {
        return text.indexOf(separator) != -1;
 }
 /**
  * Inspect banned symbols in this multi-text
  * @param text Provide with a multi-text where could be banned symbols
  * @return True if any or anywhere symbol is found 
  */
 public boolean isSeparator(String ...text)
 {      
         for (String subtext: text){
//             System.out.print(subtext+", ");
             if (subtext.indexOf(separator) != -1)
             return true;
         }    
             
         return false;
 }
 
 /**
  * Inspect banned symbols in this vector text
  * @param text Provide vector with a text where could be banned symbols
  * @return True if any or anywhere symbol is found 
  */
 public boolean isSeparator(Vector<String> text)
 {      
         for (String subtext: text){
//             System.out.print(subtext+", ");
             if (subtext.indexOf(separator) != -1)
             return true;
         }    
             
         return false;
 }
 
 /**
  * Inspect banned symbols in this arraylist text
  * @param text Provided arraylist with a text where could be banned symbols
  * @return True if any or anywhere symbol is found 
  */
 public boolean isSeparator(ArrayList<String> text)
 {      
         for (String subtext: text){
//             System.out.print(subtext+", ");
             if (subtext.indexOf(separator) != -1)
             return true;
         }    
             
         return false;
 }
 /**
  *Main and only updater that job is to get all data from file and store into dataStringHolder vector to use as a buffer in this class
  * @since  1.0
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
                          System.err.println("Class: StaticFileManager, method: readInFileUpdater() ERROR_ReadLineMethod path:"+fileSystem.getAbsolutePath());
                     }
             // line is not visible here.
     }
   /**
    * Main and only data pusher into file from local class buffer.
    * Use to update filename.txt with data.
    */
   public void uploadInFile()
   {
       String textCollection = "";
       
//       System.out.println("Size: "+getRowsLenght());
       
       for (int line = 0; line < getSize(); line++) {
           textCollection+=dataStringHolder.get(line)+"\n";
//           System.out.println("Line: "+line+" ::::::\n"+textCollection);
       }
//       System.out.println("Data Send:::\n"+textCollection);
       
       
        try {
             outputStream = new FileWriter(filename);                      
  

             outputStream.write(textCollection);
             outputStream.close();
             
        }catch (Exception e) {
            System.out.println("Error with PrintWriter:"+filename);
        }
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
                    boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);
                    boolean wasZeroPoint = false; // remember if was encountered with zero point separator at least one time
//                    System.out.println("ignoreZeroPoint: "+ignoreZeroSeparator);
                  
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                        
                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
                               
                                    if(ignoreZeroSeparator & !wasZeroPoint){
                                         SeparatedTextVector.add(""); // remove ignoreZeroSeparator that is "_"~~~~"
                                         wasZeroPoint = true;
                                         }     
                                    else
                                        SeparatedTextVector.add ( dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd) ); // only job is to get text from bouth separators
                                    
//                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd)+"], text-index counted: "+(textIndex));        
                                 ++textIndex; // last text was counted
                            }
                            
                    }
            }else {
//                 System.err.println("getLineVector line out of range ");
//                 throw new ArrayIndexOutOfBoundsException(line);
                 } 
            
        return SeparatedTextVector;
       
       
   }
   
   /**
    * By the given line , detach data from the dataHolder(line) raw string and separate sub-data into vector points by given range. 
    * Start use as offset where to start to recipient data, and end where to stop.
    * 
    * @param line Specify column from the data vector.
    * @param start Where start to catch data.
    * @param end   End of catched data, also doesn't have any limits.
    * @return  Provide vector with separated sub-data that was defined in specified range 'start-/end' to do that.
    */
 public Vector getLineVectorSpecifiedSize(int line, int start , int end) {
   
      int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            Vector<String> SeparatedTextVector = new Vector();
            
            if (dataStringHolder.size() > line ) // 0 is equal that no text in file 
            {
                     boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);
                     boolean wasZeroPoint = false; // remember if was encountered with zero point separator at least one time
                    
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
//                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd)+"], text-index counted: "+(textIndex));
                                     if (start <= textIndex & textIndex < end ) // cath in data in specified range
                                     {
                                             if(ignoreZeroSeparator & !wasZeroPoint &  textIndex == 0) // detect zero point where to delete zeroseparator that equal-> '_'
                                                 {
                                                     SeparatedTextVector.add(""); // remove ignoreZeroSeparator that is "_"~~~~"
                                                     wasZeroPoint = true;
                                                 }   
                                             else
                                                 SeparatedTextVector.add ( dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd) ); // only job is to get text from bouth separators
                                     }
                                     
//                                       if(ignoreZeroSeparator & !wasZeroPoint ){
//                                         SeparatedTextVector.add(""); // remove ignoreZeroSeparator that is "_"~~~~"
//                                         wasZeroPoint = true;
//                                         }     
//                                         else
//                                        SeparatedTextVector.add ( dataStringHolder.get(line).substring((separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd) ); // only job is to get text from bouth separators
//                                    
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
                     boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);
                    
//                    System.out.println("Raw data: "+dataStringHolder.get(line));
                    while (separatorIndexEnd != -1 & dataStringHolder.size() > line ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                    {
                        separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                        separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter

                        
                            if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value
                                
//                               System.out.println("line:"+line+",Separator start-index:" +separatorIndexStart+", end-index : " + separatorIndexEnd+" ,output:["+dataStringHolder.get(line).substring(separatorIndexStart+1,separatorIndexEnd)+"], text-index counted: "+(textIndex)+ ", user-index:"+index+",user-found = "+(textIndex == index ? "yes":"no"));
                                  
//                                  if (ignoreZeroSeparator & index == 0 ) // fix zero point  
//                                      return ""; 

                                  if (textIndex == index & ! (ignoreZeroSeparator & textIndex == 0) ){ // only save text when needet , zero point protection
                                       SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd); // only job is to get text from bouth separators
//                                        System.err.println("rezult data: "+SeparatedText);
                                        return SeparatedText; // break and return if find what user want. 
                                  }
                                 
                                  ++textIndex; // last text was counted
                            }
                            
                           
                    }
            } else {
//                System.out.println("getDataFromLineIndex line out of range");   
            //     throw new ArrayIndexOutOfBoundsException(line);    
            }
            
                   
           
        return SeparatedText;
   }



/**
 * In specified row , count each  sub-data column.
 * @param line Given argument are use to specific line. 
 * @return Contains length each sub-data points. If fail return -1
 */
public int getLineColumnComponentsLenght(int line) {

            int separatorIndexStart =0;// rezoved begining of the text position    
            int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
            int textIndex = 0;  // count each index of text between separators
            
            // emty vector return 0;
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
//                System.out.println("getLineRowComponentsLenght line out of range");
//                 throw new ArrayIndexOutOfBoundsException(line);    
            }

       if (textIndex == 0) return -1; else return textIndex; // if fail to find separator, fix value from zero to minus one,else return finded indexes
}


/**
 * Obtain data existence from unknown line by defined index position where are compare whith  dataMath given value.  
 * @param index Where should compare a data.
 * @param dataMath Data that math in specified index
 * @return Give data row position if find any data are mathed. Else return -1
 */
 public int getDataRowPosition( int index, String dataMath)  { 
        

//         
         
         
           
         
           
                
                for (int line = 0; line < getLenght()+1; line++) 
                {
                    
//                       System.err.println("DATA: "+dataStringHolder.get(line));
                    int separatorIndexStart =0;// rezoved begining of the text position    
                    int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
                    int textIndex = 0;  // count each index of text between separators
                    String SeparatedText="";
                    
                    
                                    boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);

                               //     System.out.print("line: "+line+",data Math: ["+dataMath+"], data: ");
                                    
               //                    System.out.println("Raw data: "+dataStringHolder.get(line));
                                   while (separatorIndexEnd != -1 ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                                   {
                                       separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                                       separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                                       

                                           if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value

                                                 SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd);
                                         //        System.out.print(","+textIndex+": "+SeparatedText+ " ->math:"+(SeparatedText.compareTo(dataMath) == 0? "yes" : "no"));
                                                 
                                                 if (SeparatedText.compareTo(dataMath) == 0 & index == textIndex){ // only save text when needet , zero point protection
                                                    return line;
                                                 }

                                                 ++textIndex; // last text was counted
                                           }


                                   }
                          
                               //      System.out.println("");
            }
                           

                       return -1;
   }


/**
 * Obtain data existence from unknown line by defined index position where are compare whith  dataMath given value.  
 * @param index Where should compare a data.
 * @param dataMath Data that math in specified index
 * @param ignoreCaseSensitive Ignore comparison of smaller or upper case.
 * @return Give data row position if find any data are mathed. Else return -1
 */
 public int getDataRowPosition( int index, String dataMath , boolean ignoreCaseSensitive)  { 
        

//         
         
         
           
         
           
                
                for (int line = 0; line < getLenght()+1; line++) 
                {
                    
//                       System.err.println("DATA: "+dataStringHolder.get(line));
                    int separatorIndexStart =0;// rezoved begining of the text position    
                    int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
                    int textIndex = 0;  // count each index of text between separators
                    String SeparatedText="";
                    
                    
                                    boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);

                               //     System.out.print("line: "+line+",data Math: ["+dataMath+"], data: ");
                                    
               //                    System.out.println("Raw data: "+dataStringHolder.get(line));
                                   while (separatorIndexEnd != -1 ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                                   {
                                       separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                                       separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                                       

                                           if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value

                                                 SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd);
                                               
                                                 if (ignoreCaseSensitive){ // ignore case sencitive
                                                    SeparatedText = SeparatedText.toUpperCase();
                                                    dataMath = dataMath.toUpperCase();
                                                }
                                         //        System.out.print(","+textIndex+": "+SeparatedText+ " ->math:"+(SeparatedText.compareTo(dataMath) == 0? "yes" : "no"));
                                                 
                                                 if (SeparatedText.compareTo(dataMath) == 0 & index == textIndex){ // only save text when needet , zero point protection
                                                    return line;
                                                 }

                                                 ++textIndex; // last text was counted
                                           }


                                   }
                          
                               //      System.out.println("");
            }
                           

                       return -1;
   }
 

/**
 * Obtain data existence from unknown line by defined index position where are compare whith  dataMath given value.
 * Note: All Data Math should be in correct order to succeed a chain test.
 * @param startIndex Starting point where data should exist.
 * @param endIndex ending point where data should end.
 * @param dataMath Data that math in specified range between starting point and ending point
 * @return Give data row when find full data collection. Else return -1.
 */
 
 public int getDataRowPosition( int startIndex, int endIndex, String ...dataMath )  { 
        
        if  (startIndex >= endIndex) // Out of range 
  {
      System.err.println("getDataRowPosition int startIndex is to big.");
      return -1;
  }              
                
                for (int line = 0; line < getLenght()+1; line++) 
                {
                    
//                       System.err.println("DATA: "+dataStringHolder.get(line));
                    int separatorIndexStart =0;// rezoved begining of the text position    
                    int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
                    int textIndex = 0;  // count each index of text between separators
                    int countData = 0;
                    String SeparatedText="";
                    
                    
                                    boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);

                                   // System.out.print("line: "+line+",data Math: ["+dataMath[countData]+"], data: ");
                                    
                                   while (separatorIndexEnd != -1 ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                                   {
                                       separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                                       separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                                       

                                           if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value

                                                 SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd);
                                               
                                                 
                                             //    System.out.print(",text index: "+textIndex+": data: ["+SeparatedText+ " == "+dataMath[countData]+" is same "+(SeparatedText.compareTo(dataMath[countData]) == 0? "..yes]" : ".no]"));
                                                  
                                                 if (SeparatedText.compareTo(dataMath[countData]) == 0 & (startIndex <=textIndex & endIndex > textIndex) ){ // only save text when needet , zero point protection
                                                    
                                                      
                                                     
                                                    // Dont go then max arguments.                 if arguments less then indexes range . Ignore. Especily when is fron 0 to 2 
                                                     if(dataMath.length    >  countData & (endIndex-startIndex ) <= dataMath.length   ){
                                                      
                                                             countData++;
                                                     }
                                                 }
//                                                  
                                                 
                                                 ++textIndex; // last text was counted
                                           
                                           }
                                           
                                           
                                           if (countData  == (endIndex-startIndex )    ) // return rezult find all values and registered as a cout
                                           {
                                        //       System.out.print("\n... [-N-->Data Count:"+countData +" Expected:> "+(endIndex-startIndex )+"]");
                                               return line;
                                           }
                                           
                                           if (textIndex > endIndex ) // stop looking after known max range 
                                              break;  
                                   }
                          
                                   //  System.out.println("");
            }
                           

                       return -1;
     
   }

 
/**
 * Obtain data existence from unknown line by defined index position where are compare whith  dataMath given value.
 * Note: All Data Math should be in correct order to succeed a chain test.
 * @param startIndex Starting point where data should exist.
 * @param endIndex ending point where data should end.
 * @param dataMath Data that math in specified range between starting point and ending point
 * @param ignoreCasSensitive Ignore comparison of smaller or upper case.
 * @return Give data row when find full data collection. Else return -1.
 */

 public int getDataRowPosition( int startIndex, int endIndex,boolean ignoreCaseSensitive , String ...dataMath  )  { 
        
     if  (startIndex >= endIndex) // Out of range 
  {
      System.err.println("getDataRowPosition int startIndex is to big.");
      return -1;
  }              
  
//     if (getLenghtColumn() == -1) {
//        System.out.println("Last column position  " + getLenghtColumn() + " not exist. Empty file");
//        return -1 ;
//     }
         for (int line = 0; line < getLenght()+1; line++) 
                {
                    
//                       System.err.println("DATA: "+dataStringHolder.get(line));
                    int separatorIndexStart =0;// rezoved begining of the text position    
                    int separatorIndexEnd =0; // rezolve last marker point of  ' ~ ' separator
                    int textIndex = 0;  // count each index of text between separators
                    int countData = 0;
                    String SeparatedText="";
                    
                    
                                    boolean ignoreZeroSeparator = (dataStringHolder.get(line).indexOf(zeroseparator) == 0 ? true:false);

                                   // System.out.print("line: "+line+",data Math: ["+dataMath[countData]+"], data: ");
                                    
                                   while (separatorIndexEnd != -1 ) // while not reached end in string with point separators , and size of the line is not greater then expected then
                                   {
                                       separatorIndexStart = separatorIndexEnd; // update last each time leaving beginning of current text between separators
                                       separatorIndexEnd = dataStringHolder.get(line).indexOf(separator,separatorIndexEnd+1); // get last point and move to anoter
                                       

                                           if (separatorIndexEnd != -1) { //in any case if index range is to big when that will rezult in no string value

                                                 SeparatedText = dataStringHolder.get(line).substring( (separatorIndexStart == 0 ? separatorIndexStart : separatorIndexStart+1),separatorIndexEnd);
                                               
                                                  if (ignoreCaseSensitive){ // ignore case sencitive
                                                    SeparatedText = SeparatedText.toUpperCase();
                                                    dataMath[countData] = dataMath[countData].toUpperCase();
                                                  }
                                                 
                                              //   System.out.print(",text index: "+textIndex+": data: ["+SeparatedText+ " == "+dataMath[countData]+" is same "+(SeparatedText.compareTo(dataMath[countData]) == 0? "..yes]" : ".no]"));
                                                  
                                                 if (SeparatedText.compareTo(dataMath[countData]) == 0 & (startIndex <=textIndex & endIndex > textIndex) ){ // only save text when needet , zero point protection
                                                    
                                                      
                                                     
                                                    // Dont go then max arguments.                 if arguments less then indexes range . Ignore. Especily when is fron 0 to 2 
                                                     if(dataMath.length    >  countData & (endIndex-startIndex ) <= dataMath.length   ){
                                                      
                                                             countData++;
                                                     }
                                                 }
//                                                  
                                                 
                                                 ++textIndex; // last text was counted
                                           
                                           }
                                           
                                           
                                           if (countData  == (endIndex-startIndex )    ) // return rezult find all values and registered as a cout
                                           {
                                            //   System.out.print("\n... [-N-->Data Count:"+countData +" Expected:> "+(endIndex-startIndex )+"]");
                                               return line;
                                           }
                                           
                                           if (textIndex > endIndex ) // stop looking after known max range 
                                              break;  
                                   }
                          
                                    // System.out.println("");
            }
                           

                       return -1;
   }


 
 // get lenght only from RAM but not directly from file.txt , to do that need use updeter first.
/**
 * Get total lines stored in data as length.
 * @return  Provide total amount of the column
 */
public int getSize() {
    
     return dataStringHolder.size();
     
}

/**
 * Get total ammout of lines ignoring full size.
 * Can be use directly point last line.
 * @return Provide last line position.
 */
public int getLenght() 
{     
    int len=getSize();
    if (len > 0)
      return len - 1;
    else
      return 0;
    
};

/**
 * Provide information about total amount of columns in last row/line.
 * Can be use directly point last line column length.
 * @return Give value from existing last row columns.
 */
public int getLenghtColumn() 
{
return getLineColumnComponentsLenght(getLenght());
}
/**
 * Check if file/buffer have content
 * 
 * @return If is any content then return true.
 */
public boolean isFileEmpty() {
    if (dataStringHolder.size() > 0) {
       // System.out.println(" :-> "+dataStringHolder.get(0));
        return false;
    } 
    return true; // if empty then return true
}

/**
 * Display all data from dataStringHolder
 */

public void displayDataMap() 
{  
       System.out.println("\n------------Display data map--------------------");
            for (int line = 0; line < dataStringHolder.size(); line++) {
                System.out.println(line+" "+dataStringHolder.get(line) );
            }
       System.out.println("-------------------------------------------------"); 
       System.out.println("Total Rows: " +(getSize())+".");
       System.out.println("Last row position:" +(getLenght()) + ". , and last column position:" +getLenghtColumn()+".");
       System.out.println("---------------Data map in Value-----------------\n"); 
      
       for (int line = 0; line < getSize(); line++) {
           
           System.out.println("Line: "+line);
           for (int column = 0; column < getLineColumnComponentsLenght(line); column++) 
           {
                System.out.println("  Collumn: "+column);
               
           }
           
       }
      System.out.println("-------------------------------------------------");  
}

  



















}

