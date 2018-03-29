/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macros;
import java.awt.event.KeyEvent;



/**
 *
 * @author zilvinus.peciulis@gmailc.com
 */

 // Tmap that hold shift information



public class IOKeyMap {
/**
 * Hold unicode shifting properties
 */   
 
 private class __keymap {
    public char key;
    public int value;
    public boolean shift; // if unicode
    
     
    
    public __keymap(char key,int value,boolean shift) {
     
         this.key = key;
         this.value = value;
         this.shift = shift;
     };
}   
 public  __keymap[] keymap;    
    
  public  IOKeyMap () {
        keymap  = new __keymap[44+1];
        
        // A-Z
        keymap[0] = new __keymap('A',KeyEvent.VK_A,true);
        keymap[1] = new __keymap('B',KeyEvent.VK_B,true);
        keymap[2] = new __keymap('C',KeyEvent.VK_C,true);
        keymap[3] = new __keymap('D',KeyEvent.VK_D,true);
        keymap[4] = new __keymap('E',KeyEvent.VK_E,true);
        keymap[5] = new __keymap('F',KeyEvent.VK_F,true);
        keymap[6] = new __keymap('G',KeyEvent.VK_G,true);
        keymap[7] = new __keymap('H',KeyEvent.VK_H,true);
        keymap[8] = new __keymap('J',KeyEvent.VK_J,true);
        keymap[9] = new __keymap('K',KeyEvent.VK_K,true);
        keymap[10] = new __keymap('L',KeyEvent.VK_L,true);
        keymap[11] = new __keymap('M',KeyEvent.VK_M,true);
        keymap[12] = new __keymap('N',KeyEvent.VK_N,true);
        keymap[13] = new __keymap('O',KeyEvent.VK_O,true);
        keymap[14] = new __keymap('P',KeyEvent.VK_P,true);
        keymap[15] = new __keymap('Q',KeyEvent.VK_Q,true);
        keymap[16] = new __keymap('R',KeyEvent.VK_R,true);
        keymap[17] = new __keymap('S',KeyEvent.VK_S,true);
        keymap[18] = new __keymap('T',KeyEvent.VK_T,true);
        keymap[19] = new __keymap('U',KeyEvent.VK_U,true);
        keymap[20] = new __keymap('V',KeyEvent.VK_V,true);
        keymap[21] = new __keymap('W',KeyEvent.VK_W,true);
        keymap[22] = new __keymap('X',KeyEvent.VK_X,true);
        keymap[23] = new __keymap('Y',KeyEvent.VK_Y,true);
        keymap[24] = new __keymap('Z',KeyEvent.VK_Z,true);
        // from ~ to +
        keymap[25] = new __keymap('~',KeyEvent.VK_SLASH,true); // fix for later, now gives ???
        keymap[26] = new __keymap('!',KeyEvent.VK_1,true);
        keymap[27] = new __keymap('@',KeyEvent.VK_2,true);
        keymap[28] = new __keymap('#',KeyEvent.VK_3,true);
        keymap[29] = new __keymap('$',KeyEvent.VK_4,true);
        keymap[30] = new __keymap('%',KeyEvent.VK_5,true);
        keymap[31] = new __keymap('^',KeyEvent.VK_6,true);
        keymap[32] = new __keymap('&',KeyEvent.VK_7,true);
        keymap[33] = new __keymap('*',KeyEvent.VK_8,true);
        keymap[34] = new __keymap('(',KeyEvent.VK_9,true);
        keymap[35] = new __keymap(')',KeyEvent.VK_0,true);
        keymap[36] = new __keymap('_',KeyEvent.VK_MINUS,true);
        keymap[37] = new __keymap('+',KeyEvent.VK_EQUALS,true);  // fix for later, now gives
        // Separators
        keymap[38] = new __keymap('<',KeyEvent.VK_COMMA,true);
        keymap[39] = new __keymap('>',KeyEvent.VK_PERIOD,true);
        keymap[40] = new __keymap('?',KeyEvent.VK_SLASH,true);
        keymap[41] = new __keymap(':',KeyEvent.VK_SEMICOLON,true);
        keymap[42] = new __keymap('"',KeyEvent.VK_COMMA,true);
        keymap[43] = new __keymap('{',KeyEvent.VK_OPEN_BRACKET,true);
        keymap[44] = new __keymap('}',KeyEvent.VK_CLOSE_BRACKET,true);
        
        
        
        
    }
    
    // return total ammount of array lists
  /**
   * 
   * @return  Give total map length
   */
    int getLenght() {return keymap.length;}
    // check if key exist
    
    
    /**
     * Check if key exist in map 
     * @param compare Value are that looking for.
     * @return Report condition about successful found and give position in the map. Else return -1
     */
    
    
    public int isKeyExist(char compare) {
     int pos = 0;
        for (__keymap obj : keymap) {
            
//            System.err.println("KEY: "+obj.key +", equal "+ (compare == obj.key ) );
           
            if (compare == obj.key)
                return pos;
            
          pos ++;  
        }
        return -1;
    }
    
    
    /**
     * Give key press value 
     * @param key_position Position comes from  [ int isKeyExist(char compare) ] if key exist.
     * @return Give key value else if out of map range return -1
     */
    public int getValue(int key_position)
     
    {
         if (key_position > -1 & key_position <= keymap.length)
             return keymap[key_position].value;
         
       return -1;
    }
            
            
           
    
    /**
     * See All map of parameters
     */
  public  void seeMap () {
         System.err.println("array len: "+keymap.length);
        
        for (__keymap object : keymap) {
            
            System.out.println(object.key+", "+object.value+", "+object.shift );
        }
    }
    
}
