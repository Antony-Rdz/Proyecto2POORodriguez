import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import ExamenJava.FileSystemModel;

@SuppressWarnings("serial")
public class ProfesorGUI extends JFrame {

	private JPanel PanelMayorProfesor;

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
					@SuppressWarnings("unused")
					ProfesorGUI frame = new ProfesorGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfesorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelMayorProfesor = new JPanel();
		PanelMayorProfesor.setBorder(new EmptyBorder(5, 5, 5, 5));
		PanelMayorProfesor.setLayout(new BorderLayout(10,10));
		setContentPane(PanelMayorProfesor);
		//PanelMayorProfesor.add(inicioSesionProfesor());
		PanelMayorProfesor.add(MenuProfesor());
		//PanelMayorProfesor.add(ventanaColocaNombreArchivo());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	JPanel inicioSesionProfesor() {
		JPanel panelContenedorInicioSesion = new JPanel();
		panelContenedorInicioSesion.setBorder(new EmptyBorder(20,20,20,20));
		panelContenedorInicioSesion.setLayout(new BoxLayout(panelContenedorInicioSesion, BoxLayout.Y_AXIS));
		
		JPanel panelEncabezado = new JPanel();
		panelContenedorInicioSesion.add(panelEncabezado);
		
		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel panelLblRut = new JPanel(new BorderLayout());
		panelContenedorInicioSesion.add(panelLblRut);
		
		JPanel panelTextRut = new JPanel();
		panelTextRut.setLayout(new BoxLayout(panelTextRut, BoxLayout.X_AXIS));
		panelContenedorInicioSesion.add(panelTextRut);
		
		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JPanel panelLblContra = new JPanel(new BorderLayout());
		panelContenedorInicioSesion.add(panelLblContra);
		
		JPanel panelTextContra = new JPanel();
		panelTextContra.setLayout(new BoxLayout(panelTextContra, BoxLayout.X_AXIS));
		panelContenedorInicioSesion.add(panelTextContra);
		
		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JPanel panelBotonLogin = new JPanel(new GridLayout(0,1,0,0));
		panelContenedorInicioSesion.add(panelBotonLogin);
		
		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel panelLblReset = new JPanel();
		panelContenedorInicioSesion.add(panelLblReset);
	
		
		JLabel lblEncabezado = new JLabel("Inicio de Sesion Profesor");
		lblEncabezado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncabezado.setFont(new Font("Dialog", Font.PLAIN, 25));
		panelEncabezado.add(lblEncabezado);
		
		JLabel lblRut = new JLabel("Rut");
		lblRut.setFont(new Font("Thaoma", Font.PLAIN, 18));
		panelLblRut.add(lblRut,BorderLayout.WEST);
		
		
		JTextField textRut = new JTextField();
		textRut.setBackground(Color.WHITE);
		textRut.setForeground(Color.BLACK);
		textRut.setFont(new Font("Thaoma", Font.PLAIN, 23));
		panelTextRut.add(textRut);
		
		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setHorizontalAlignment(SwingConstants.LEFT);
		lblContra.setFont(new Font("Thaoma", Font.PLAIN, 18));
		panelLblContra.add(lblContra,BorderLayout.WEST);
		
		JPasswordField textContrasenia = new JPasswordField();
		textContrasenia.setBackground(Color.WHITE);
		textContrasenia.setFont(new Font("Thaoma", Font.PLAIN, 23));
		panelTextContra.add(textContrasenia);
		
		JButton botonLogin = new JButton("Ingresar");
		botonLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelBotonLogin.add(botonLogin);
		panelLblReset.setLayout(new BorderLayout(0, 0));
		
		JLabel lblResetContra = new JLabel("Recuperar Contraseña");
		lblResetContra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblResetContra.setHorizontalAlignment(SwingConstants.LEFT);
		panelLblReset.add(lblResetContra);
		
		
		return panelContenedorInicioSesion;
	}

	
	private JPanel MenuProfesor() {
		
		JPanel panelContenedorMenu = new JPanel(new BorderLayout());
		JPanel panelContenedorIzquierdo = new JPanel(new BorderLayout());
		panelContenedorIzquierdo.setBorder(new EmptyBorder(0, 0, 20, 0));
		JPanel panelContenedorDerecho = new JPanel(new BorderLayout());
		
		panelContenedorMenu.add(panelContenedorIzquierdo,BorderLayout.WEST);
		panelContenedorMenu.add(panelContenedorDerecho,BorderLayout.CENTER);
		
		JLabel lblImagenProfesor = new JLabel();
		String ubicacionImagen="";
		try {
			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\profesor.png";			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		ImageIcon imagenAdmin =new ImageIcon(ubicacionImagen);
		lblImagenProfesor.setIcon(new ImageIcon(imagenAdmin.getImage().getScaledInstance(210, 200, Image.SCALE_DEFAULT)));
		panelContenedorIzquierdo.add(lblImagenProfesor,BorderLayout.CENTER);
		
		JPanel panelBotonSalir = new JPanel();
		panelContenedorIzquierdo.add(panelBotonSalir,BorderLayout.SOUTH);
		JButton botonSalir = new JButton("Salir");
		panelBotonSalir.add(botonSalir);
		
		JTabbedPane panelControladorProfesor = new JTabbedPane();
		panelContenedorDerecho.add(panelControladorProfesor,BorderLayout.CENTER);
		
		JPanel panelCreacionExamen = new JPanel(new BorderLayout());
		panelControladorProfesor.addTab("Examenes", panelCreacionExamen);
		
		JPanel panelNotasExamen = new JPanel(new BorderLayout());
		panelNotasExamen.setBorder(new EmptyBorder(10,10,10,10));
		panelControladorProfesor.addTab("Notas", panelNotasExamen);
		
		JPanel panelArbolCarpetasCreaExamen = new JPanel(new BorderLayout(0,10));
		panelArbolCarpetasCreaExamen.setBorder(new EmptyBorder(10, 10, 30, 0));
		panelCreacionExamen.add(panelArbolCarpetasCreaExamen,BorderLayout.WEST);
		
		/*JPanel panelTablaYCrearExamenes = new JPanel(new BorderLayout());
		panelTablaYCrearExamenes.setBorder(new EmptyBorder(10, 0, 10, 0));
		panelCreacionExamen.add(panelTablaYCrearExamenes,BorderLayout.CENTER);*/
		
		JPanel panelBotonCrear = new JPanel(new BorderLayout());
		panelBotonCrear.setBorder(new EmptyBorder(0, 0, 0, 30));
		panelArbolCarpetasCreaExamen.add(panelBotonCrear,BorderLayout.NORTH);
		
		JPanel panelExploradorDeArchivos = new JPanel(new BorderLayout());
		panelArbolCarpetasCreaExamen.add(panelExploradorDeArchivos,BorderLayout.CENTER);
		
		/*JPanel panelTablaDeExamenes = new JPanel();
		panelTablaDeExamenes.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTablaYCrearExamenes.add(panelTablaDeExamenes,BorderLayout.CENTER);*/
		
		/*JPanel panelEditaExamen = new JPanel();
		panelEditaExamen.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTablaYCrearExamenes.add(panelEditaExamen,BorderLayout.CENTER);*/
		
		JButton botonCrearExamen = new JButton("Crear Examen");
		botonCrearExamen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ventanaColocaNombreArchivo()) {
					//JOptionPane.showMessageDialog(null, "le puse nombre al archivo");
					dispose();
					@SuppressWarnings("unused")
					CrearExamen creacion = new CrearExamen();
				}
				
			}
		});
		panelBotonCrear.add(botonCrearExamen,BorderLayout.EAST);
		
		
		
		String ubicacionArchivos="";
		try {
			ubicacionArchivos = new File(".").getCanonicalPath() + "\\Profesor\\Examenes algo";	
			//ubicacionImagen = new File(".").getCanonicalPath() + "\\Profesor\\Examenes "+getProfesorName();	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		JTree arbolCarpetas = new JTree();
		FileSystemModel modelo = new FileSystemModel(new File(ubicacionArchivos));
		
		//System.out.println("El archivo se llama: "+modelo.getNombreArchivos(new File(ubicacionArchivos), 2));
		arbolCarpetas.setModel(modelo);
		JScrollPane scrollArbol = new JScrollPane(arbolCarpetas);
		//scrollArbol.setPreferredSize(new Dimension(200, 322));
		panelExploradorDeArchivos.add(scrollArbol,BorderLayout.CENTER);
		
		/*-JTable tablaDeExamenesCreados = new JTable();
		tablaDeExamenesCreados.setModel(encabezado());
		tablaDeExamenesCreados.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablaDeExamenesCreados.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeExamenesCreados.getTableHeader().setReorderingAllowed(false);
		tablaDeExamenesCreados.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollTabla = new JScrollPane(tablaDeExamenesCreados);
		panelEditaExamen.add(scrollTabla);*/
		
		JTable tablaDeExamenesNotas= new JTable();
		tablaDeExamenesNotas.setModel(encabezado());
		tablaDeExamenesNotas.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablaDeExamenesNotas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeExamenesNotas.getTableHeader().setReorderingAllowed(false);
		tablaDeExamenesNotas.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollTablaNota = new JScrollPane(tablaDeExamenesNotas);
		panelNotasExamen.add(scrollTablaNota,BorderLayout.CENTER);

                                                                                                                  
		return panelContenedorMenu;
	}
	
	DefaultTableModel encabezado() {
		String[] rubrosTablaProf = {"Nombre","Fecha"};
		DefaultTableModel tablaPorDefecto = new DefaultTableModel(rubrosTablaProf, 0);
		return tablaPorDefecto;	
	}
	
	
	JPanel AgregaExamen() {
		
		JPanel panelContenedorAgregaExamen = new JPanel();
		
		
		return panelContenedorAgregaExamen;
	}
	
	
	
	Boolean ventanaColocaNombreArchivo() {
		
		JPanel panel = new JPanel();	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10,10,0,10));
		
		JPanel panelLabel = new JPanel(new BorderLayout());
		panel.add(panelLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		JPanel paneltexto = new JPanel(new BorderLayout());
		panel.add(paneltexto);
		
		JLabel label = new JLabel("Cree Un Nombre Para Su Examen");
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setFont(new Font("Thaoma", Font.PLAIN, 15));

		JTextField ingresaNombreArchivo = new JTextField();
		ingresaNombreArchivo.setFont(new Font("Calibri", Font.BOLD, 20));
		ingresaNombreArchivo.setForeground(Color.BLACK);
		ingresaNombreArchivo.setBackground(Color.WHITE);
		
		panelLabel.add(label,BorderLayout.WEST);
		paneltexto.add(ingresaNombreArchivo,BorderLayout.CENTER);

		String[] options = new String[] { "Cancelar", "Aceptar" };

		int option = JOptionPane.showOptionDialog(null, panel, "Ingreso de Contraseña", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

		
		if(ingresaNombreArchivo.getText().isBlank() && option == 1) {
			JOptionPane.showMessageDialog(null, "Nombre no puede estar vacio");
			
		}else {
			if (option == 1) {
				//System.out.println("Acepte");
				//CrearExamen asd = new CrearExamen();
				//COLOCA EL NOMBRE AL ARCHIVO Y PROCEDO A MOSTRAR EL PANEL DE CREACION DE EXAMEN
				return true;
			} else {
				System.out.println("no le puse nombre a mi archivo");
				return false;
			}
		}
		
		return false;
	}
	
	
	
	/*public class CrearExamen extends JFrame{
		
		JPanel panelMayorCrearExamen;
		
		public CrearExamen() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panelMayorCrearExamen = new JPanel();
			panelMayorCrearExamen.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelMayorCrearExamen.setLayout(new BorderLayout(10,10));
			setContentPane(panelMayorCrearExamen);
			//PanelMayorProfesor.add(inicioSesionProfesor());
			panelMayorCrearExamen.add(panelIniciador());
			//PanelMayorProfesor.add(ventanaColocaNombreArchivo());
			//setResizable(false);
			//pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
		
		private JPanel panelIniciador() {
			JPanel panelContenedorCreaExamen = new JPanel();
			
			JLabel label = new JLabel("Equizde");
			panelContenedorCreaExamen.add(label);
			
			return panelContenedorCreaExamen;
		}  
	}*/
	
}
