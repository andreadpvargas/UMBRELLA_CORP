/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numeros;

/**
 *
 * @author Ana Gaviria
 */
public class Progresion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("----PROGRESIÓN GEOMETRICA---- ");
        System.out.println(" ");
        
      
        System.out.println("Ejemplo 1: ");
        System.out.println("----------------------------");
        
        //Creo mi objeto
        Numeros num1 = new Numeros(2, 2 , 4);
        
        System.out.println("Primer Termino = " + num1.getA());
        System.out.println("Razon = " + num1.getR());
        System.out.println("Número = " + num1.getN());
        
        System.out.println("El " + num1.getN() + "th " + "termino " 
                           +   "de la" + "\n" + "Progresión Geometrica es: "
                           + num1.getA() * (int)(Math.pow(num1.getR(), num1.getN() - 1)) );
        

        System.out.println("----------------------------");
         
        System.out.println(" ");
        
        System.out.println("Ejemplo 2: ");
        System.out.println("----------------------------");
        
        //Creo mi objeto
        Numeros num2 = new Numeros(2, 3 , 5);
        
        System.out.println("Primer Termino = " + num2.getA());
        System.out.println("Razon = " + num2.getR());
        System.out.println("Número = " + num2.getN());
        
        System.out.println("El " + num2.getN() + "th " + "termino " 
                           +   "de la" + "\n" + "Progresión Geometrica es: "
                           + num2.getA() * (int)(Math.pow(num2.getR(), num2.getN() - 1)) );
        

    }
    
}
