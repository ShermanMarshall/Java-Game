
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
public class lists2 {
public static void main (String args[]) {
        lists node, list;               
        Scanner input = new Scanner(System.in);
        System.out.print("Input Stuff: ");
        String first = input.nextLine();
        node = new lists(first);
        list = node;
        while(!input.equals("!!!")) {
            System.out.print("Input stuff: ");
            String string = input.nextLine();
                               
            node = new lists(string);
        }    
        
        while (!list.name.equals("!!!")) {
            System.out.println(list.name);
            list = list.node;
        }
         
        System.out.println("\nThats all folks");                        
        }
}
    
            
        
