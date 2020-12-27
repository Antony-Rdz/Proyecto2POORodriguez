import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

public class InicioSesion extends JFrame {

	private JPanel PanelMayorInicioSesion;


	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		InicioSesion frame = new InicioSesion();

	}

	public JPanel Iniciador() {

		JPanel panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(20, 10, 20, 10));
		panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
		
		JPanel panelLabel = new JPanel();
		panelContenedor.add(panelLabel);

		panelContenedor.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelEleccion = new JPanel(new GridLayout(0, 3, 3, 0));
		panelEleccion.setPreferredSize(new Dimension(350, 35));
		panelContenedor.add(panelEleccion);

		panelContenedor.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelBotones = new JPanel(new GridLayout(0,2,100,0));
		panelContenedor.add(panelBotones);

		JLabel encabezado = new JLabel("Elije que eres");
		encabezado.setFont(new Font("Tahoma", Font.PLAIN, 22));
		encabezado.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(encabezado, BorderLayout.CENTER);

		ButtonGroup grupoelecciones = new ButtonGroup();
		JRadioButton eleccionAdmin = new JRadioButton("Admin");
		eleccionAdmin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
					new AdminGUI();
				}
			}
		});
		eleccionAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		eleccionAdmin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		JRadioButton eleccionProfe = new JRadioButton("Profesor");
		eleccionProfe.setHorizontalAlignment(SwingConstants.CENTER);
		eleccionProfe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		JRadioButton eleccionAlumno = new JRadioButton("Alumno");
		eleccionAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		eleccionAlumno.setFont(new Font("Tahoma", Font.PLAIN, 22));

		grupoelecciones.add(eleccionAdmin);
		grupoelecciones.add(eleccionProfe);
		grupoelecciones.add(eleccionAlumno);

		panelEleccion.add(eleccionAlumno);
		panelEleccion.add(eleccionProfe);
		panelEleccion.add(eleccionAdmin);

		JButton botonSiguiente = new JButton("Iniciar");
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(eleccionAdmin.isSelected()) {
					dispose();
					new AdminGUI();
				}
				if(eleccionAlumno.isSelected()) {
					dispose();
					new AlumnoGUI();
					
				}
				if(eleccionProfe.isSelected()) {
					dispose();
					new ProfesorGUI();
				}
			}
		});
		botonSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelBotones.add(botonSiguiente);
		JButton botonSalir = new JButton("Salir");
		botonSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		botonSalir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelBotones.add(botonSalir);

		return panelContenedor;
	}

	
	public InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelMayorInicioSesion = new JPanel();
		PanelMayorInicioSesion.setBorder(new EmptyBorder(5, 5, 5, 5));
		PanelMayorInicioSesion.setLayout(new BorderLayout(0, 0));
		setContentPane(PanelMayorInicioSesion);
		PanelMayorInicioSesion.add(Iniciador());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/*public static JRadioButton getSelection(ButtonGroup group) {
		for (Enumeration e = group.getElements(); e.hasMoreElements();) {
			JRadioButton b = (JRadioButton) e.nextElement();
			if (b.getModel() == group.getSelection()) {
				return b;
			}
		}
		return null;
	}*/
}
