/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 * 
 * Print Bracket Number
Given an expression exp of length n consisting of some brackets. The task is to print the bracket numbers when the expression is being parsed.

Examples :

Input : (a+(bc))+(d/e)
Output : 1 2 2 1 3 3
The highlighted brackets in the given expression
(a+(bc))+(d/e) has been assigned the numbers as:
1 2 2 1 3 3.

Input : ((())(()))
Output : 1 2 3 3 2 4 5 5 4 1
 */
public class Brackets {

    public static void main(String[] args) {
        //get the expression
        String expression=JOptionPane.showInputDialog("Digita la expresión");
        
        //for test purposes
        //String expression="(a+(bc))+(d/e)";
        //String expression="((())(()))";
        
        //split the expression in a char array
        char[] chars=expression.toCharArray();
        
        //arraylist for save the last bracket number
        ArrayList<Integer> count=new ArrayList();
        int countBracket=0;
        String result="";
        
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('){
                countBracket++;
                result+=countBracket;
                count.add(countBracket);
            }
            
            if(chars[i]==')'){
                result+=count.get(count.size()-1);
                count.remove(count.size()-1);
            }
        }
        
        JOptionPane.showMessageDialog(null,"Result:\n"+result);
    }   
}