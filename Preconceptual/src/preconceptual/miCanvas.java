package preconceptual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class miCanvas extends Canvas {

    int idEsquema = 1;

    public miCanvas() {
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        int margenHorizontal = 20;
        int margenVertical = 0;
        int contadorClases = 0;
        int clasesPorFila = 4;

        ResultSet result = ConexionSQL.querys("select *from sustantivos where esquema=" + idEsquema);

        try {
            while (result.next()) {
                //dibujar clase
                g.setColor(Color.black);

                //rectangulo del titulo
                g.drawRect(margenHorizontal + 10, margenVertical + 10, 200, 30);
                g.drawString(result.getString("nombre"), margenHorizontal + 15, margenVertical + 30);

                //rectangulo de los atributos
                g.drawRect(margenHorizontal + 10, margenVertical + 40, 200, 100);

                //leer los atributos del sustantivo
                ResultSet resultAtributos = ConexionSQL.querys("select sustantivos.nombre from triadas left join sustantivos on sustantivos.id=triadas.sustantivo2 where sustantivo1=" + result.getString("id") + " and conector='Tiene'");
                int contadorAtributos=24;
                try {
                    while (resultAtributos.next()) {
                        g.drawString(resultAtributos.getString("nombre"), margenHorizontal + 15, margenVertical + 30+contadorAtributos);
                        contadorAtributos+=20;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
                }

                //rectangulo de los metodos
                g.drawRect(margenHorizontal + 10, margenVertical + 140, 200, 100);

                //leer los metodos del sustantivo
                ResultSet resultMetodos = ConexionSQL.querys("select triadas.conector,sustantivos.nombre from triadas left join sustantivos on sustantivos.id=triadas.sustantivo2 where sustantivo1=" + result.getString("id") + " and conector<>'Tiene' and conector<>'Es'");
                int contadorMetodos=24;
                try {
                    while (resultMetodos.next()) {
                        g.drawString(resultMetodos.getString("conector")+"("+resultMetodos.getString("nombre")+")", margenHorizontal + 15, margenVertical + 140+contadorMetodos);
                        contadorMetodos+=20;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
                }

                //contadores
                margenHorizontal += 300;
                contadorClases++;
                if (contadorClases == clasesPorFila) {
                    margenVertical += 280;
                    margenHorizontal = 20;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
