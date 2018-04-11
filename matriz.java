/*Dayana: dada una matriz cuadrada, intercambia el elemento de diagonales mayor y menor.
Ejemplo:

Entrada: 0 1 2 
3 4 5 
6 7 8

Salida: 2 1 0 
3 4 5 
8 7 6
*/

import java.util.Scanner;

public class main {

    public static void main(String args[]) {
        //creacion de la matriz
        final int FILAS = 3, COLUMNAS = 3;
        Scanner sc = new Scanner(System.in);
        int i, j, mayor, menor;
        int filaMayor, filaMenor, colMayor, colMenor;
        int[][] A = new int[FILAS][COLUMNAS];
        System.out.println("Ingrese valores a la matriz: ");
        for (i = 0; i < FILAS; i++) {
            for (j = 0; j < COLUMNAS; j++) {
                System.out.print("A[" + i + "][" + j + "]= ");
                A[i][j] = sc.nextInt();
            }
        }
           System.out.println("----------------");
        // me muesta la matriz
        System.out.println(" MATRIZ ");
        for (i = 0; i < A.length; i++) {
            for (j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
         
            System.out.println();
            
        }
        System.out.println("----------------");
        //intercambia el elemento de diagonales mayor y menor.
        System.out.println(" MATRIZ MODIFICADA ");
        //primera fila
        int valorMenor = A[0][0];
        A[0][0] = A[0][A.length - 1];
        A[0][A.length - 1] = valorMenor;
         //segunda fial
        int valorMayor = A[A.length - 1][A.length - 1];
        A[A.length - 1][A.length - 1] = A[A.length - 1][0];
        A[A.length - 1][0] = valorMayor;
        
        // imprime matriz modificada
        for (i = 0; i < A.length; i++) {
            for (j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
