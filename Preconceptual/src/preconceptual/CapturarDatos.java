package preconceptual;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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
    JButton bAgregar, bCanvas;
    JPanel pTriadas,pCentral;
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

        bCanvas = new JButton("Generar Canvas");
        bCanvas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dibujarCanvas();
            }
        });

        //panel central
        pTriadas = new JPanel();

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
        
        JPanel panelCanvas=new JPanel();
        panelCanvas.add(bCanvas);
        
        JPanel pSuperior2=new JPanel(new GridLayout(2,1));
        pSuperior2.add(pSuperior);
        pSuperior2.add(panelCanvas);

        setLayout(new BorderLayout());
        add(pSuperior2, BorderLayout.NORTH);
        pCentral = new JPanel();
        pCentral.add(pTriadas);
        add(new JScrollPane(pCentral), BorderLayout.CENTER);
        add(panelCanvas, BorderLayout.SOUTH);

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
        if (cSustantivo1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Indique el sustantivo 1");
            return false;
        }
        if (cConector.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Indique el conector");
            return false;
        }
        if (cSustantivo2.getSelectedItem() == null) {
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
        String dependencia = "null";
        if (id != 0) {
            dependencia = id + "";
        }
        //agregamos la triada
        ConexionSQL.statement("insert into triadas (sustantivo1,conector,sustantivo2,dependencia) values ('" + sustantivos.get(sustantivo1) + "','" + conector + "','" + sustantivos.get(sustantivo2) + "'," + dependencia + ")");
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
        pTriadas.removeAll();

        int contadorFilas = 0;

        ResultSet result = ConexionSQL.querys("select triadas.dependencia,triadas.id,sus1.nombre as sustantivo1,triadas.conector,sus2.nombre as sustantivo2 from triadas left join sustantivos as sus1 on sus1.id=triadas.sustantivo1 left join sustantivos as sus2 on sus2.id=triadas.sustantivo2");

        try {
            while (result.next()) {
                String dependencia = "";
                if (result.getString("dependencia") != null) {
                    dependencia = "(*)";
                }
                pTriadas.add(new JLabel(dependencia + result.getString("sustantivo1")));
                pTriadas.add(new JLabel(result.getString("conector")));
                pTriadas.add(new JLabel(result.getString("sustantivo2")));
                int id = result.getInt("id");
                JButton boton = new JButton("Agregar");
                boton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (datosValidos()) {
                            agregar(id);
                        }
                    }
                });

                JButton eliminar = new JButton("Eliminar");
                eliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        borrar(id);
                    }
                });

                pTriadas.add(boton);
                pTriadas.add(eliminar);

                contadorFilas++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CapturarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        pTriadas.setLayout(new GridLayout(contadorFilas, 4));
        pTriadas.repaint();
        pTriadas.updateUI();
    }

    private void borrar(int id) {
        ConexionSQL.statement("delete from triadas where id=" + id);
        actualizar();

    }

    private void dibujarCanvas() {
        JFrame frameCanvas=new JFrame();
        frameCanvas.setSize(700,800);
        frameCanvas.setLocationRelativeTo(null);
        frameCanvas.setTitle("Diagrama de Clases");
        frameCanvas.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameCanvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCanvas.setLayout(new GridLayout(1,1));
        frameCanvas.add(new JScrollPane(new miCanvas()));
        frameCanvas.setVisible(true);
    }
}