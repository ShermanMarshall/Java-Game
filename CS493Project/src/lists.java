
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sherman
 */
public class lists {
        String name;
        lists node;
        
        public lists(String val) {
            name = val;
            Scanner input = new Scanner(System.in);
            String string = input.nextLine();
            node = new lists(string);            
        }
        
}
