package preconceptual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian
 */
public class CapturarDatos extends JFrame {

    JComboBox cConector, cSustantivo1, cSustantivo2;
    ;
    JButton bAgregar;
    JPanel pCentral;
    int idEsquema = 1;
    Map<String, Integer> sustantivos;

    public CapturarDatos() {
        //inicializando los componentes
        sustantivos = new HashMap<String, Integer>();
        cSustantivo1 = new JComboBox();
        cSustantivo1.setEditable(true);
        cSustantivo2 = new JComboBox();
        cSustantivo2.setEditable(true);
        leerSustantivos();

        cConector = new JComboBox();
        cConector.addItem("Es");
        cConector.addItem("Tiene");
        cConector.setEditable(true);

        bAgregar = new JButton("Agregar");
        bAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (datosValidos()) {
                    agregar(0);
                }
            }
        });

        //panel central
        pCentral = new JPanel();

        //panel superior
        JPanel pSuperior = new JPanel(new GridLayout(2, 4));
        pSuperior.add(new JLabel("Sustantivo 1"));
        pSuperior.add(new JLabel("Conector"));
        pSuperior.add(new JLabel("Sustantivo 2"));
        pSuperior.add(new JLabel(""));
        pSuperior.add(cSustantivo1);
        pSuperior.add(cConector);
        pSuperior.add(cSustantivo2);
        pSuperior.add(bAgregar);

        setLayout(new BorderLayout());
        add(pSuperior, BorderLayout.NORTH);
        JPanel pAuxiliar = new JPanel();
        pAuxiliar.add(pCentral);
        add(new JScrollPane(pAuxiliar), BorderLayout.CENTER);

        //inicializando el JFrame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Captura de datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualizar();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    //si los datos son validos
    private boolean datosValidos() {
        if (cSustantivo1.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique el sustantivo 1");
            return false;
        }
        if (cConector.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique el conector");
            return false;
        }
        if (cSustantivo2.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique el sustantivo 2");
            return false;
        }
        return true;
    }

    //agregamos la triada
    private void agregar(int id) {
        String sustantivo1 = cSustantivo1.getSelectedItem().toString();
        String conector = cConector.getSelectedItem().toString();
        String sustantivo2 = cSustantivo2.getSelectedItem().toString();
        //agregamos los sustantivos
        ConexionSQL.statement("insert ignore into sustantivos (esquema,nombre) values (" + idEsquema + ",'" + sustantivo1 + "')");
        ConexionSQL.statement("insert ignore into sustantivos (esquema,nombre) values (" + idEsquema + ",'" + sustantivo2 + "')");

        leerSustantivos();

        System.out.println(sustantivo1);
        System.out.println(sustantivos.get(sustantivo1));
        System.out.println(sustantivo2);
        System.out.println(sustantivos.get(sustantivo2));
        //agregamos la triada
        ConexionSQL.statement("insert into triadas (sustantivo1,conector,sustantivo2) values ('" + sustantivos.get(sustantivo1) + "','" + conector + "','" + sustantivos.get(sustantivo2) + "')");
        actualizar();
    }

    private void leerSustantivos() {
        cSustantivo1.removeAllItems();
        cSustantivo2.removeAllItems();

        ResultSet result = ConexionSQL.querys("select *from sustantivos where esquema=" + idEsquema);
        try {
            while (result.next()) {
                cSustantivo1.addItem(result.getString("nombre"));
                cSustantivo2.addItem(result.getString("nombre"));
                sustantivos.put(result.getString("nombre"), result.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizar() {
        pCentral.removeAll();
        
        int contadorFilas = 0;

        ResultSet result = ConexionSQL.querys("select triadas.id,sus1.nombre as sustantivo1,triadas.conector,sus2.nombre as sustantivo2 from triadas left join sustantivos as sus1 on sus1.id=triadas.sustantivo1 left join sustantivos as sus2 on sus2.id=triadas.sustantivo2");

        try {
            while (result.next()) {
                pCentral.add(new JLabel(result.getString("sustantivo1")));
                pCentral.add(new JLabel(result.getString("conector")));
                pCentral.add(new JLabel(result.getString("sustantivo2")));
                int id = result.getInt("id");
                JButton boton = new JButton("Agregar");
                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (datosValidos()) {
                            agregar(0);
                        }
                    }
                });

                JButton eliminar = new JButton("Eliminar");
                eliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        borrar(id);
                    }
                });

                pCentral.add(boton);
                pCentral.add(eliminar);

                contadorFilas++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        pCentral.setLayout(new GridLayout(contadorFilas, 4));
        pCentral.repaint();
        pCentral.updateUI();
    }

    private void borrar(int id) {
        System.out.println("Borrando: " + id);
        ConexionSQL.statement("delete from triadas where id=" + id);
        actualizar();
    }
}
