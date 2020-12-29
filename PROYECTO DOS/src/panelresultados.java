import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

public class panelresultados extends JFrame {

	private JPanel tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panelresultados frame = new panelresultados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public panelresultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabla = new JPanel();
		tabla.setLayout(new BorderLayout(0, 0));
		setContentPane(tabla);
		tabla.add(tablaResultadosDeExamen());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	public JPanel tablaResultadosDeExamen() {
		JPanel panelMaestroTabla = new JPanel(new BorderLayout(0,20));
		JPanel panelMenu = new JPanel(new BorderLayout());
		panelMaestroTabla.add(panelMenu,BorderLayout.NORTH);
		JPanel panelTabla = new JPanel(new BorderLayout());
		panelTabla.setBorder(new EmptyBorder(0, 20, 20, 20));
		panelMaestroTabla.add(panelTabla,BorderLayout.CENTER);
		
		JMenuBar menuOpciones = new JMenuBar();
		panelMenu.add(menuOpciones,BorderLayout.CENTER);
		JMenu opciones = new JMenu("Opciones");
		menuOpciones.add(opciones);
		JMenuItem opcionVolver = new JMenuItem("Volver");
		opciones.add(opcionVolver);
		
		JTable tablaNotas = new JTable();
		tablaNotas.setPreferredSize(new Dimension(20,100));
		tablaNotas.setModel(encabezado());
		JScrollPane scrolltabla = new JScrollPane(tablaNotas);
		panelTabla.add(scrolltabla, BorderLayout.CENTER);
		
	
		
		
		
		
		return panelMaestroTabla;
	}
	
	
	DefaultTableModel encabezado() {
		String[] rubrosTablaProf = { "Nombre", "P1","P2","P3","P4","P5","Nota" };
		DefaultTableModel tablaPorDefecto = new DefaultTableModel(rubrosTablaProf, 0);
		return tablaPorDefecto;
	}

}
