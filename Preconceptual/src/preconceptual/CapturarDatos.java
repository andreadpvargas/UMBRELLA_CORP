package preconceptual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sebastian
 */
public class CapturarDatos extends JFrame {

    JTextField fSustantivo1, fSustantivo2;
    JComboBox cConector;
    JButton bAgregar;

    public CapturarDatos() {
        //inicializando los componentes
        fSustantivo1 = new JTextField();
        fSustantivo2 = new JTextField();
        cConector = new JComboBox();
        cConector.addItem("Seleccione...");
        cConector.addItem("Es");
        cConector.addItem("Tiene");
        cConector.addItem("Verbo");
        bAgregar=new JButton("Agregar");

        //configuración de los paneles
        //panel superior
        JPanel pSuperior = new JPanel(new GridLayout(2, 4));
        pSuperior.add(new JLabel("Sustantivo 1"));
        pSuperior.add(new JLabel("Conector"));
        pSuperior.add(new JLabel("Sustantivo 2"));
        pSuperior.add(new JLabel(""));
        pSuperior.add(fSustantivo1);
        pSuperior.add(cConector);
        pSuperior.add(fSustantivo2);
        pSuperior.add(bAgregar);
        
        setLayout(new BorderLayout());
        add(pSuperior,BorderLayout.NORTH);

        //inicializando el JFrame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Captura de datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

}
