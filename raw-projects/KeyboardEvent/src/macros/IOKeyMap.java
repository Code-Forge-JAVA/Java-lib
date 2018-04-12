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
    public boolean shift = true; // if unicode
    
     
    
    public __keymap(char key,int value) {
     
         this.key = key;
         this.value = value;
        
     };
    
    public __keymap(char key,int value,boolean shift) {
     
         this.key = key;
         this.value = value;
         this.shift = shift ;
        
     };
}   
 public  __keymap[] keymap;    
    
  public  IOKeyMap () {
        keymap  = new __keymap[70+1];
        
        // A-Z
        keymap[0] = new __keymap('A',KeyEvent.VK_A);
        keymap[1] = new __keymap('B',KeyEvent.VK_B);
        keymap[2] = new __keymap('C',KeyEvent.VK_C);
        keymap[3] = new __keymap('D',KeyEvent.VK_D);
        keymap[4] = new __keymap('E',KeyEvent.VK_E);
        keymap[5] = new __keymap('F',KeyEvent.VK_F);
        keymap[6] = new __keymap('G',KeyEvent.VK_G);
        keymap[7] = new __keymap('H',KeyEvent.VK_H);
        keymap[8] = new __keymap('J',KeyEvent.VK_J);
        keymap[9] = new __keymap('K',KeyEvent.VK_K);
        keymap[10] = new __keymap('L',KeyEvent.VK_L);
        keymap[11] = new __keymap('M',KeyEvent.VK_M);
        keymap[12] = new __keymap('N',KeyEvent.VK_N);
        keymap[13] = new __keymap('O',KeyEvent.VK_O);
        keymap[14] = new __keymap('P',KeyEvent.VK_P);
        keymap[15] = new __keymap('Q',KeyEvent.VK_Q);
        keymap[16] = new __keymap('R',KeyEvent.VK_R);
        keymap[17] = new __keymap('S',KeyEvent.VK_S);
        keymap[18] = new __keymap('T',KeyEvent.VK_T);
        keymap[19] = new __keymap('U',KeyEvent.VK_U);
        keymap[20] = new __keymap('V',KeyEvent.VK_V);
        keymap[21] = new __keymap('W',KeyEvent.VK_W);
        keymap[22] = new __keymap('X',KeyEvent.VK_X);
        keymap[23] = new __keymap('Y',KeyEvent.VK_Y);
        keymap[24] = new __keymap('Z',KeyEvent.VK_Z);
        
        keymap[25] = new __keymap('0',KeyEvent.VK_NUMPAD0,false);
        keymap[26] = new __keymap('1',KeyEvent.VK_NUMPAD1,false);
        keymap[27] = new __keymap('2',KeyEvent.VK_NUMPAD2,false);
        keymap[28] = new __keymap('3',KeyEvent.VK_NUMPAD3,false);
        keymap[29] = new __keymap('4',KeyEvent.VK_NUMPAD4,false);
        keymap[30] = new __keymap('5',KeyEvent.VK_NUMPAD5,false);
        keymap[31] = new __keymap('6',KeyEvent.VK_NUMPAD6,false);
        keymap[32] = new __keymap('7',KeyEvent.VK_NUMPAD7,false);
        keymap[33] = new __keymap('8',KeyEvent.VK_NUMPAD8,false);
        keymap[34] = new __keymap('9',KeyEvent.VK_NUMPAD9,false);
        
        // from ~ to +
        keymap[35] = new __keymap('~',KeyEvent.VK_SLASH); // fix for later, now gives ???
        keymap[36] = new __keymap('!',KeyEvent.VK_1);
        keymap[37] = new __keymap('@',KeyEvent.VK_2);
        keymap[38] = new __keymap('#',KeyEvent.VK_3);
        keymap[39] = new __keymap('$',KeyEvent.VK_4);
        keymap[40] = new __keymap('%',KeyEvent.VK_5);
        keymap[41] = new __keymap('^',KeyEvent.VK_6);
        keymap[42] = new __keymap('&',KeyEvent.VK_7);
        keymap[43] = new __keymap('*',KeyEvent.VK_8);
        keymap[44] = new __keymap('(',KeyEvent.VK_9);
        keymap[45] = new __keymap(')',KeyEvent.VK_0);
        keymap[46] = new __keymap('_',KeyEvent.VK_MINUS);
        keymap[47] = new __keymap('+',KeyEvent.VK_EQUALS);  // fix for later, now gives
        // Separators
        keymap[48] = new __keymap('<',KeyEvent.VK_COMMA);
        keymap[49] = new __keymap('>',KeyEvent.VK_PERIOD);
        keymap[50] = new __keymap('?',KeyEvent.VK_SLASH);
        keymap[51] = new __keymap(':',KeyEvent.VK_SEMICOLON);
        keymap[52] = new __keymap('"',KeyEvent.VK_COMMA);
        keymap[53] = new __keymap('{',KeyEvent.VK_OPEN_BRACKET);
        keymap[54] = new __keymap('}',KeyEvent.VK_CLOSE_BRACKET);
        //LT
        keymap[55] = new __keymap('ą',KeyEvent.VK_1,false);
        keymap[56] = new __keymap('č',KeyEvent.VK_2,false);
        keymap[57] = new __keymap('ę',KeyEvent.VK_3,false);
        keymap[58] = new __keymap('ė',KeyEvent.VK_4,false);
        keymap[59] = new __keymap('į',KeyEvent.VK_5,false);
        keymap[60] = new __keymap('š',KeyEvent.VK_6,false);
        keymap[61] = new __keymap('ų',KeyEvent.VK_7,false);
        keymap[62] = new __keymap('ū',KeyEvent.VK_8,false);
        
        keymap[63] = new __keymap('Ą',KeyEvent.VK_1);
        keymap[64] = new __keymap('Č',KeyEvent.VK_2);
        keymap[65] = new __keymap('Ę',KeyEvent.VK_3);
        keymap[66] = new __keymap('Ė',KeyEvent.VK_4);
        keymap[67] = new __keymap('Į',KeyEvent.VK_5);
        keymap[68] = new __keymap('Š',KeyEvent.VK_6);
        keymap[69] = new __keymap('Ų',KeyEvent.VK_7);
        keymap[70] = new __keymap('Ū',KeyEvent.VK_8);
        
       
        
        
        
        
        
        
        
        
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
    
     public boolean getShift(int shift_position)
     
    {
         if (shift_position > -1 & shift_position <= keymap.length)
             return keymap[shift_position].shift;
         
       return false;
    }
            
            
           
    
    /**
     * See All map of parameters
     */
  public  void seeMap () {
         System.err.println("array len: "+keymap.length);
        
        for (__keymap object : keymap) {
            
            System.out.println(object.key+", "+object.value );
        }
    }
    
}
