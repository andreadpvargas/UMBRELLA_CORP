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
public class Numeros {
    //Atributos de la clase
    int a; //Primer Termino 
    int r; //Razon
    int n; //Número

   //Constructor de la clase
    public Numeros(int a, int r, int n) {
        this.a = a;
        this.r = r;
        this.n = n;
    }
     

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
