

import javax.swing.JOptionPane;
// eliminar todo Palabras palindrómicas de la oración 

class Palindromicas {

    // función para verificar si 'str' es palíndromo
    static boolean esPalindromicas(String str) {

        int i = 0, j = str.length() - 1;

        // atravesando desde ambos extremos
        while (i < j) {
            // no palindrome
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }
        // palindrome
        return true;
    }

    // función para eliminar todas las palabras palindrómicas
// de la oración dada
    static void eliminarPalidromicas(String str) {
        //dividir la oracion en palabras 
        String[] palabras = str.split(" ");

        //recorremos las palabras 
        for (int i = 0; i < palabras.length; i++) {
            if (esPalindromicas(palabras[i]) == false) {
                System.out.print(palabras[i] + " ");
            }

        }

    }

    // Código de conductor
    public static void main(String[] args) {
        String str = JOptionPane.showInputDialog("Introduzca la palabra");
        eliminarPalidromicas(str);
    }
}
