package horas;

public class Horas {

    int hora1;
    int hora2;

    public int getHora1() {
        return hora1;
    }

    public void setHora1(int hora1) {
        this.hora1 = hora1;
    }

    public int getHora2() {
        return hora2;
    }

    public void setHora2(int hora2) {
        this.hora2 = hora2;
    }

    
    
    // Funcion para encontrar los minutos
    public static void main(String[] args) {
        Horas hor1= new Horas();
        
        hor1.setHora1(5);
        hor1.setHora2(7);
    // encontrando el ángulo entre minuto
        // la aguja en la primera hora por el recorrido 360/12=30 y se divide por 11
        // para encontrar el tiempo en minutos 
       System.out.println("Ejemplo n°1 de entradas");
       
        int AngTheta = 30 * hor1.getHora1();
        System.out.println("(" + AngTheta * 2 + "/" + " 11 ) minutos");
        
        System.out.println("Ejemplo n°2 de entradas");
         int AngTheta2 = 30 * hor1.getHora2() ;
        System.out.println("(" + AngTheta2 * 2 + "/" + " 11 ) minutos");
    }

    
    
}

       
    



